package com.ucc.ctc.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ucc.ctc.dao.ClientBiometricDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.entity.ClientBiometricEntity;
import com.ucc.ctc.models.entity.ClientEntity;

import java.util.List;

public class ClientBiometricRepository {
    private ClientBiometricDao clientBiometricDao;
    private LiveData<List<ClientEntity>> allClients;
    private LiveData<List<String>> allClientName;
    public ClientBiometricRepository(Application application)
    {
        CTCDatabase ctcDatabase = CTCDatabase.getInstance(application);
        clientBiometricDao = ctcDatabase.clientBiometricDao();

    }

    public void insert(ClientBiometricEntity clientBiometric) {
        new InsertClientBiometricAsyncTask(clientBiometricDao).execute(clientBiometric);
    }

    public void update(ClientBiometricEntity clientBiometric){
        new UpdateClientBiometricAsyncTask(clientBiometricDao).execute(clientBiometric);
    }

    public void delete(ClientBiometricEntity clientBiometric){
        new DeleteClientBiometricAsyncTask(clientBiometricDao).execute(clientBiometric);
    }

    public void deleteAllClientBiometric() {
        new DeleteAllClientBiometricAsyncTask(clientBiometricDao).execute();
        //DeleteAllClientBiometricAsyncTask
    }
    public LiveData<List<ClientBiometricEntity>> getAllClientBiometricSearch(String clientId) {
        return clientBiometricDao.getAllClientBiometric(clientId);
    }
    public LiveData<List<String>> getAllClientName() {
        return allClientName;
    }
    //AsyncTask for create new client Biometric Data
    private static class InsertClientBiometricAsyncTask extends AsyncTask<ClientBiometricEntity, Void, Void> {

        private ClientBiometricDao clientBiometricDao;

        public InsertClientBiometricAsyncTask(ClientBiometricDao clientBiometricDao) {
            this.clientBiometricDao = clientBiometricDao;
        }


        @Override
        protected Void doInBackground(ClientBiometricEntity... clientBiometric) {
            clientBiometricDao.insert(clientBiometric[0]);
            return null;
        }
    }

    //AsyncTask for update existing client Biometric Data
    private static class UpdateClientBiometricAsyncTask extends AsyncTask<ClientBiometricEntity, Void, Void>{

        private ClientBiometricDao clientBiometricDao;

        public UpdateClientBiometricAsyncTask(ClientBiometricDao clientBiometricDao) {
            this.clientBiometricDao = clientBiometricDao;
        }

        @Override
        protected Void doInBackground(ClientBiometricEntity... clientBiometric) {
            ///
            clientBiometricDao.update(clientBiometric[0]);
            return null;
        }
    }

    //AsyncTask for delete existing client Biometric Data
    private static class DeleteClientBiometricAsyncTask extends AsyncTask<ClientBiometricEntity, Void, Void>{

        private ClientBiometricDao clientBiometricDao;

        public DeleteClientBiometricAsyncTask (ClientBiometricDao clientBiometricDao){
            this.clientBiometricDao = clientBiometricDao;
        }

        @Override
        protected Void doInBackground(ClientBiometricEntity... clientBiometric) {
            clientBiometricDao.delete(clientBiometric[0]);
            return null;
        }
    }

    //AsyncTask for delete all clients Biometric Data
    private static class DeleteAllClientBiometricAsyncTask extends AsyncTask<Void, Void, Void>{

        private ClientBiometricDao clientBiometricDao;

        public DeleteAllClientBiometricAsyncTask(ClientBiometricDao clientBiometricDao){
            this.clientBiometricDao = clientBiometricDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
           // clientBiometricDao.deleteAllClient();
            return null;
        }
    }
}

