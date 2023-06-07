package com.ucc.ctc.viewsModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ucc.ctc.models.FacilitySearchResponse;
import com.ucc.ctc.repository.FacilitySearchRepository;

public class FacilitySearchViewModel extends ViewModel {

    // public class UserViewModel extends ViewModel {
    private LiveData<FacilitySearchResponse> facilityLiveData;
    private FacilitySearchRepository facilityRepository;

    public FacilitySearchViewModel() {

        facilityRepository = FacilitySearchRepository.getInstance();
    }

    public void init(String url,String hfrId) {
       /* if (this.facilityLiveData != null) {
            return;
        }*/
        facilityLiveData = facilityRepository.getFacilityById( url,hfrId );
        Log.v("Test Mickidadi", "View Model Responds" +hfrId);
    }

    public LiveData<FacilitySearchResponse> getFacilityLiveData() {
        return facilityLiveData;
    }
}