package com.ucc.ctc.viewsModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ucc.ctc.models.entity.ClientBiometricEntity;
import com.ucc.ctc.models.entity.PatientEntity;
import com.ucc.ctc.repository.ClientBiometricRepository;
import com.ucc.ctc.repository.PatientRepository;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {
    private PatientRepository repository;
    private LiveData<List<PatientEntity>> allPatient;

    public PatientViewModel(@NonNull Application application) {
        super(application);

        repository = new PatientRepository(application);
        allPatient = repository.getAllPatient();

    }

    public void insert(PatientEntity patientEntity){
        repository.insert(patientEntity);
    }

    public void update(PatientEntity patientEntity){
        repository.update(patientEntity);
    }

    public void delete(PatientEntity patientEntity){
        repository.delete(patientEntity);
    }

    public void deleteAllPatient(){
        //repository.;
    }

    public LiveData<List<PatientEntity>> getPatientSearch(String clientId){

       // return repository.(clientId);
        return null;
    }
    public LiveData<List<PatientEntity>> getAllPatient(){

        return repository.getAllPatient();
    }
}
