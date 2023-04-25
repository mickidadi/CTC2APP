package com.ucc.ctc.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ucc.ctc.dao.AdminHierarchyDao;
import com.ucc.ctc.dao.FacilityDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.entity.AdminHierarchyEntity;
import com.ucc.ctc.models.entity.FacilityEntity;

import java.util.List;

public class AdminHierarchyRepository {
    private AdminHierarchyDao adminHierarchyDao;
    private LiveData<List<AdminHierarchyEntity>> allCouncil;
    //private LiveData<List<String>> allFacilityName;

    public AdminHierarchyRepository(Application application)
    {
        CTCDatabase ctcDatabase = CTCDatabase.getInstance(application);
        adminHierarchyDao = ctcDatabase.adminHierarchyDao();
        allCouncil = adminHierarchyDao.getAllCouncil();

    }

    public void insert(AdminHierarchyEntity adminHierarchy) {
        new InsertAdminHierarchyAsyncTask(adminHierarchyDao).execute(adminHierarchy);
    }

    public void update(AdminHierarchyEntity adminHierarchy){
        new UpdateAdminHierarchyAsyncTask(adminHierarchyDao).execute(adminHierarchy);
    }

    public void delete(AdminHierarchyEntity adminHierarchy){
        new DeleteAdminHierarchyAsyncTask(adminHierarchyDao).execute(adminHierarchy);
    }

    public void deleteAllAdminHierarchy() {
        new DeleteAllAdminHierarchyAsyncTask(adminHierarchyDao).execute();
    }


    //AsyncTask for create new AdminHierarchy
    private static class InsertAdminHierarchyAsyncTask extends AsyncTask<AdminHierarchyEntity, Void, Void> {

        private AdminHierarchyDao adminHierarchyDao;

        public InsertAdminHierarchyAsyncTask(AdminHierarchyDao adminHierarchyDao) {
            this.adminHierarchyDao = adminHierarchyDao;
        }


        @Override
        protected Void doInBackground(AdminHierarchyEntity... adminHierarchyEntities) {
            adminHierarchyDao.insert(adminHierarchyEntities[0]);
            return null;
        }
    }

    //AsyncTask for update existing AdminHierarchy
    private static class UpdateAdminHierarchyAsyncTask extends AsyncTask<AdminHierarchyEntity, Void, Void>{

        private AdminHierarchyDao adminHierarchyDao;

        public UpdateAdminHierarchyAsyncTask(AdminHierarchyDao adminHierarchyDao) {
            this.adminHierarchyDao = adminHierarchyDao;
        }

        @Override
        protected Void doInBackground(AdminHierarchyEntity... adminHierarchyEntities) {
            ///
            adminHierarchyDao.update(adminHierarchyEntities[0]);
            return null;
        }
    }

    //AsyncTask for delete existing AdminHierarchy
    private static class DeleteAdminHierarchyAsyncTask extends AsyncTask<AdminHierarchyEntity, Void, Void>{

        private AdminHierarchyDao adminHierarchyDao;

        public DeleteAdminHierarchyAsyncTask (AdminHierarchyDao adminHierarchyDao){
            this.adminHierarchyDao = adminHierarchyDao;
        }

        @Override
        protected Void doInBackground(AdminHierarchyEntity... adminHierarchyEntities) {
            adminHierarchyDao.delete(adminHierarchyEntities[0]);
            return null;
        }
    }

    //AsyncTask for delete all AdminHierarchy
    private static class DeleteAllAdminHierarchyAsyncTask extends AsyncTask<Void, Void, Void>{

        private AdminHierarchyDao adminHierarchyDao;

        public DeleteAllAdminHierarchyAsyncTask(AdminHierarchyDao adminHierarchyDao){
            this.adminHierarchyDao = adminHierarchyDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            adminHierarchyDao.deleteAllAdminHierarchy();
            return null;
        }
    }
    public LiveData<List<AdminHierarchyEntity>> getAllCouncil() {
        return allCouncil;
    }

    public LiveData<List<AdminHierarchyEntity>> getDivisionById(String nodeId) {
        return adminHierarchyDao.getDivisionById(nodeId);
    }
    public LiveData<List<AdminHierarchyEntity>> getWardById(String nodeId) {
        return adminHierarchyDao.getWardById(nodeId);
    }
    public LiveData<List<AdminHierarchyEntity>> getVillageById(String nodeId) {
        return adminHierarchyDao.getVillageById(nodeId);
    }
}
