package com.ucc.ctc.viewsModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ucc.ctc.models.entity.AdminHierarchyEntity;
import com.ucc.ctc.repository.AdminHierarchyRepository;

import java.util.List;

public class AdminHierarchyViewModel extends AndroidViewModel {
    private AdminHierarchyRepository repository;
    private LiveData<List<AdminHierarchyEntity>> allCouncil;
    private MutableLiveData<String> selectedItemKey = new MutableLiveData<>(); // LiveData for the selected item's key
    public AdminHierarchyViewModel(@NonNull Application application) {
        super(application);
        repository = new AdminHierarchyRepository(application);
        allCouncil = repository.getAllCouncil();
    }
    public void insert(AdminHierarchyEntity adminHierarchy){
        repository.insert(adminHierarchy);
    }
    public void update(AdminHierarchyEntity adminHierarchy){
        repository.update(adminHierarchy);
    }
    public void delete(AdminHierarchyEntity adminHierarchy){
        repository.delete(adminHierarchy);
    }
    public void deleteAllAdminHierarchy(){
        repository.deleteAllAdminHierarchy();
    }
    public LiveData<List<AdminHierarchyEntity>> getAllCouncil(){
        return allCouncil;
    }
    public LiveData<List<AdminHierarchyEntity>> getDivisionById(String nodeId) {
       return repository.getDivisionById(nodeId);
    }
    public LiveData<List<AdminHierarchyEntity>> getWardById(String nodeId) {
        return repository.getWardById(nodeId);
    }
    public LiveData<List<AdminHierarchyEntity>> getVillageById(String nodeId) {
        return repository.getVillageById(nodeId);
    }
    public MutableLiveData<String> getSelectedItemKey() {
        return selectedItemKey;
    }

    // Called when an item is selected in the dropdown menu
    public void onItemSelected(String itemKey) {
        selectedItemKey.setValue(itemKey);
    }
}
