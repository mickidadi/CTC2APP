package com.ucc.ctc.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ucc.ctc.apis.ApiService;
import com.ucc.ctc.dao.ClientBiometricDao;
import com.ucc.ctc.dao.ClientDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.*;
import com.ucc.ctc.models.entity.ClientBiometricEntity;
import com.ucc.ctc.models.entity.ClientEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UploadClientRemoteRepository {
    private ClientDao clientDao;
    private ClientBiometricDao clientBioDao;
    private ApiService apiService;
    private List<ClientEntity> clients;

    public UploadClientRemoteRepository(Context context) {
        CTCDatabase database = CTCDatabase.getInstance(context);
        clientDao = database.clientDao();
       // clients = clientDao.getAllClientBio();
        clientBioDao = database.clientBiometricDao();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://technolemon.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public LiveData<List<ClientEntity>> getAllClients() {
        return clientDao.getAllClients();
    }

    public LiveData<UploadClientRemoteResponse> sendDataToAPI() {
        MutableLiveData<UploadClientRemoteResponse> data = new MutableLiveData<>();

        // Perform the database retrieval and API call in the background
        new SendDataAsyncTask(data).execute();

        return data;
    }

    private JSONObject convertToJson(UploadClientPayload payload) {
        JSONObject payloadObj = new JSONObject();

        try {
            payloadObj.put("Session", payload.getSession());
            payloadObj.put("SessionStatus", payload.getSessionStatus());

            JSONArray dataArray = new JSONArray();

            for (UploadClientTableData data : payload.getTableData()) {
                JSONObject dataObj = new JSONObject();
                dataObj.put("TableName", data.getTableName());

                JSONArray recordsArray = new JSONArray();

                for (Record record : data.getRecords()) {
                    if (record instanceof UploadClientRecord) {
                        UploadClientRecord clientRecord = (UploadClientRecord) record;
                        JSONObject recordObj = new JSONObject();
                        recordObj.put("ClientId", clientRecord.getClientId());
                        recordObj.put("FirstName", clientRecord.getFirstName());
                        recordObj.put("MiddleName", clientRecord.getMiddleName());
                        recordObj.put("LastName", clientRecord.getLastName());
                        recordsArray.put(recordObj);
                    } else if (record instanceof UploadClientBiometricRecord) {
                        UploadClientBiometricRecord clientBiometry = (UploadClientBiometricRecord) record;
                        JSONObject recordObj = new JSONObject();
                        recordObj.put("ClientBiometryId", clientBiometry.getClientBiometryId());
                        recordObj.put("BiometricSpecificationId", clientBiometry.getBiometricSpecificationId());
                        recordObj.put("Biometry", clientBiometry.getBiometricTemplate());
                        recordObj.put("ClientId", clientBiometry.getClientId());
                        recordsArray.put(recordObj);
                    }
                    // Handle other record types if needed
                }

                dataObj.put("Records", recordsArray);
                dataArray.put(dataObj);
            }

            payloadObj.put("Data", dataArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return payloadObj;
    }

    private class SendDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private MutableLiveData<UploadClientRemoteResponse> data;

        public SendDataAsyncTask(MutableLiveData<UploadClientRemoteResponse> data) {
            this.data = data;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UploadClientPayload payload = new UploadClientPayload();
            payload.setSession("AFB903FAC111894");
            payload.setSessionStatus(1);

            List<UploadClientTableData> tableDataList = new ArrayList<>();

            // Retrieve data from Room database
            List<ClientEntity> clients =clientDao.getAllClientBio();
            // tblClients
            UploadClientTableData tblClients = new UploadClientTableData();
            tblClients.setTableName("tblClients");

            List<Record> clientRecords = new ArrayList<>();

            for (ClientEntity client : clients) {
                UploadClientRecord client1 = new UploadClientRecord();
                client1.setClientId(client.getClientId());
                client1.setFirstName(client.getFirstName());
                client1.setMiddleName(client.getMiddleName());
                client1.setLastName(client.getLastName());
                clientRecords.add(client1);
            }

            tblClients.setRecords(clientRecords);
            tableDataList.add(tblClients);

            // tblClient Biometries
            UploadClientTableData tblClientBiometries = new UploadClientTableData();
            tblClientBiometries.setTableName("tblClientBiometries");

            List<Record> clientBiometries = new ArrayList<>();
            List<ClientBiometricEntity> biometries = clientBioDao.getAllClientBiometric();

            for (ClientBiometricEntity clientbio : biometries) {
                UploadClientBiometricRecord biometry1 = new UploadClientBiometricRecord();
                biometry1.setClientBiometryId(clientbio.getId());
                biometry1.setBiometricSpecificationId(2);
                byte[] byteArray1 = {80, 65, 78, 75, 65, 74};
                biometry1.setBiometricTemplate(clientbio.getBiometricTemplate());
                biometry1.setClientId(clientbio.getClientId());
                clientBiometries.add(biometry1);
            }
            tblClientBiometries.setRecords(clientBiometries);
            tableDataList.add(tblClientBiometries);

            payload.setTableData(tableDataList);
            JSONObject payloaddata = convertToJson(payload);

            apiService.sendData(payloaddata).enqueue(new Callback<UploadClientRemoteResponse>() {
                @Override
                public void onResponse(Call<UploadClientRemoteResponse> call, Response<UploadClientRemoteResponse> response) {
                    if (response.isSuccessful()) {
                        data.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<UploadClientRemoteResponse> call, Throwable ts) {
                    data.setValue(null);
                }
            });

            return null;
        }
    }
}
