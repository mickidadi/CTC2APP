package com.ucc.ctc.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.ucc.ctc.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CTCEnrollmentActivity extends AppCompatActivity {
    private SearchableSpinner searchableSpinner_referred,searchableSpinner_items,searchableSpinner_arvExposure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ctcenrollment_main );

        List<String> referred = new ArrayList<>();
        referred.add("OPD");
        referred.add("STI");
        referred.add("TB Clinic");
        referred.add("RCH/PMTCT/EID");
        referred.add("Inpatient");
        referred.add("CITC");
        referred.add("Community Index");
        referred.add("Facility Index");
        referred.add("PLHIV Group");
        referred.add("PrEP");
        referred.add("CBHS");
        referred.add("VMMC");
        referred.add("Self Testing");
        referred.add("Other");
        // Create an ArrayAdapter with the item list
        searchableSpinner_referred = findViewById(R.id.check_box_container_referred);
        ArrayAdapter<String> adapter_referred = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, referred);
        adapter_referred.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the SearchableSpinner
        searchableSpinner_referred.setAdapter(adapter_referred);
        // Select an item programmatically
        int referredSelectedIndex = 2; // Index of the item you want to select
      if (referredSelectedIndex >= 0 && referredSelectedIndex < adapter_referred.getCount()) {
            searchableSpinner_referred.setSelection(referredSelectedIndex);
        }
      //end
        List<String> items = new ArrayList<>();
        items.add("With Records(Referral form and CTC 1 card)");
        items.add("No Records Available");
        items.add("In Care");
        items.add("On ART");

        // Create an ArrayAdapter with the item list
        searchableSpinner_items = findViewById(R.id.check_box_container);
        ArrayAdapter<String> adapter_items = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter_items.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the SearchableSpinner
        searchableSpinner_items.setAdapter(adapter_items);
        // Select an item programmatically
        int itemsSelectedIndex = 2; // Index of the item you want to select
        if (itemsSelectedIndex >= 0 && itemsSelectedIndex < adapter_items.getCount()) {
            searchableSpinner_items.setSelection(itemsSelectedIndex);
        }
        //end
        List<String> arvExposure = new ArrayList<>();
        arvExposure.add("None");
        arvExposure.add("PEP");
        arvExposure.add("ART");
        arvExposure.add("Hepatitis B Rx");

        arvExposure.add("HEI Prophylaxis");
        arvExposure.add("PMTCT Prophylaxis");
        arvExposure.add("PrEP");
        arvExposure.add("Other");

        // Create an ArrayAdapter with the item list
        searchableSpinner_arvExposure = findViewById(R.id.drugAllergiesOnART);
        ArrayAdapter<String> adapter_arvExposure = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arvExposure);
        adapter_items.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the SearchableSpinner
        searchableSpinner_arvExposure.setAdapter(adapter_arvExposure);
        // Select an item programmatically
        int arvExposureSelectedIndex = 2; // Index of the item you want to select
        if (arvExposureSelectedIndex >= 0 && arvExposureSelectedIndex < adapter_arvExposure.getCount()) {
            searchableSpinner_arvExposure.setSelection(arvExposureSelectedIndex);
        }
        //end
        TextView dateDiagnosedhiv = findViewById(R.id.dateDiagnosedhiv);
        dateDiagnosedhiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Create a new instance of DatePickerDialog and show it
                DatePickerDialog datePickerDialog = new DatePickerDialog( CTCEnrollmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the text field with the selected date
                        dateDiagnosedhiv.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
        //End date Diagnosed HIV
        EditText dateRetestedVerification = findViewById(R.id.dateRetestedVerification);
        dateRetestedVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Create a new instance of DatePickerDialog and show it
                DatePickerDialog datePickerDialog = new DatePickerDialog( CTCEnrollmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the text field with the selected date
                        dateRetestedVerification.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
        //end dateRetestedVerification
        EditText dateEnrolledInCare = findViewById(R.id.dateEnrolledInCare);
        dateEnrolledInCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Create a new instance of DatePickerDialog and show it
                DatePickerDialog datePickerDialog = new DatePickerDialog( CTCEnrollmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the text field with the selected date
                        dateEnrolledInCare.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
        //end dateEnrolledInCare
        EditText dateReadyStartART = findViewById(R.id.dateEnrolledInCare);
        dateReadyStartART.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Create a new instance of DatePickerDialog and show it
                DatePickerDialog datePickerDialog = new DatePickerDialog( CTCEnrollmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the text field with the selected date
                        dateReadyStartART.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
        //end dateReadyStartART
        EditText dateStartART = findViewById(R.id.dateEnrolledInCare);
        dateStartART.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Create a new instance of DatePickerDialog and show it
                DatePickerDialog datePickerDialog = new DatePickerDialog( CTCEnrollmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the text field with the selected date
                        dateStartART.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
        //end dateReadyStartART
    }
}