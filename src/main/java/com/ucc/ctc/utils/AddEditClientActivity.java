package com.ucc.ctc.utils;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ucc.ctc.R;

public class AddEditClientActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.ucc.ctc.utils.EXTRA_ID";
    public static final String EXTRA_FIRST_NAME = "com.ucc.ctc.utils.EXTRA_FIRST_NAME";
    public static final String EXTRA_MIDDLE_NAME = "com.ucc.ctc.utils.EXTRA_MIDDLE_NAME";
    public static  final String EXTRA_LAST_NAME="com.ucc.ctc.utils.EXTRA_LAST_NAME";
    public static  final String EXTRA_CLIENT_ID="com.ucc.ctc.utils.EXTRA_CLIENT_ID";
    public static  final String EXTRA_DATEOFBIRTH="com.ucc.ctc.utils.EXTRA_DATEOFBIRTH";
    public static  final String EXTRA_SEX="com.ucc.ctc.utils.EXTRA_SEX";
    public static  final String EXTRA_REFERENCECODE="com.ucc.ctc.utils.EXTRA_REFERENCECODE";
    private EditText firstName;
    private EditText middleName;
    private EditText lastName;
    private EditText clientId;
    RadioGroup sex_radio_group;
    RadioButton selectedSex;
    private EditText dateOfBirth;
    private EditText referenceCode;
    private TextView sexView;
    private Button mSaveBtn;
    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_add_edit_client);

        firstName = findViewById(R.id.firstName);
        middleName =findViewById(R.id.middleName);
        lastName = findViewById(R.id.lastName);
        clientId=findViewById( R.id.clientId );
        referenceCode=findViewById( R.id.referenceCode);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Client");
            firstName.setText(intent.getStringExtra(EXTRA_FIRST_NAME));
            middleName.setText(intent.getStringExtra(EXTRA_MIDDLE_NAME));
            lastName.setText(intent.getStringExtra(EXTRA_LAST_NAME));
            clientId.setText(intent.getStringExtra(EXTRA_CLIENT_ID));
            referenceCode.setText(intent.getStringExtra(EXTRA_REFERENCECODE));

        } else {
            setTitle("Add Client ");
        }
    }

    private void saveClient() {
        String firstname = firstName.getText().toString();
        String middlename = middleName.getText().toString();
        String lastname=lastName.getText().toString();
        String clientid=clientId.getText().toString();
        String referencecode=referenceCode.getText().toString();

        if (firstname.trim().isEmpty() || lastname.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a first Name and Last Name", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_FIRST_NAME, firstname);
        data.putExtra(EXTRA_MIDDLE_NAME, middlename);
        data.putExtra(EXTRA_LAST_NAME, lastname);
        data.putExtra(EXTRA_CLIENT_ID, clientid);
        data.putExtra(EXTRA_REFERENCECODE, referencecode);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
       setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_allClient:
                saveClient();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}