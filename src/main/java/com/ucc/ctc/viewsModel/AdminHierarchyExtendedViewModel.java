package com.ucc.ctc.viewsModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.ucc.ctc.models.entity.AdminHierarchyExtendedEntity;
import com.ucc.ctc.repository.AdminHierarchyExtendedRepository;
import java.util.List;

public class AdminHierarchyExtendedViewModel extends AndroidViewModel {
    private AdminHierarchyExtendedRepository repository;
    private LiveData<List<AdminHierarchyExtendedEntity>> allCouncil;
    public AdminHierarchyExtendedViewModel(@NonNull Application application) {
        super(application);
        repository = new AdminHierarchyExtendedRepository(application);
        allCouncil = repository.getAllCouncil();
    }
    public void insert(AdminHierarchyExtendedEntity adminHierarchy){
        repository.insert(adminHierarchy);
    }
    public void update(AdminHierarchyExtendedEntity adminHierarchy){
        repository.update(adminHierarchy);
    }
    public void delete(AdminHierarchyExtendedEntity adminHierarchy){
        repository.delete(adminHierarchy);
    }
    public void deleteAllAdminHierarchy(){
        repository.deleteAllAdminHierarchy();
    }
    public LiveData<List<AdminHierarchyExtendedEntity>> getAllCouncil(){
        return allCouncil;
    }
    public LiveData<List<AdminHierarchyExtendedEntity>> getDivisionById(String nodeId) {
       return repository.getDivisionById(nodeId);
    }
    public LiveData<List<AdminHierarchyExtendedEntity>> getWardById(String nodeId) {
        return repository.getWardById(nodeId);
    }
    public LiveData<List<AdminHierarchyExtendedEntity>> getVillageById(String nodeId) {
        return repository.getVillageById(nodeId);
    }
}
