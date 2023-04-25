package com.ucc.ctc.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ucc.ctc.dao.AdminHierarchyDao;
import com.ucc.ctc.dao.AdminHierarchyDivisionDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.entity.AdminHierarchyDivisionEntity;
import com.ucc.ctc.models.entity.AdminHierarchyEntity;

import java.util.List;

public class AdminHierarchyDivisionRepository {
    private AdminHierarchyDivisionDao adminHierarchyDivisionDao;
    private LiveData<List<AdminHierarchyDivisionEntity>> allDivision;

    public AdminHierarchyDivisionRepository(Application application)
    {
        CTCDatabase ctcDatabase = CTCDatabase.getInstance(application);
        adminHierarchyDivisionDao = ctcDatabase.adminHierarchyDivisionDao();
        allDivision = adminHierarchyDivisionDao.getAllDivision();

    }

    public void insert(AdminHierarchyDivisionEntity adminHierarchy) {
        new InsertAdminHierarchyAsyncTask(adminHierarchyDivisionDao).execute(adminHierarchy);
    }

    public void update(AdminHierarchyDivisionEntity adminHierarchy){
        new UpdateAdminHierarchyAsyncTask(adminHierarchyDivisionDao).execute(adminHierarchy);
    }

    public void delete(AdminHierarchyDivisionEntity adminHierarchy){
        new DeleteAdminHierarchyAsyncTask(adminHierarchyDivisionDao).execute(adminHierarchy);
    }

    public void deleteAllAdminHierarchy() {
        new DeleteAllAdminHierarchyAsyncTask(adminHierarchyDivisionDao).execute();
    }


    //AsyncTask for create new AdminHierarchy
    private static class InsertAdminHierarchyAsyncTask extends AsyncTask<AdminHierarchyDivisionEntity, Void, Void> {

        private AdminHierarchyDivisionDao adminHierarchyDao;

        public InsertAdminHierarchyAsyncTask(AdminHierarchyDivisionDao adminHierarchyDao) {
            this.adminHierarchyDao = adminHierarchyDao;
        }


        @Override
        protected Void doInBackground(AdminHierarchyDivisionEntity... adminHierarchyEntities) {
            adminHierarchyDao.insert(adminHierarchyEntities[0]);
            return null;
        }
    }

    //AsyncTask for update existing AdminHierarchy
    private static class UpdateAdminHierarchyAsyncTask extends AsyncTask<AdminHierarchyDivisionEntity, Void, Void>{

        private AdminHierarchyDivisionDao adminHierarchyDao;

        public UpdateAdminHierarchyAsyncTask(AdminHierarchyDivisionDao adminHierarchyDao) {
            this.adminHierarchyDao = adminHierarchyDao;
        }

        @Override
        protected Void doInBackground(AdminHierarchyDivisionEntity... adminHierarchyEntities) {
            ///
            adminHierarchyDao.update(adminHierarchyEntities[0]);
            return null;
        }
    }

    //AsyncTask for delete existing AdminHierarchy
    private static class DeleteAdminHierarchyAsyncTask extends AsyncTask<AdminHierarchyDivisionEntity, Void, Void>{

        private AdminHierarchyDivisionDao adminHierarchyDao;

        public DeleteAdminHierarchyAsyncTask (AdminHierarchyDivisionDao adminHierarchyDao){
            this.adminHierarchyDao = adminHierarchyDao;
        }

        @Override
        protected Void doInBackground(AdminHierarchyDivisionEntity... adminHierarchyEntities) {
            adminHierarchyDao.delete(adminHierarchyEntities[0]);
            return null;
        }
    }

    //AsyncTask for delete all AdminHierarchy
    private static class DeleteAllAdminHierarchyAsyncTask extends AsyncTask<Void, Void, Void>{

        private AdminHierarchyDivisionDao adminHierarchyDao;

        public DeleteAllAdminHierarchyAsyncTask(AdminHierarchyDivisionDao adminHierarchyDao){
            this.adminHierarchyDao = adminHierarchyDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            adminHierarchyDao.deleteAllAdminHierarchy();
            return null;
        }
    }
    public LiveData<List<AdminHierarchyDivisionEntity>> getAllDivision() {
        return allDivision;
    }
    public LiveData<List<AdminHierarchyDivisionEntity>> getDivisionById(int nodeId) {
        return adminHierarchyDivisionDao.getDivisionById(nodeId);
    }

}
