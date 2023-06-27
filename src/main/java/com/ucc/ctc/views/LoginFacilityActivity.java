package com.ucc.ctc.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.ucc.ctc.R;
import com.ucc.ctc.models.entity.UserEntity;
import com.ucc.ctc.repository.LoginFacilityRepository;
import com.ucc.ctc.viewsModel.FacilityViewModel;
import com.ucc.ctc.viewsModel.LoginFacilityViewModel;
import com.ucc.ctc.viewsModel.UserRemoteViewModel;


public class LoginFacilityActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private FacilityViewModel facilityViewModel;
    private UserRemoteViewModel userViewModels;
    private LoginFacilityViewModel loginViewModel;
    LoginFacilityRepository loginRepository;
    // Private Spinner Spinner Facility;
    private SearchableSpinner facilitySpinner;
    private String username;
    private String password;
    private String facilityName;
    private String url;
    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_facility);
        loginRepository = new LoginFacilityRepository(getApplication());
        sharedPreferences = getApplicationContext().getSharedPreferences("facilitySession", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        initializeViews();
        setListeners();
        initializeViewModels();
        setupFacilitySpinner();
    }

    private void initializeViews() {
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        facilitySpinner = findViewById(R.id.facilityLoginSpinner);
    }

    private void setListeners() {
        TextView facilitySetting = findViewById(R.id.facilityRegisterAccount);
        facilitySetting.setOnClickListener(view -> {
            Intent intent = new Intent(LoginFacilityActivity.this, FacilitySettingActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(view -> {
            username = usernameEditText.getText().toString().trim();
            password = passwordEditText.getText().toString().trim();
            if (checkAllFields()) {
                facilityName = facilitySpinner.getSelectedItem().toString();
                setSessionData();

                loginViewModel.login(username, password).observe(LoginFacilityActivity.this, user -> {
                    if (user == null) {
                        userViewModels = new ViewModelProvider(LoginFacilityActivity.this).get(UserRemoteViewModel.class);
                        userViewModels.getRemoteUser(username, password, url);
                        userViewModels.getUserLiveData().observe(LoginFacilityActivity.this, userProfile -> {
                            if (userProfile.getCode() == 200) {
                                String fullName = userProfile.getFullName();
                                String pass = userProfile.getPassword();
                                String loginName = userProfile.getUsername();
                                String facilityId = userProfile.getFacilityId();
                                if (!TextUtils.isEmpty(loginName) || !TextUtils.isEmpty(fullName) || !TextUtils.isEmpty(pass)) {
                                    UserEntity userData = new UserEntity(fullName, loginName, pass, facilityId);
                                    loginRepository.insert(userData);
                                    Intent intent = new Intent(LoginFacilityActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                                Toast.makeText(LoginFacilityActivity.this, "Remote user found " + userProfile.getFullName(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginFacilityActivity.this, "Remote user Not found " + userProfile.getUsername(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Intent intent = new Intent(LoginFacilityActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void initializeViewModels() {
        facilityViewModel = new ViewModelProvider(this).get(FacilityViewModel.class);
        loginViewModel = new ViewModelProvider(this).get(LoginFacilityViewModel.class);
    }

    private void setupFacilitySpinner() {
        facilitySpinner.setOnItemSelectedListener(this);
        facilityViewModel.getAllFacilityName().observe(this, facilityNames -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(LoginFacilityActivity.this, android.R.layout.simple_spinner_dropdown_item, facilityNames);
            facilitySpinner.setTitle("Select Facility");
            facilitySpinner.setAdapter(adapter);
        });
    }

    private boolean checkAllFields() {
        boolean isValid = true;
        if (username.isEmpty()) {
            usernameEditText.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        if (password.isEmpty()) {
            passwordEditText.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        TextView errorText = (TextView) facilitySpinner.getSelectedView();
        if (facilitySpinner.getCount() == 0) {
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText(getString(R.string.error_field_required));
            isValid = false;
        }
        return isValid;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void setSessionData() {
        editor.putString("Name", facilityName);
        editor.putString("facilityNamesession", facilityName);
        String[] facilityId = facilityName.split(" ");
        String facilityIdsession = "";
        if (facilityId.length > 0) {
            facilityIdsession = facilityId[0];
            editor.putString("facilityIdsession", facilityIdsession);
            facilityViewModel.getFacilityById(facilityIdsession).observe(this, facilityEntity -> {
                url = facilityEntity.getCtcWebUrl();
                editor.putString("facilityUrl", url);
                Log.d("Inside Facility", " IP :" + url);
                editor.apply();
            });
        }else{
            editor.apply();
        }
        Log.d("My Activity", " IP :" + url + " facilityIdsession" + facilityIdsession);

    }
}
