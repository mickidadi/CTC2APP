package com.ucc.ctc.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ucc.ctc.dao.ClientBiometricDao;
import com.ucc.ctc.dao.ClientDao;
import com.ucc.ctc.dao.FacilityDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.Record;
import com.ucc.ctc.models.UploadClientBiometricRecord;
import com.ucc.ctc.models.UploadClientPayload;
import com.ucc.ctc.models.UploadClientRecord;
import com.ucc.ctc.models.UploadClientTableData;
import com.ucc.ctc.models.entity.ClientBiometricEntity;
import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.models.entity.FacilityEntity;

import java.util.ArrayList;
import java.util.List;

public class UploadClientRepository {
    private ClientDao clientDao;
    private ClientBiometricDao clientBiometryDao;
    private CTCDatabase db;
    private MutableLiveData<UploadClientPayload> payloadLiveData;

    public UploadClientRepository(Context context) {
          db = CTCDatabase.getInstance(context);
        clientDao = db.clientDao();
        clientBiometryDao = db.clientBiometricDao();

    }

    public LiveData<List<ClientEntity>> getAllClients() {
        return clientDao.getAllClients();
    }
   public LiveData<List<ClientBiometricEntity>> getAllClientBiometries() {
        return clientBiometryDao.getAllClientBiometricData();
    }
    public MutableLiveData<UploadClientPayload> getPayloadLiveData() {
        if (payloadLiveData == null) {
            payloadLiveData = new MutableLiveData<>();
        }
        return payloadLiveData;
    }


}

