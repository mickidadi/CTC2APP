package com.ucc.ctc.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ucc.ctc.dao.ClientDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.entity.ClientEntity;

import java.util.List;

public class ClientRepository {
    private ClientDao clientDao;
    private LiveData<List<ClientEntity>> allClients;
    private LiveData<List<String>> allClientName;
    public ClientRepository(Application application)
    {
        CTCDatabase ctcDatabase = CTCDatabase.getInstance(application);
        clientDao = ctcDatabase.clientDao();

        allClients = clientDao.getAllClients();
        allClientName = clientDao.getAllClientName();
    }

    public void insert(ClientEntity client) {
        new InsertClientAsyncTask(clientDao).execute(client);
    }

    public void update(ClientEntity client){
        new UpdateClientAsyncTask(clientDao).execute(client);
    }

    public void delete(ClientEntity client){
        new DeleteClientAsyncTask(clientDao).execute(client);
    }

    public void deleteAllClients() {
        new DeleteAllClientsAsyncTask(clientDao).execute();
    }

    public LiveData<List<ClientEntity>> getAllClients() {
        return allClients;
    }
    public LiveData<List<ClientEntity>> getAllClientSearch(String clientId,String name) {
        return clientDao.getAllClientSearch(clientId,name);
    }
    public LiveData<List<String>> getAllClientName() {
        return allClientName;
    }
    //AsyncTask for create new client
    private static class InsertClientAsyncTask extends AsyncTask<ClientEntity, Void, Void> {

        private ClientDao clientDao;

        public InsertClientAsyncTask(ClientDao clientDao) {
            this.clientDao = clientDao;
        }


        @Override
        protected Void doInBackground(ClientEntity... clients) {
            clientDao.insert(clients[0]);
            return null;
        }
    }

    //AsyncTask for update existing client
    private static class UpdateClientAsyncTask extends AsyncTask<ClientEntity, Void, Void>{

        private ClientDao clientDao;

        public UpdateClientAsyncTask(ClientDao clientDao) {
            this.clientDao = clientDao;
        }

        @Override
        protected Void doInBackground(ClientEntity... clients) {
            ///
            clientDao.update(clients[0]);
            return null;
        }
    }

    //AsyncTask for delete existing client
    private static class DeleteClientAsyncTask extends AsyncTask<ClientEntity, Void, Void>{

        private ClientDao clientDao;

        public DeleteClientAsyncTask (ClientDao clientDao){
            this.clientDao = clientDao;
        }

        @Override
        protected Void doInBackground(ClientEntity... clients) {
            clientDao.delete(clients[0]);
            return null;
        }
    }

    //AsyncTask for delete all clients
    private static class DeleteAllClientsAsyncTask extends AsyncTask<Void, Void, Void>{

        private ClientDao clientDao;

        public DeleteAllClientsAsyncTask(ClientDao clientDao){
            this.clientDao = clientDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            clientDao.deleteAllClient();
            return null;
        }
    }
}
