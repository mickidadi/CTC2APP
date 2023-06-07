package com.ucc.ctc.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ucc.ctc.dao.ClientDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.entity.ClientEntity;

import java.util.List;

public class DashboardRepository {
    private ClientDao clientDao;
    private LiveData<Integer> clientCount;
    public DashboardRepository(Application application)
    {
        CTCDatabase ctcDatabase = CTCDatabase.getInstance(application);
        clientDao = ctcDatabase.clientDao();
        clientCount = clientDao.getClientCount();

    }

    public LiveData<Integer> getClientCount() {
        return clientCount;
    }
}
