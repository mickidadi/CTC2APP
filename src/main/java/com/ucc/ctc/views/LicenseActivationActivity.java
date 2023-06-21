package com.ucc.ctc.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;
import com.ucc.ctc.R;
import com.ucc.ctc.utils.Finger;
import com.ucc.ctc.utils.Tools;
import com.ucc.ctc.utils.Util;
import com.ucc.ctc.utils.ViewAnimation;
import com.ucc.ctc.viewsModel.ClientBiometricViewModel;

import java.util.ArrayList;
import java.util.List;

public class LicenseActivationActivity extends AppCompatActivity {

    private NestedScrollView nested_scroll_view;
    private ImageButton bt_toggle_licensing_activation, bt_toggle_biometry_setting;
    private View lyt_expand_licensing_activation, lyt_expand_biometry_setting,parent;
    private CheckBox fingerCheckBox;
    //private ImageView handImage;
    //private TextView finger_label_value;
    List<String> selectedItems_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
         setContentView( R.layout.activity_license_activation );

        //Setting finger
        initComponent();
        initFinger();
        //end
    }
    private void initComponent() {
        // Biometric Licensing item_section
        parent = findViewById(android.R.id.content);
        bt_toggle_licensing_activation = (ImageButton) findViewById(R.id.bt_toggle_licensing_activation);
        lyt_expand_licensing_activation = findViewById(R.id.lyt_expand_licensing_activation);
        bt_toggle_licensing_activation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSectionLicensing(bt_toggle_licensing_activation);
            }
        });
        // Biometric Setting Section
        bt_toggle_biometry_setting= (ImageButton) findViewById(R.id.bt_toggle_biometry_setting);
        lyt_expand_biometry_setting = (View)findViewById(R.id.lyt_expand_biometry_setting);

        bt_toggle_biometry_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSectionBiometry(bt_toggle_biometry_setting);
            }
        });
        // nested scrollview
        nested_scroll_view = (NestedScrollView) findViewById(R.id.nested_scroll_view);
    }

    private void toggleSectionLicensing(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(lyt_expand_licensing_activation, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    Tools.nestedScrollTo(nested_scroll_view, lyt_expand_licensing_activation);
                }
            });
        } else {
            ViewAnimation.collapse(lyt_expand_licensing_activation);
        }
    }
  private void toggleSectionBiometry(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(lyt_expand_biometry_setting, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    Tools.nestedScrollTo(nested_scroll_view, lyt_expand_biometry_setting);
                }
            });
        } else {
            ViewAnimation.collapse(lyt_expand_biometry_setting);
        }
    }
   public boolean toggleArrow(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }
    public void snackBar(String message) {
        Snackbar.make(parent, message, Snackbar.LENGTH_SHORT).show();
    }
    public void initFinger(){
        LinearLayout left_container = findViewById(R.id.left_finger_checkbox);
        LinearLayout right_container = findViewById(R.id.right_finger_checkbox);
      //  handImage = findViewById(R.id.hand_image);
        //finger_label_value=findViewById( R.id.finger_label_value );
        //List<String> selectedItems_left = new ArrayList<>();
        selectedItems_right = new ArrayList<>();
        for (String item_left : Util.getLeftFinger()) {
            MaterialCheckBox checkBox_left = new MaterialCheckBox(this);
            checkBox_left.setText(item_left);
            checkBox_left.setTextSize( 11 );
            left_container.addView(checkBox_left);

            checkBox_left.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        // selectedItems_left.add(item_left);
                        selectedItems_right.add(item_left);
                        Finger selectedFinger = Finger.valueOf(item_left); // Replace with code to get selected finger
                         } else {
                        // Find the index of the item with value
                        if(selectedItems_right.size()!=0) {
                            int indexToRemove = selectedItems_right.indexOf(item_left);
                            // Remove the item at that index
                            selectedItems_right.remove( indexToRemove );
                        }                    }
                }
            });
        }
        for (String item_right : Util.getRightFinger()) {
            MaterialCheckBox checkBox_right = new MaterialCheckBox(this);
            checkBox_right.setText(item_right);
            checkBox_right.setTextSize( 11 );
            right_container.addView(checkBox_right);

            checkBox_right.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        selectedItems_right.add(item_right);
                        Finger selectedFinger = Finger.valueOf(item_right); // Replace with code to get selected finger
                        } else {
                        // Find the index of the item with value
                        if(selectedItems_right.size()!=0) {
                            int indexToRemove = selectedItems_right.indexOf(item_right);
                            // Remove the item at that index
                            selectedItems_right.remove(indexToRemove );
                        }
                    }
                    Log.d("MyTag", "My array: " +selectedItems_right);
                }
            });
        }
    }
}