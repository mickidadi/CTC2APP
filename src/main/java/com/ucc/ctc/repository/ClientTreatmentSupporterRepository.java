package com.ucc.ctc.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ucc.ctc.dao.ClientTreatmentSupporterDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.entity.ClientTreatmentSupporterEntity;
import java.util.List;

public class ClientTreatmentSupporterRepository {
    private ClientTreatmentSupporterDao clientTreatmentSupporterDao;
    public ClientTreatmentSupporterRepository(Application application)
    {
        CTCDatabase ctcDatabase = CTCDatabase.getInstance(application);
        clientTreatmentSupporterDao = ctcDatabase.clientTreatmentSupporterDao();

    }

    public void insert(ClientTreatmentSupporterEntity clientTreatmentSupporterEntity) {
        new InsertClientSupporterAsyncTask(clientTreatmentSupporterDao).execute(clientTreatmentSupporterEntity);
    }

    public void update(ClientTreatmentSupporterEntity clientPhysicalAddressEntity){
        new UpdateClientTreatmentSupporterAsyncTask(clientTreatmentSupporterDao).execute(clientPhysicalAddressEntity);
    }

    public void delete(ClientTreatmentSupporterEntity clientTreatmentSupporterEntity){
        new DeleteClientTreatmentAsyncTask(clientTreatmentSupporterDao).execute(clientTreatmentSupporterEntity);
    }

    public LiveData<ClientTreatmentSupporterEntity> getClientSupporterSearch(String clientId) {
        return clientTreatmentSupporterDao.getClientTreatmentSupporter(clientId);
    }
    //AsyncTask for create new client Treatment Supporter
    private static class InsertClientSupporterAsyncTask extends AsyncTask<ClientTreatmentSupporterEntity, Void, Void> {

        private ClientTreatmentSupporterDao clientTreatmentSupporterDao;

        public InsertClientSupporterAsyncTask(ClientTreatmentSupporterDao clientTreatmentSupporterDao) {
            this.clientTreatmentSupporterDao = clientTreatmentSupporterDao;
        }


        @Override
        protected Void doInBackground(ClientTreatmentSupporterEntity... supporter) {
            clientTreatmentSupporterDao.insert(supporter[0]);
            return null;
        }
    }

    //AsyncTask for update existing client Treatment Supporter
    private static class UpdateClientTreatmentSupporterAsyncTask extends AsyncTask<ClientTreatmentSupporterEntity, Void, Void>{

        private ClientTreatmentSupporterDao clientTreatmentSupporterDao;

        public UpdateClientTreatmentSupporterAsyncTask(ClientTreatmentSupporterDao clientTreatmentSupporterDao) {
            this.clientTreatmentSupporterDao=clientTreatmentSupporterDao;
        }

        @Override
        protected Void doInBackground(ClientTreatmentSupporterEntity... supporter) {
            ///
            clientTreatmentSupporterDao.update(supporter[0]);
            return null;
        }
    }

    //AsyncTask for delete existing client Treatment Supporter
    private static class DeleteClientTreatmentAsyncTask extends AsyncTask<ClientTreatmentSupporterEntity, Void, Void>{

        private ClientTreatmentSupporterDao clientTreatmentSupporterDao;

        public DeleteClientTreatmentAsyncTask (ClientTreatmentSupporterDao clientTreatmentSupporterDao){
            this.clientTreatmentSupporterDao= clientTreatmentSupporterDao;
        }

        @Override
        protected Void doInBackground(ClientTreatmentSupporterEntity... supporter) {
            clientTreatmentSupporterDao.delete(supporter[0]);
            return null;
        }
    }

}
