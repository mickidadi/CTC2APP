package com.ucc.ctc.viewsModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ucc.ctc.models.entity.PatientVisitEntity;
import com.ucc.ctc.repository.PatientVisitRepository;

import java.util.List;

public class PatientVisitViewModel extends AndroidViewModel {
    private PatientVisitRepository repository;
    private LiveData<List<PatientVisitEntity>> allVisits;

    public PatientVisitViewModel(@NonNull Application application) {
        super(application);
        repository = new PatientVisitRepository(application);
        allVisits = repository.getAllVisits();
    }
    public void insert(PatientVisitEntity visit){
        repository.insert(visit);
    }

    public void update(PatientVisitEntity visit){
        repository.update(visit);
    }

    public void delete(PatientVisitEntity visit){
        repository.delete(visit);
    }

    public void deleteAllPatientVisit(){
        repository.deleteAllVisit();
    }

    public LiveData<List<PatientVisitEntity>> getAllClients(){
        return allVisits;
    }

}
