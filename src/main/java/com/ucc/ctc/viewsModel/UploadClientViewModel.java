package com.ucc.ctc.viewsModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ucc.ctc.models.UploadClientRemoteResponse;
import com.ucc.ctc.models.entity.ClientBiometricEntity;
import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.repository.UploadClientRemoteRepository;
import com.ucc.ctc.repository.UploadClientRepository;

import java.util.List;

public class UploadClientViewModel extends AndroidViewModel {
    private UploadClientRepository repository;
    private UploadClientRemoteRepository remoteRepository;
    private LiveData<List<ClientBiometricEntity>> allClientBiometrics;
    private LiveData<List<ClientEntity>> allClients;
    private LiveData<UploadClientRemoteResponse> payloadLiveData;

    public UploadClientViewModel(@NonNull Application application) {
        super(application);
        repository = new UploadClientRepository(application);
        remoteRepository = new UploadClientRemoteRepository(application);
        //allClientBiometrics = repository.getAllClientBiometricEntities();
       // allClients = repository.getAllClientEntities();
    }

    public LiveData<List<ClientBiometricEntity>> getAllClientBiometricEntities() {
        return allClientBiometrics;
    }

    public LiveData<List<ClientEntity>> getAllClientEntities() {
        return allClients;
    }

    public LiveData<UploadClientRemoteResponse> sendDataToAPI(String url) {
        if (payloadLiveData == null) {
            payloadLiveData = remoteRepository.sendDataToAPI(url);
        }
        return payloadLiveData;
    }
}
