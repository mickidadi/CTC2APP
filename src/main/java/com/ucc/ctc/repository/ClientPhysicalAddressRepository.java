package com.ucc.ctc.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ucc.ctc.dao.ClientDao;
import com.ucc.ctc.dao.ClientPhysicalAddressDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.models.entity.ClientPhysicalAddressEntity;

import java.util.List;

public class ClientPhysicalAddressRepository {
    private ClientPhysicalAddressDao clientPhysicalAddressDao;
    public ClientPhysicalAddressRepository(Application application)
    {
        CTCDatabase ctcDatabase = CTCDatabase.getInstance(application);
        clientPhysicalAddressDao = ctcDatabase.clientPhysicalAddressDao();

    }

    public void insert(ClientPhysicalAddressEntity clientPhysicalAddress) {
        new InsertClientAddressAsyncTask(clientPhysicalAddressDao).execute(clientPhysicalAddress);
    }

    public void update(ClientPhysicalAddressEntity clientPhysicalAddressEntity){
        new UpdateClientAddressAsyncTask(clientPhysicalAddressDao).execute(clientPhysicalAddressEntity);
    }

    public void delete(ClientPhysicalAddressEntity clientPhysicalAddress){
        new DeleteClientAddressAsyncTask(clientPhysicalAddressDao).execute(clientPhysicalAddress);
    }

    public LiveData<ClientPhysicalAddressEntity> getClientPhysicalAddressSearch(String clientId) {
        return clientPhysicalAddressDao.getClientPhysicalAddress(clientId);
    }
    //AsyncTask for create new client physical Address
    private static class InsertClientAddressAsyncTask extends AsyncTask<ClientPhysicalAddressEntity, Void, Void> {

        private ClientPhysicalAddressDao clientPhysicalAddressDao;

        public InsertClientAddressAsyncTask(ClientPhysicalAddressDao clientPhysicalAddressDao) {
            this.clientPhysicalAddressDao = clientPhysicalAddressDao;
        }


        @Override
        protected Void doInBackground(ClientPhysicalAddressEntity... address) {
            clientPhysicalAddressDao.insert(address[0]);
            return null;
        }
    }

    //AsyncTask for update existing client Address
    private static class UpdateClientAddressAsyncTask extends AsyncTask<ClientPhysicalAddressEntity, Void, Void>{

        private ClientPhysicalAddressDao clientPhysicalAddressDao;

        public UpdateClientAddressAsyncTask(ClientPhysicalAddressDao clientPhysicalAddressDao) {
            this.clientPhysicalAddressDao=clientPhysicalAddressDao;
        }

        @Override
        protected Void doInBackground(ClientPhysicalAddressEntity... address) {
            ///
            clientPhysicalAddressDao.update(address[0]);
            return null;
        }
    }

    //AsyncTask for delete existing client Address
    private static class DeleteClientAddressAsyncTask extends AsyncTask<ClientPhysicalAddressEntity, Void, Void>{

        private ClientPhysicalAddressDao clientPhysicalAddressDao;

        public DeleteClientAddressAsyncTask (ClientPhysicalAddressDao clientPhysicalAddressDao){
            this.clientPhysicalAddressDao= clientPhysicalAddressDao;
        }

        @Override
        protected Void doInBackground(ClientPhysicalAddressEntity... address) {
            clientPhysicalAddressDao.delete(address[0]);
            return null;
        }
    }

}
