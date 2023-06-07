package com.ucc.ctc.viewsModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.repository.ClientRepository;
import com.ucc.ctc.repository.DashboardRepository;

import java.util.List;

public class DashboardViewModel extends AndroidViewModel {
    private DashboardRepository repository;
    private LiveData<Integer> clientCount;

    public DashboardViewModel(@NonNull Application application) {
        super(application);

        repository = new DashboardRepository(application);
        clientCount = repository.getClientCount();

    }
    public LiveData<Integer> getClientCount(){
       return clientCount;
    }


}
