package com.ucc.ctc.viewsModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ucc.ctc.models.AdminHierarchyDivisionRootRemote;
import com.ucc.ctc.models.entity.AdminHierarchyDivisionEntity;
import com.ucc.ctc.models.entity.AdminHierarchyEntity;
import com.ucc.ctc.repository.AdminHierarchyDivisionRepository;
import com.ucc.ctc.repository.AdminHierarchyRepository;

import java.util.List;

public class AdminHierarchyDivisionViewModel extends AndroidViewModel {
    private AdminHierarchyDivisionRepository repository;
    private LiveData<List<AdminHierarchyDivisionEntity>> allDivision,adminHierarchyDivisionLiveData;

    public AdminHierarchyDivisionViewModel(@NonNull Application application) {
        super(application);
        repository = new AdminHierarchyDivisionRepository(application);
        allDivision = repository.getAllDivision();
    }
    public void insert(AdminHierarchyDivisionEntity adminHierarchy){
        repository.insert(adminHierarchy);
    }
    public void update(AdminHierarchyDivisionEntity adminHierarchy){
        repository.update(adminHierarchy);
    }
    public void delete(AdminHierarchyDivisionEntity adminHierarchy){
        repository.delete(adminHierarchy);
    }
    public void deleteAllAdminHierarchy(){
        repository.deleteAllAdminHierarchy();
    }
    public LiveData<List<AdminHierarchyDivisionEntity>> getAllDivision(){
        return allDivision;
    }

    public void getDivisionById(int nodeId){
        adminHierarchyDivisionLiveData =repository.getDivisionById(nodeId);
    }
    public LiveData<List<AdminHierarchyDivisionEntity>> getDivisionLiveDataLiveData() {
        return adminHierarchyDivisionLiveData;
    }
}
