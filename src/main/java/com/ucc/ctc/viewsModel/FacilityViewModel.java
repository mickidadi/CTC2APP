package com.ucc.ctc.viewsModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ucc.ctc.models.entity.FacilityEntity;
import com.ucc.ctc.repository.FacilityRepository;

import java.util.List;

public class FacilityViewModel extends AndroidViewModel {
    private FacilityRepository repository;
    private LiveData<List<FacilityEntity>> allFacilities;
    private final LiveData<List<String>> allFacilityName;
    private LiveData<FacilityEntity> facilityId;
    public FacilityViewModel(@NonNull Application application) {
        super(application);

        repository = new FacilityRepository(application);
        allFacilities = repository.getAllFacilities();
        allFacilityName = repository.getAllFacilityName();
    }

    public void insert(FacilityEntity facility){
        repository.insert(facility);
    }

    public void update(FacilityEntity facility){
        repository.update(facility);
    }

    public void delete(FacilityEntity facility){
        repository.delete(facility);
    }

    public void deleteAllFacility(){
        repository.deleteAllFacility();
    }

    public LiveData<List<FacilityEntity>> getAllFacilities(){
        return allFacilities;
    }
    public LiveData<List<String>> getAllFacilityName(){
        return allFacilityName;
    }


    public LiveData<FacilityEntity> getFacilityById(String hfrId) {
        if (hfrId != null) {
            facilityId = repository.getFacilityById(hfrId);
        }
        return facilityId;
    }
}
