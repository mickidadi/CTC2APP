package com.ucc.ctc.views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.ucc.ctc.R;
import com.ucc.ctc.models.LoggedInUserView;
import com.ucc.ctc.viewsModel.ClientViewModel;
import com.ucc.ctc.viewsModel.FacilityViewModel;
import com.ucc.ctc.viewsModel.LoginFormState;
import com.ucc.ctc.viewsModel.LoginResult;
import com.ucc.ctc.viewsModel.LoginViewModel;

import java.util.List;

import static android.os.SystemClock.sleep;


public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private LoginViewModel loginViewModel;
    SharedPreferences sharedpreferences;
    private String facilityName="facilityName";
    private FacilityViewModel facilityViewModel;
    private ClientViewModel clientViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_login);
        Spinner facility_spinner=findViewById(R.id.facilityLoginSpinner);
        TextView facilitySetting=findViewById( R.id.facilityRegisterAccount );
        //Button facilityData=findViewById( R.id.facilityRegisterAccount);
        //Open FACILITY SETTING WINDOW
        facilitySetting.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent =new Intent(LoginActivity.this,FacilitySettingActivity.class);
                startActivity(intent);
            }
        } );
        //END SETTING FACILITY
         // Intent intent =new Intent();
        //GET VALUE FROM  FACILITY ACTIVITY
        //Bundle bundle = getIntent().getExtras();
        // getting the data which is stored in shared preferences.
       // sharedpreferences = getSharedPreferences("FacilityLogin", Context.MODE_PRIVATE);

        // in shared prefs inside het string method
        // we are passing key value as EMAIL_KEY and
        // default value is
        // set to null if not present.
       // String name = sharedpreferences.getString(facilityName, null);

        //String name = bundle.getString("facilityName");
        //END FACILITY DATA INFORMATION
        // Spinner click listener
        facility_spinner.setOnItemSelectedListener( this );

       /* facilityViewModel = new ViewModelProvider(this).get( FacilityViewModel.class);
        facilityViewModel.getAllFacilities().observe(this, new Observer<List<Facility>>() {
            @Override
            public void onChanged(List<Facility> facilities) {
                Log.v("MESSAGE","FACILITY DATA"+facilities.toString());
            }
        });*/


     /*   // Create an instance of the ViewModel
        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);

// Observe the LiveData object and update the adapter whenever the data changes
        clientViewModel.getAllClientName().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> clientNames) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_spinner_dropdown_item, clientNames);
                facility_spinner.setAdapter(adapter);
            }
        });*/
        //Start Create an instance of the ViewModel
        facilityViewModel = new ViewModelProvider(this).get(FacilityViewModel.class);

// Observe the LiveData object and update the adapter whenever the data changes
        facilityViewModel.getAllFacilityName().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> facilityNames) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_spinner_dropdown_item, facilityNames);
                facility_spinner.setAdapter(adapter);
            }
        });
        //End find facility
    /* loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);*/
        loginViewModel = ViewModelProviders.of(this)
                .get(LoginViewModel.class);
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });
        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);
                //Complete and destroy login activity once
                startActivity(new Intent(LoginActivity.this, MainActivity.class));

                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sleep(2000);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcomeb) + model.getDisplayName();

        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_facility_setting,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.facility_setting_data:
                 openFacilitySetting();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    //open Facility Setting
    private void openFacilitySetting(){

        Intent intent = new Intent(this, FacilitySettingActivity.class);
       // intent.putExtra("fullName", client.getFirstName());
       // intent.putExtra("ClientId", client.getClientId());
          startActivity(intent);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
