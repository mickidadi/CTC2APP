package com.ucc.ctc.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ucc.ctc.apis.ApiService;
import com.ucc.ctc.dao.ClientBiometricDao;
import com.ucc.ctc.dao.ClientDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.Record;
import com.ucc.ctc.models.UploadClientBiometricRecord;
import com.ucc.ctc.models.UploadClientPayload;
import com.ucc.ctc.models.UploadClientRecord;
import com.ucc.ctc.models.UploadClientRemoteResponse;
import com.ucc.ctc.models.UploadClientTableData;
import com.ucc.ctc.models.entity.ClientBiometricEntity;
import com.ucc.ctc.models.entity.ClientEntity;

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

    public LiveData<UploadClientRemoteResponse> sendDataToAPI(String url) {
        MutableLiveData<UploadClientRemoteResponse> data = new MutableLiveData<>();
        // Set Url to upload data
        getSendData(url);
        // Perform the database retrieval and API call in the background
        new SendDataAsyncTask(data, clientDao, clientBioDao, apiService).execute();

        return data;
    }

    private static JsonNode convertToJson(UploadClientPayload payload) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payloadObj = objectMapper.createObjectNode();

        // Rest of the code remains unchanged
        // ...

        return payloadObj;
    }

    private void getSendData(String url) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + url + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    private static class SendDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private MutableLiveData<UploadClientRemoteResponse> data;
        private ClientDao clientDao;
        private ClientBiometricDao clientBioDao;
        private ApiService apiService;

        public SendDataAsyncTask(MutableLiveData<UploadClientRemoteResponse> data, ClientDao clientDao, ClientBiometricDao clientBioDao, ApiService apiService) {
            this.data = data;
            this.clientDao = clientDao;
            this.clientBioDao = clientBioDao;
            this.apiService = apiService;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UploadClientPayload payload = new UploadClientPayload();
            payload.setSession("AFB903FAC111894");
            payload.setSessionStatus(1);

            List<UploadClientTableData> tableDataList = new ArrayList<>();

            // Retrieve Data from Room Database
            List<ClientEntity> clients = clientDao.getAllClientBio();
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
            //End Client Data
            //tblClient Biometries
            UploadClientTableData tblClientBiometries = new UploadClientTableData();
            tblClientBiometries.setTableName("tblClientBiometries");

            List<Record> clientBiometries = new ArrayList<>();
            List<ClientBiometricEntity> biometries = clientBioDao.getAllClientBiometric();

            for (ClientBiometricEntity clientbio : biometries) {
                UploadClientBiometricRecord biometry1 = new UploadClientBiometricRecord();
                biometry1.setClientBiometryId(clientbio.getId());
                biometry1.setBiometricSpecificationId(2);
                biometry1.setBiometricTemplate(clientbio.getBiometricTemplate());
                biometry1.setClientId(clientbio.getClientId());
                clientBiometries.add(biometry1);
            }
            tblClientBiometries.setRecords(clientBiometries);
            tableDataList.add(tblClientBiometries);
            //End Biometric Client Data

            payload.setTableData(tableDataList);
            JsonNode payloaddata = convertToJson(payload);
            Log.v( "dataclient",  payload.toString());
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
