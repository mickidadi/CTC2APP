package com.ucc.ctc.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ucc.ctc.dao.FacilityDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.entity.FacilityEntity;

import java.util.List;

public class FacilityRepository {
    private FacilityDao facilityDao;
    private LiveData<List<FacilityEntity>> allFacilities;
    private LiveData<List<String>> allFacilityName;
    //private LiveData<List<String>> allFacilityName;

    public FacilityRepository(Application application)
    {
        CTCDatabase ctcDatabase = CTCDatabase.getInstance(application);
        facilityDao = ctcDatabase.facilityDao();

        allFacilities = facilityDao.getAllFacility();
        allFacilityName = facilityDao.getAllFacilityName();
    }

    public void insert(FacilityEntity facility) {
        new InsertFacilityAsyncTask(facilityDao).execute(facility);
    }

    public void update(FacilityEntity facility){
        new UpdateFacilityAsyncTask(facilityDao).execute(facility);
    }

    public void delete(FacilityEntity facility){
        new DeleteFacilityAsyncTask(facilityDao).execute(facility);
    }

    public void deleteAllFacility() {
        new DeleteAllFacilityAsyncTask(facilityDao).execute();
    }

    public LiveData<List<FacilityEntity>> getAllFacilities() {
        return allFacilities;
    }

    //AsyncTask for create new Facility
    private static class InsertFacilityAsyncTask extends AsyncTask<FacilityEntity, Void, Void> {

        private FacilityDao facilityDao;

        public InsertFacilityAsyncTask(FacilityDao facilityDao) {
            this.facilityDao = facilityDao;
        }


        @Override
        protected Void doInBackground(FacilityEntity... facilities) {
            facilityDao.insert(facilities[0]);
            return null;
        }
    }

    //AsyncTask for update existing Facility
    private static class UpdateFacilityAsyncTask extends AsyncTask<FacilityEntity, Void, Void>{

        private FacilityDao facilityDao;

        public UpdateFacilityAsyncTask(FacilityDao facilityDao) {
            this.facilityDao = facilityDao;
        }

        @Override
        protected Void doInBackground(FacilityEntity... facilities) {
            ///
            facilityDao.update(facilities[0]);
            return null;
        }
    }

    //AsyncTask for delete existing Facility
    private static class DeleteFacilityAsyncTask extends AsyncTask<FacilityEntity, Void, Void>{

        private FacilityDao facilityDao;

        public DeleteFacilityAsyncTask (FacilityDao facilityDao){
            this.facilityDao = facilityDao;
        }

        @Override
        protected Void doInBackground(FacilityEntity... facilities) {
            facilityDao.delete(facilities[0]);
            return null;
        }
    }

    //AsyncTask for delete all Facility
    private static class DeleteAllFacilityAsyncTask extends AsyncTask<Void, Void, Void>{

        private FacilityDao facilityDao;

        public DeleteAllFacilityAsyncTask(FacilityDao facilityDao){
            this.facilityDao = facilityDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            facilityDao.deleteAllFacility();
            return null;
        }
    }
    public LiveData<List<String>> getAllFacilityName() {

        return allFacilityName;
    }
    public LiveData<FacilityEntity> getFacilityById(String hfrId) {
        return facilityDao.getFacilityById(hfrId);
    }

}
