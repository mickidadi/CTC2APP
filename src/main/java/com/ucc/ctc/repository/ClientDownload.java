package com.ucc.ctc.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ucc.ctc.apis.ApiService;
import com.ucc.ctc.dao.ClientDao;
import com.ucc.ctc.dao.PatientVisitDao;
import com.ucc.ctc.models.ClientRemote;
import com.ucc.ctc.models.ClientRootRemote;
import com.ucc.ctc.models.ClientVisitRemote;
import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.models.entity.PatientVisitEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientDownload{
    private static ClientDownload instance;
    private ApiService apiServices;
    private ClientDao clientDao;
    private PatientVisitDao visitDao;
    private Executor executor;

    private ClientDownload() {
        // Create a new instance of HttpLoggingInterceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Add the loggingInterceptor to your OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://technolemon.com/")
                .addConverterFactory( GsonConverterFactory.create())
                .client(client)
                .build();
        apiServices = retrofit.create(ApiService.class);
        executor = Executors.newSingleThreadExecutor();
    }

    public static synchronized ClientDownload getInstance() {
        if (instance == null) {
            instance = new ClientDownload();
        }
        return instance;
    }

    public LiveData<ClientRootRemote> getUserRemote(String username, String password) {
        MutableLiveData<ClientRootRemote> data = new MutableLiveData<>();

        // Execute the network request on a background thread
        executor.execute(new Runnable() {
            @Override
            public void run() {
                apiServices.getRemoteClientDownload(username, password).enqueue(new Callback<ClientRootRemote>() {
                    @Override
                    public void onResponse(Call<ClientRootRemote> call, Response<ClientRootRemote> response) {
                        if (response.isSuccessful()) {
                            //data.setValue(response.body());
                            List<ClientRemote> patientList = response.body().getClientInfoList();
                            for (ClientRemote patient : patientList) {
                                ClientEntity patientEntity = new ClientEntity(
                                        patient.getClientId(),
                                        patient.getFirstName(),
                                        patient.getMiddleName(),
                                        patient.getLastName(),
                                        patient.getSex(),
                                        patient.getDateOfBirth(),
                                        patient.getReferenceCode(),
                                        patient.getFacilityId(),
                                        ""
                                );
                                clientDao.insert(patientEntity);

                                for (ClientVisitRemote visit : patient.getVisitInfoList()) {
                                    PatientVisitEntity visitEntity = new PatientVisitEntity(
                                            visit.getPatientId(),
                                            visit.getVisitTypeCode(),
                                            visit.getVisitDate(),
                                            visit.getNumDaysDispensed()
                                    );
                                    visitDao.insert(visitEntity);
                                }
                            }
                        }
                    }
                  @Override
                    public void onFailure(Call<ClientRootRemote> call, Throwable ts) {
                        data.postValue(null);
                    }
                });
            }
        });

        return data;
    }
}

