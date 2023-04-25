package com.ucc.ctc.viewsModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ucc.ctc.models.ClientRootRemote;
import com.ucc.ctc.repository.ClientDownloadRepository;
public class ClientDownloadViewModel extends ViewModel {
    private LiveData<ClientRootRemote> clientLiveData;
    private ClientDownloadRepository clientRepository;

    public ClientDownloadViewModel() {
        clientRepository = ClientDownloadRepository.getInstance();
    }
    public void getRemoteClient(String username,String password) {
        if (this.clientLiveData!= null) {

        }
        clientLiveData = clientRepository.getRemoteClient(username,password);
    }
    public LiveData<ClientRootRemote> getClientLiveData() {
        return clientLiveData;
    }
}
