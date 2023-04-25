package com.ucc.ctc.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import com.ucc.ctc.dao.AdminHierarchyExtendedDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.entity.AdminHierarchyExtendedEntity;

import java.util.List;

public class AdminHierarchyExtendedRepository {
    private AdminHierarchyExtendedDao adminHierarchyDao;
    private LiveData<List<AdminHierarchyExtendedEntity>> allCouncil;

    public AdminHierarchyExtendedRepository(Application application)
    {
        CTCDatabase ctcDatabase = CTCDatabase.getInstance(application);
        adminHierarchyDao = ctcDatabase.adminHierarchyExtendedDao();
        allCouncil = adminHierarchyDao.getAllCouncil();

    }

    public void insert(AdminHierarchyExtendedEntity adminHierarchy) {
        new InsertAdminHierarchyAsyncTask(adminHierarchyDao).execute(adminHierarchy);
    }

    public void update(AdminHierarchyExtendedEntity adminHierarchy){
        new UpdateAdminHierarchyAsyncTask(adminHierarchyDao).execute(adminHierarchy);
    }

    public void delete(AdminHierarchyExtendedEntity adminHierarchy){
        new DeleteAdminHierarchyAsyncTask(adminHierarchyDao).execute(adminHierarchy);
    }

    public void deleteAllAdminHierarchy() {
        new DeleteAllAdminHierarchyAsyncTask(adminHierarchyDao).execute();
    }


    //AsyncTask for create new AdminHierarchy
    private static class InsertAdminHierarchyAsyncTask extends AsyncTask<AdminHierarchyExtendedEntity, Void, Void> {

        private AdminHierarchyExtendedDao adminHierarchyDao;

        public InsertAdminHierarchyAsyncTask(AdminHierarchyExtendedDao adminHierarchyDao) {
            this.adminHierarchyDao = adminHierarchyDao;
        }
     @Override
        protected Void doInBackground(AdminHierarchyExtendedEntity... adminHierarchyEntities) {
            adminHierarchyDao.insert(adminHierarchyEntities[0]);
            return null;
        }
    }
    //AsyncTask for update existing AdminHierarchy
    private static class UpdateAdminHierarchyAsyncTask extends AsyncTask<AdminHierarchyExtendedEntity, Void, Void>{

        private AdminHierarchyExtendedDao adminHierarchyDao;

        public UpdateAdminHierarchyAsyncTask(AdminHierarchyExtendedDao adminHierarchyDao) {
            this.adminHierarchyDao = adminHierarchyDao;
        }

        @Override
        protected Void doInBackground(AdminHierarchyExtendedEntity... adminHierarchyEntities) {
            ///
            adminHierarchyDao.update(adminHierarchyEntities[0]);
            return null;
        }
    }
    //AsyncTask for delete existing AdminHierarchy
    private static class DeleteAdminHierarchyAsyncTask extends AsyncTask<AdminHierarchyExtendedEntity, Void, Void>{

        private AdminHierarchyExtendedDao adminHierarchyDao;

        public DeleteAdminHierarchyAsyncTask (AdminHierarchyExtendedDao adminHierarchyDao){
            this.adminHierarchyDao = adminHierarchyDao;
        }

        @Override
        protected Void doInBackground(AdminHierarchyExtendedEntity... adminHierarchyEntities) {
            adminHierarchyDao.delete(adminHierarchyEntities[0]);
            return null;
        }
    }

    //AsyncTask for delete all AdminHierarchy
    private static class DeleteAllAdminHierarchyAsyncTask extends AsyncTask<Void, Void, Void>{

        private AdminHierarchyExtendedDao adminHierarchyDao;

        public DeleteAllAdminHierarchyAsyncTask(AdminHierarchyExtendedDao adminHierarchyDao){
            this.adminHierarchyDao = adminHierarchyDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            adminHierarchyDao.deleteAllAdminHierarchy();
            return null;
        }
    }
    public LiveData<List<AdminHierarchyExtendedEntity>> getAllCouncil() {
        return allCouncil;
    }

    public LiveData<List<AdminHierarchyExtendedEntity>> getDivisionById(String nodeId) {
        return adminHierarchyDao.getDivisionById(nodeId);
    }
    public LiveData<List<AdminHierarchyExtendedEntity>> getWardById(String nodeId) {
        return adminHierarchyDao.getWardById(nodeId);
    }
    public LiveData<List<AdminHierarchyExtendedEntity>> getVillageById(String nodeId) {
        return adminHierarchyDao.getVillageById(nodeId);
    }
}
