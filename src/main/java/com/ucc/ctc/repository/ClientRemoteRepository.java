
 package com.ucc.ctc.repository;

 import androidx.lifecycle.LiveData;
 import androidx.lifecycle.MutableLiveData;

 import com.ucc.ctc.apis.ApiService;
 import com.ucc.ctc.models.UserProfile;

 import okhttp3.OkHttpClient;
 import okhttp3.logging.HttpLoggingInterceptor;
 import retrofit2.Call;
 import retrofit2.Callback;
 import retrofit2.Response;
 import retrofit2.Retrofit;
 import retrofit2.converter.gson.GsonConverterFactory;

public class ClientRemoteRepository {
    private static ClientRemoteRepository instance;
    private ApiService apiServices;

    private ClientRemoteRepository() {

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
    }

    public static synchronized ClientRemoteRepository getInstance() {
        if (instance == null) {
            instance = new ClientRemoteRepository();
        }
        return instance;
    }
    public LiveData<UserProfile> getUserRemote(String username, String password) {
        MutableLiveData<UserProfile> data = new MutableLiveData<>();
        apiServices.getRemoteUser(username,password).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable ts) {
                data.setValue(null);
            }
        });
        return data;
    }
}
