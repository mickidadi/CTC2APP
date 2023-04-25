package com.ucc.ctc.viewsModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.repository.ClientRepository;

import java.util.List;

public class ClientViewModel extends AndroidViewModel {
    private ClientRepository repository;
    private LiveData<List<ClientEntity>> allClients;
    private LiveData<List<String>> allClientName;

    public ClientViewModel(@NonNull Application application) {
        super(application);

        repository = new ClientRepository(application);
        allClients = repository.getAllClients();
        allClientName = repository.getAllClientName();

    }

    public void insert(ClientEntity client){
        repository.insert(client);
    }

    public void update(ClientEntity client){
        repository.update(client);
    }

    public void delete(ClientEntity client){
        repository.delete(client);
    }

    public void deleteAllClients(){
        repository.deleteAllClients();
    }

    public LiveData<List<ClientEntity>> getAllClients(){
        return allClients;
    }
    public LiveData<List<String>> getAllClientName(){
        return allClientName;
    }
    public LiveData<List<ClientEntity>> getClientSearch(String clientId,String name){

        return repository.getAllClientSearch(clientId,name);
    }
    /*public void getClientSearch(String clientId,String name) {
        if (this.allClients!= null) {

        }
        allClients = repository.getAllClientSearch(clientId,name);
    }*/
}
