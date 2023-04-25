package com.ucc.ctc.views;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.ucc.ctc.R;

import java.util.ArrayList;
import java.util.List;

public class CTCEnrollmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ctcenrollment );
        List<String> items = new ArrayList<>();
            items.add("With Records(Referral form and CTC 1 card)");
            items.add("No Records Available");
            items.add("In Care");
            items.add("On ART");
        LinearLayout container = findViewById(R.id.check_box_container);

        for (String item : items) {
            MaterialCheckBox checkBox = new MaterialCheckBox(this);
            checkBox.setText(item);
            container.addView(checkBox);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Toast.makeText(CTCEnrollmentActivity.this, item + " is checked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CTCEnrollmentActivity.this, item + " is unchecked", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}