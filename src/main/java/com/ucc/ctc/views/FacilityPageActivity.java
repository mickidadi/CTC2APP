package com.ucc.ctc.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ucc.ctc.R;
import com.ucc.ctc.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class FacilityPageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button buttonext;
    SharedPreferences sharedpreferences;
    public static final String facilityName = "facilityName";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_facility_page );
        Spinner facility_spinner=findViewById(R.id.facility_spinner);
          buttonext=findViewById( R.id.facility_next);

    // Spinner click listener
        facility_spinner.setOnItemSelectedListener(this);

    // Spinner Drop down elements
    List<String> categories = new ArrayList<String>();
      categories.add("Automobile");
      categories.add("Business Services");
      categories.add("Computers");
      categories.add("Education");
      categories.add("Personal");
      categories.add("Travel");
    String compareValue="Personal";
    // Creating adapter for spinner
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

    // Drop down layout style - list view with radio button
      dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // attaching data adapter to spinner
        facility_spinner.setAdapter(dataAdapter);
        if (compareValue != null) {
              int spinnerPosition = Util.getIndex(facility_spinner,compareValue);
             facility_spinner.setSelection(spinnerPosition);
        }

}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        buttonext.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FacilityPageActivity.this, LoginActivity.class);
                sharedpreferences = getSharedPreferences("FacilityLogin", Context.MODE_PRIVATE);
               // intent.putExtra("facilityName", item);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(facilityName, item);
                    editor.commit();
                startActivity(intent);
            }
        } );
    }


    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}