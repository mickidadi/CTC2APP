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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.ucc.ctc.R;
import com.ucc.ctc.models.UserProfile;
import com.ucc.ctc.models.entity.UserEntity;
import com.ucc.ctc.repository.LoginFacilityRepository;
import com.ucc.ctc.viewsModel.FacilityViewModel;
import com.ucc.ctc.viewsModel.LoginFacilityViewModel;
import com.ucc.ctc.viewsModel.UserRemoteViewModel;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import java.util.List;

public class LoginFacilityActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
private EditText usernameEditText;
private EditText passwordEditText;
private Button loginButton;
private FacilityViewModel facilityViewModel;
private UserRemoteViewModel userViewModels;
private LoginFacilityViewModel loginViewModel;
//Private Spinner Spinner Facility;
SearchableSpinner facility_spinner;
private String username ;
private String password;
private String facilityName;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView( R.layout.activity_login_facility);
LoginFacilityRepository loginRepository=new LoginFacilityRepository(getApplication());

loginButton = findViewById(R.id.login);
facility_spinner=findViewById(R.id.facilityLoginSpinner);
TextView facilitySetting=findViewById( R.id.facilityRegisterAccount );
//Button facilityData=findViewById( R.id.facilityRegisterAccount);
usernameEditText = findViewById(R.id.username);
passwordEditText = findViewById(R.id.password);
//Open FACILITY SETTING WINDOW
// int genderId = radioGroupGender.getCheckedRadioButtonId();
//RadioButton radioButton = findViewById(genderId);
// String gender = radioButton.getText().toString();

facilitySetting.setOnClickListener( new View.OnClickListener() {
@Override
public void onClick(View view) {
Intent  intent =new Intent(LoginFacilityActivity.this,FacilitySettingActivity.class);
startActivity(intent);
}
} );
//END SETTING FACILITY
//Start Create an instance of the ViewModel
facilityViewModel = new ViewModelProvider(this).get( FacilityViewModel.class);
//
facility_spinner.setOnItemSelectedListener( LoginFacilityActivity.this );
// Observe the LiveData object and update the adapter whenever the data changes
facilityViewModel.getAllFacilityName().observe(this, new Observer<List<String>>() {
@Override
public void onChanged(List<String> facilityNames) {
ArrayAdapter<String> adapter = new ArrayAdapter<>(LoginFacilityActivity.this, android.R.layout.simple_spinner_dropdown_item, facilityNames);
facility_spinner.setTitle("Select Facility");
//facility_spinner.setPositiveButton("OK");
facility_spinner.setAdapter(adapter);
}
});
//End find facility

loginViewModel = ViewModelProviders.of(LoginFacilityActivity.this).get( LoginFacilityViewModel.class);

loginButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {

username = usernameEditText.getText().toString().trim();
password = passwordEditText.getText().toString().trim();
if (CheckAllFields()) {
facilityName = facility_spinner.getSelectedItem().toString();
//loginViewModel.authenticateUser(username, password);
setSessionData();
loginViewModel.login( username, password ).observe( LoginFacilityActivity.this, new Observer<UserEntity>() {
@Override
public void onChanged(UserEntity user) {
if (user == null) {
// show error message to user
userViewModels = ViewModelProviders.of( LoginFacilityActivity.this ).get( UserRemoteViewModel.class );
userViewModels.getRemoteUser( username, password );
userViewModels.getUserLiveData().observe( LoginFacilityActivity.this, new Observer<UserProfile>() {
    @Override
    public void onChanged(UserProfile user) {
        Log.v( "Welcome", "Message IN " + facilityName + " Code :" + user.getCode() );
        if (user.getCode() == 200) {
            String fullName = user.getFullName();
            String pass = user.getPassword();
            String loginName = user.getUsername();
            String facilityId = user.getFacilityId();
            if (!TextUtils.isEmpty( loginName ) || !TextUtils.isEmpty( fullName ) || !TextUtils.isEmpty( pass )) {
                //String fullName, String loginName, String password, String facilityId
                UserEntity userData = new UserEntity( fullName, loginName, pass, facilityId );
                Log.v( "Hello Test ", "Remote user found " + userData.getFullName() + " " + fullName );
                loginRepository.insert( userData );
                //end register User

                // login successful, navigate to main activity
                Intent intent = new Intent( LoginFacilityActivity.this, MainActivity.class );
                startActivity( intent );
                // finish();
            }
            Toast.makeText( LoginFacilityActivity.this, "Remote user found " + user.getFullName(), Toast.LENGTH_SHORT ).show();
        } else {
            Log.v( "Hello Test ", "Remote user Not found " + user.getCode() );
            Toast.makeText( LoginFacilityActivity.this, "Remote user Not found " + user.getUsername(), Toast.LENGTH_SHORT ).show();

        }
    }
} );
///  Toast.makeText(LoginFacilityActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
} else {

// setSessionData();
//Toast.makeText(LoginFacilityActivity.this, "valid username "+facilityIdsession, Toast.LENGTH_SHORT).show();
// login successful, navigate to main activity
Intent intent = new Intent( LoginFacilityActivity.this, MainActivity.class );
startActivity( intent );
//finish();
}
}
} );
}
}
});
}
private boolean CheckAllFields() {
boolean isValid=true;
if (username.isEmpty()) {
usernameEditText.setError("This field is required");
isValid=false;
}
if (password.isEmpty()) {
passwordEditText.setError("This field is required");
isValid=false;
}
TextView errorText = (TextView)facility_spinner.getSelectedView();
if (facility_spinner.getCount() == 0) {
errorText.setError("");
errorText.setTextColor( Color.RED);//just to highlight that this is an error
errorText.setText("This field is required");//changes the selected item text to this
isValid=false;
}
return  isValid;
}
/**
* <p>Callback method to be invoked when an item in this view has been
* selected. This callback is invoked only when the newly selected
* position is different from the previously selected position or if
* there was no selected item.</p>
* <p>
* Implementers can call getItemAtPosition(position) if they need to access the
* data associated with the selected item.
*
* @param parent   The AdapterView where the selection happened
* @param view     The view within the AdapterView that was clicked
* @param position The position of the view in the adapter
* @param id       The row id of the item that is selected
*/
@Override
public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
// On selecting a spinner item
String item = parent.getItemAtPosition(position).toString();

// Showing selected spinner item
Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
}

/**
* Callback method to be invoked when the selection disappears from this
* view. The selection can disappear for instance when touch is activated
* or when the adapter becomes empty.
*
* @param parent The AdapterView that now contains no selected item.
*/
@Override
public void onNothingSelected(AdapterView<?> parent) {

}
public void setSessionData(){


SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("facilitySession", Context.MODE_PRIVATE);
SharedPreferences.Editor editor = sharedPreferences.edit();
editor.putString("Name",facilityName );
editor.putString("facilityNamesession", facilityName);
String[] facilityId = facilityName.split(" ");
String facilityIdsession="";
if(facilityId.length>0) {
facilityIdsession=facilityId[0];
editor.putString( "facilityIdsession",facilityIdsession);
}
editor.apply();


}
}
