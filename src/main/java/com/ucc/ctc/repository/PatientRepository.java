package com.ucc.ctc.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ucc.ctc.dao.PatientDao;
import com.ucc.ctc.dao.PatientVisitDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.entity.PatientEntity;
import com.ucc.ctc.models.entity.PatientVisitEntity;

import java.util.List;

public class PatientRepository {
    private PatientDao patientDao;
    private LiveData<List<PatientEntity>> allPatient;
    public PatientRepository(Application application)
    {
        CTCDatabase ctcDatabase = CTCDatabase.getInstance(application);
        patientDao = ctcDatabase.patientDao();
        //allVisits = visitDao.getAllVisits();

    }
    public void insert(PatientEntity patientEntity) {
        new InsertPatientAsyncTask(patientDao).execute(patientEntity);
    }

    public void update(PatientEntity patientEntity){
        new UpdatePatientAsyncTask(patientDao).execute(patientEntity);
    }

    public void delete(PatientEntity patientEntity){
        new DeletePatientAsyncTask(patientDao).execute(patientEntity);
    }

    public void deleteAllPatient() {
        new DeleteAllPatientAsyncTask(patientDao).execute();
    }

    public LiveData<List<PatientEntity>> getAllPatient() {
        return allPatient;
    }

    //AsyncTask for create new Patient
    private static class InsertPatientAsyncTask extends AsyncTask<PatientEntity, Void, Void> {

        private PatientDao patientDao;

        public InsertPatientAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }


        @Override
        protected Void doInBackground(PatientEntity... patient) {
            patientDao.insert(patient[0]);
            return null;
        }
    }

    //AsyncTask for update existing client
    private static class UpdatePatientAsyncTask extends AsyncTask<PatientEntity, Void, Void>{

        private PatientDao patientDao;

        public UpdatePatientAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(PatientEntity... patient) {
            ///
            patientDao.update(patient[0]);
            return null;
        }
    }

    //AsyncTask for delete existing client
    private static class DeletePatientAsyncTask extends AsyncTask<PatientEntity, Void, Void>{

        private PatientDao patientDao;

        public DeletePatientAsyncTask (PatientDao patientDao){
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(PatientEntity... patient) {
            patientDao.delete(patient[0]);
            return null;
        }
    }
   //AsyncTask for delete all Patient Visit
    private static class DeleteAllPatientAsyncTask extends AsyncTask<Void, Void, Void>{

        private PatientDao patientDao;

        public DeleteAllPatientAsyncTask(PatientDao patientDao){
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
           // visitDao;
            return null;
        }
    }
}
