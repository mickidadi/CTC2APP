package com.ucc.ctc.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ucc.ctc.dao.PatientVisitDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.entity.PatientVisitEntity;

import java.util.List;
public class PatientVisitRepository {
    private PatientVisitDao visitDao;
    private LiveData<List<PatientVisitEntity>> allVisits;
    public PatientVisitRepository(Application application)
    {
        CTCDatabase ctcDatabase = CTCDatabase.getInstance(application);
        visitDao = ctcDatabase.patientVisitDao();
        //allVisits = visitDao.getAllVisits();

    }
    public void insert(PatientVisitEntity visit) {
        new InsertPatientVisitAsyncTask(visitDao).execute(visit);
    }

    public void update(PatientVisitEntity visit){
        new UpdatePatientVisitAsyncTask(visitDao).execute(visit);
    }

    public void delete(PatientVisitEntity visit){
        new DeletePatientVisitAsyncTask(visitDao).execute(visit);
    }

    public void deleteAllVisit() {
        new DeleteAllPatientVisitsAsyncTask(visitDao).execute();
    }

    public LiveData<List<PatientVisitEntity>> getAllVisits() {
        return allVisits;
    }

    //AsyncTask for create new Patient Visits
    private static class InsertPatientVisitAsyncTask extends AsyncTask<PatientVisitEntity, Void, Void> {

        private PatientVisitDao visitDao;

        public InsertPatientVisitAsyncTask(PatientVisitDao visitDao) {
            this.visitDao = visitDao;
        }


        @Override
        protected Void doInBackground(PatientVisitEntity... visit) {
            visitDao.insert(visit[0]);
            return null;
        }
    }

    //AsyncTask for update existing client
    private static class UpdatePatientVisitAsyncTask extends AsyncTask<PatientVisitEntity, Void, Void>{

        private PatientVisitDao visitDao;

        public UpdatePatientVisitAsyncTask(PatientVisitDao visitDao) {
            this.visitDao = visitDao;
        }

        @Override
        protected Void doInBackground(PatientVisitEntity... visit) {
            ///
            visitDao.update(visit[0]);
            return null;
        }
    }

    //AsyncTask for delete existing client
    private static class DeletePatientVisitAsyncTask extends AsyncTask<PatientVisitEntity, Void, Void>{

        private PatientVisitDao visitDao;

        public DeletePatientVisitAsyncTask (PatientVisitDao visitDao){
            this.visitDao = visitDao;
        }

        @Override
        protected Void doInBackground(PatientVisitEntity... visits) {
            visitDao.delete(visits[0]);
            return null;
        }
    }
   //AsyncTask for delete all Patient Visit
    private static class DeleteAllPatientVisitsAsyncTask extends AsyncTask<Void, Void, Void>{

        private PatientVisitDao visitDao;

        public DeleteAllPatientVisitsAsyncTask(PatientVisitDao visitDao){
            this.visitDao = visitDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
           // visitDao;
            return null;
        }
    }
}
