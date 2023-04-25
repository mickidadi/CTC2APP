package com.ucc.ctc.viewsModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.ucc.ctc.models.entity.ClientTreatmentSupporterEntity;
import com.ucc.ctc.repository.ClientTreatmentSupporterRepository;

import java.util.List;

public class ClientTreatmentSupporterViewModel extends AndroidViewModel {
    private ClientTreatmentSupporterRepository repository;


    public ClientTreatmentSupporterViewModel(@NonNull Application application) {
        super(application);

        repository = new ClientTreatmentSupporterRepository(application);
    }

    public void insert(ClientTreatmentSupporterEntity clientTreatmentSupporter){
        repository.insert(clientTreatmentSupporter);
    }

    public void update(ClientTreatmentSupporterEntity clientTreatmentSupporter){
        repository.update(clientTreatmentSupporter);
    }

    public void delete(ClientTreatmentSupporterEntity clientTreatmentSupporter){
        repository.delete(clientTreatmentSupporter);
    }
    public LiveData<ClientTreatmentSupporterEntity> getClientTreatmentSupporterSearch(String clientId){

        return repository.getClientSupporterSearch(clientId);
    }
}
