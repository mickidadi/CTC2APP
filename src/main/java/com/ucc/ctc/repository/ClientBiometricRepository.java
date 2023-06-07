package com.ucc.ctc.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.neurotec.biometrics.NBiometricOperation;
import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.biometrics.NBiometricTask;
import com.neurotec.biometrics.NSubject;
import com.neurotec.biometrics.client.NBiometricClient;
import com.ucc.ctc.dao.ClientBiometricDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.entity.ClientBiometricEntity;
import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.viewsModel.ClientBiometricViewModel;

import java.nio.ByteBuffer;
import java.util.EnumSet;
import java.util.List;

public class ClientBiometricRepository {
    private ClientBiometricDao clientBiometricDao;
    private LiveData<List<ClientBiometricEntity>> allClientBiometric;
    private List<ClientBiometricEntity> allClientBiometrics;
    public ClientBiometricRepository(Application application)
    {
        CTCDatabase ctcDatabase = CTCDatabase.getInstance(application);
        clientBiometricDao = ctcDatabase.clientBiometricDao();
        allClientBiometric = clientBiometricDao.getAllClientBiometricData();
       // allClientBiometrics = clientBiometricDao.getAllClientBiometric();
       // EnrolledAllClientBiometricAsyncTask task = new EnrolledAllClientBiometricAsyncTask(allClientBiometrics);
       /// task.execute();

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
    public LiveData<List<ClientBiometricEntity>> getAllClientBiometric() {
         return allClientBiometric;
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
    //AsyncTask for Enrolled all clients Biometric Data
    private static class EnrolledAllClientBiometricAsyncTask extends AsyncTask<Void, Void, Void>{

        private List<ClientBiometricEntity> clientBiometricEntities;

        public EnrolledAllClientBiometricAsyncTask(List<ClientBiometricEntity> clientBiometricEntities){
            this.clientBiometricEntities = clientBiometricEntities;
        }
       @Override
        protected Void doInBackground(Void... voids) {
            // Retrieve all template entities from the Room database using a query
                    ///List<ClientBiometricEntity> clientBiometricEntities = allClientBiometric.getAllClientBiometricData();
                       if (clientBiometricEntities.size() > 0) {
                        NSubject candidateSubject = null;
                        NBiometricClient biometricClient = new NBiometricClient();
                        NBiometricStatus status = NBiometricStatus.NONE;
                        NSubject CandidateSubject = null;
                        NBiometricTask enrollTask = biometricClient.createTask( EnumSet.of( NBiometricOperation.ENROLL), null);
                        NBiometricStatus Status = NBiometricStatus.NONE;
                        // Loop through all client fingerprint templates (candidates) and enroll them all
                        int i=0;
                        for (ClientBiometricEntity templateEntity : clientBiometricEntities) {
                          // for (MyRecord record : records) {
                            //DataRow dr = dtClientsBiometry.Rows.get(i);
                            candidateSubject = NSubject.fromMemory( ByteBuffer.wrap( (byte[])templateEntity.getBiometricTemplate() ) );
                            candidateSubject.setId("CandidateSubject_" + templateEntity.getId()+ "_" + templateEntity.getClientId());
                            enrollTask.getSubjects().add(candidateSubject);
                            if ((i + 1) % 500 == 0 || i == clientBiometricEntities.size() - 1) { // Enroll a batch of <= 500 templates
                                biometricClient.performTask(enrollTask);
                                status = enrollTask.getStatus();
                                if (status != NBiometricStatus.OK && status != NBiometricStatus.DUPLICATE_ID) {

                                    //Log.v("Biometric engine initialization failed. Status:","");
                                    Log.v("Biometric_init_failed" , status.toString() + "(" + status + ")" +enrollTask.getError().getMessage());
                                    return null;
                                } else {
                                    enrollTask.getSubjects().clear(); // Prepare 'enrollTask' for another set of 500 templates in the next loops
                                }
                            }
                        }
                        ////LastInitClientBiometryId = dtClientsBiometry.Rows.get(dtClientsBiometry.Rows.size() - 1).getInt("ClientBiometryId");
                    }
              return null;
        }
    }
}

