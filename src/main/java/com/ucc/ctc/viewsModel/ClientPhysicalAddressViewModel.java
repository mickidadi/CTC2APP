package com.ucc.ctc.viewsModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.models.entity.ClientPhysicalAddressEntity;
import com.ucc.ctc.repository.ClientPhysicalAddressRepository;
import com.ucc.ctc.repository.ClientRepository;

import java.util.List;

public class ClientPhysicalAddressViewModel extends AndroidViewModel {
    private ClientPhysicalAddressRepository repository;


    public ClientPhysicalAddressViewModel(@NonNull Application application) {
        super(application);

        repository = new ClientPhysicalAddressRepository(application);
    }

    public void insert(ClientPhysicalAddressEntity clientPhysicalAddress){
        repository.insert(clientPhysicalAddress);
    }

    public void update(ClientPhysicalAddressEntity clientPhysicalAddress){
        repository.update(clientPhysicalAddress);
    }

    public void delete(ClientPhysicalAddressEntity clientPhysicalAddress){
        repository.delete(clientPhysicalAddress);
    }
    public LiveData<ClientPhysicalAddressEntity> getClientPhysicalAddressSearch(String clientId){

        return repository.getClientPhysicalAddressSearch(clientId);
    }
}
