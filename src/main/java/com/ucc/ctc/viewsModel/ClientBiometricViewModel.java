package com.ucc.ctc.viewsModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ucc.ctc.models.entity.ClientBiometricEntity;
import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.repository.ClientBiometricRepository;

import java.util.List;

public class ClientBiometricViewModel extends AndroidViewModel {
    private ClientBiometricRepository repository;
    private LiveData<List<ClientBiometricEntity>> allClientBiometrics;

    public ClientBiometricViewModel(@NonNull Application application) {
        super(application);

        repository = new ClientBiometricRepository(application);
        allClientBiometrics = repository.getAllClientBiometric();

    }

    public void insert(ClientBiometricEntity clientBiometric){
        repository.insert(clientBiometric);
    }

    public void update(ClientBiometricEntity clientBiometric){
        repository.update(clientBiometric);
    }

    public void delete(ClientBiometricEntity clientBiometric){
        repository.delete(clientBiometric);
    }

    public void deleteAllClientBiometric(){
        repository.deleteAllClientBiometric();
    }

    public LiveData<List<ClientBiometricEntity>> getClientBiometricSearch(String clientId){

        return repository.getAllClientBiometricSearch(clientId);
    }
    public LiveData<List<ClientBiometricEntity>> getAllClientBiometric(){

        return repository.getAllClientBiometric();
    }
}
