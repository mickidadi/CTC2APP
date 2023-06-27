
package com.ucc.ctc.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ucc.ctc.apis.ApiService;
import com.ucc.ctc.dao.ClientDao;
import com.ucc.ctc.dao.PatientVisitDao;
import com.ucc.ctc.models.ClientRootRemote;
import com.ucc.ctc.models.UserProfile;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientDownloadRepository {
    private static ClientDownloadRepository instance;
    private ApiService apiServices;
    private ClientDao clientDao;
    private PatientVisitDao visitDao;

    private ClientDownloadRepository() {

        // Create a new instance of HttpLoggingInterceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

// Add the loggingInterceptor to your OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.45.1.174/")
                .addConverterFactory( GsonConverterFactory.create())
                .client(client)
                .build();
        apiServices = retrofit.create(ApiService.class);
    }

    public static synchronized ClientDownloadRepository getInstance() {
        if (instance == null) {
            instance = new ClientDownloadRepository();
        }
        return instance;
    }
    public LiveData<ClientRootRemote> getRemoteClient12(String username, String password) {
        MutableLiveData<ClientRootRemote> data = new MutableLiveData<>();
        apiServices.getRemoteClientDownload(username,password).enqueue(new Callback<ClientRootRemote>() {
            @Override
            public void onResponse(Call<ClientRootRemote> call, Response<ClientRootRemote> response) {
                if (response.isSuccessful()) {
                     data.setValue(response.body());
                  /* List<ClientRemote> patientList =response.body().getClientInfoList();
                     for (ClientRemote patient : patientList) {
                         Log.v("Welcome","Message IN "+patient.getClientId());
                        ClientEntity patientEntity = new ClientEntity(
                                patient.getClientId(),
                                patient.getFirstName(),
                                patient.getMiddleName(),
                                patient.getLastName(),
                                patient.getSex(),
                                patient.getDateOfBirth(),
                                patient.getReferenceCode(),
                                patient.getFacilityId()
                        );
                        Log.v("Welcome","Message IN "+patientEntity.getFirstName());
                        //clientDao.insert(patientEntity);

                        for (ClientVisitRemote visit : patient.getVisitInfoList()) {
                            PatientVisitEntity visitEntity = new PatientVisitEntity(
                                    visit.getPatientId(),
                                    visit.getVisitTypeCode(),
                                    visit.getVisitDate(),
                                    visit.getNumDaysDispensed()
                            );
                          visitDao.insert(visitEntity);
                        }
                    }*/
                }
                }
            @Override
            public void onFailure(Call<ClientRootRemote> call, Throwable ts) {
                data.setValue(null);
            }
        });
        return data;
    }
    public LiveData<ClientRootRemote> getRemoteClient(String username, String password, String url) {

        MutableLiveData<ClientRootRemote> data = new MutableLiveData<>();
        // Create a new instance of HttpLoggingInterceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Add the loggingInterceptor to your OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+url)
                .addConverterFactory( GsonConverterFactory.create())
                .client(client)
                .build();
        apiServices = retrofit.create(ApiService.class);
        apiServices.getRemoteClientDownload(username,password).enqueue(new Callback<ClientRootRemote>() {
            @Override
            public void onResponse(Call<ClientRootRemote> call, Response<ClientRootRemote> response) {
                Log.v("Test ", "Respond " +response.body());
                if (response.body() != null) {
                    data.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<ClientRootRemote> call, Throwable t) {
                data.setValue(null);
                Log.v("Failed Login ", " View Repository " );
            }
        });
        Log.v("Login Data", "View Repository");
        return data;
    }
}
