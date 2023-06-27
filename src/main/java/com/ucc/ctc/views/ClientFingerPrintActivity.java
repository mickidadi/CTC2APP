package com.ucc.ctc.views;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;
import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.biometrics.NFinger;
import com.neurotec.biometrics.NSubject;
import com.neurotec.biometrics.NTemplateSize;
import com.neurotec.biometrics.client.NBiometricClient;
import com.neurotec.devices.NDeviceManager;
import com.neurotec.devices.NDeviceType;
import com.neurotec.util.concurrent.CompletionHandler;
import com.ucc.ctc.R;
import com.ucc.ctc.models.entity.ClientBiometricEntity;
import com.ucc.ctc.util.ExceptionUtils;
import com.ucc.ctc.utils.Finger;
import com.ucc.ctc.utils.Tools;
import com.ucc.ctc.utils.Util;
import com.ucc.ctc.utils.ViewAnimation;
import com.ucc.ctc.view.ErrorDialogFragment;
import com.ucc.ctc.view.InfoDialogFragment;
import com.ucc.ctc.viewsModel.ClientBiometricViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class ClientFingerPrintActivity extends AppCompatActivity {


    private CheckBox fingerCheckBox;
    private ImageView handImage;
    private View parent_view;
    private TextView finger_label_value;
    private NestedScrollView nested_scroll_view;
    private ImageButton bt_toggle_text, bt_toggle_input;
    private Button bt_hide_text, bt_save_input, bt_hide_input,take_finger_print;
    private View lyt_expand_text, lyt_expand_input;
    ClientBiometricViewModel clientBiometricViewModel;
    List<String> selectedItems_right;
    private TextView mResult;
    private NBiometricClient mBiometricClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_finger_print);

        parent_view = findViewById(android.R.id.content);
        clientBiometricViewModel=new ClientBiometricViewModel(getApplication());
        initComponent();

        LinearLayout left_container = findViewById(R.id.left_finger_checkbox);
        LinearLayout right_container = findViewById(R.id.right_finger_checkbox);
        handImage = findViewById(R.id.hand_image);
        finger_label_value=findViewById( R.id.finger_label_value );
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
                        finger_label_value.setText(item_left);
                        switch (selectedFinger) {

                            case LEFT_THUMB:
                                handImage.setImageResource(R.mipmap.left_thumb_finger );
                                break;
                            case LEFT_INDEX_FINGER:
                                handImage.setImageResource(R.mipmap.left_index_finger );
                                break;
                            case LEFT_MIDDLE_FINGER:
                                handImage.setImageResource(R.mipmap.left_middle_finger );
                                break;
                            case LEFT_RING_FINGER:
                                handImage.setImageResource(R.mipmap.left_ring_finger );
                                break;
                            case LEFT_LITTLE_FINGER:
                                handImage.setImageResource(R.mipmap.left_pinky_finger );
                                break;
                        }
                    } else {
                        // Find the index of the item with value
                        if(selectedItems_right.size()!=0) {
                            int indexToRemove = selectedItems_right.indexOf(item_left);
                            // Remove the item at that index
                            selectedItems_right.remove( indexToRemove );
                        }
                        handImage.setImageResource(R.mipmap.left_hand_empty);
                        finger_label_value.setText("");
                    }
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
                        finger_label_value.setText(item_right);
                        switch (selectedFinger) {
                            case RIGHT_THUMB:
                                handImage.setImageResource(R.mipmap.right_thumb_finger );
                                break;
                            case RIGHT_INDEX_FINGER:
                                handImage.setImageResource(R.mipmap.right_index_finger );
                                break;
                            case RIGHT_MIDDLE_FINGER:
                                handImage.setImageResource(R.mipmap.right_middle_finger );
                                break;
                            case RIGHT_RING_FINGER:
                                handImage.setImageResource(R.mipmap.right_ring_finger );
                                break;
                            case RIGHT_LITTLE_FINGER:
                                handImage.setImageResource(R.mipmap.right_pinky_finger );
                                break;
                        }
                    } else {
                        // Find the index of the item with value
                             if(selectedItems_right.size()!=0) {
                                 int indexToRemove = selectedItems_right.indexOf(item_right);
                                 // Remove the item at that index
                                 selectedItems_right.remove(indexToRemove );
                             }
                        handImage.setImageResource(R.mipmap.right_hands_empty);
                        finger_label_value.setText("");
                        }
                    Log.d("MyTag", "My array: " +selectedItems_right);
                }
            });
        }
        take_finger_print=findViewById( R.id.bt_take_finger_print );
        take_finger_print.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        } );
    }
    private void initComponent() {

        // text section
        bt_toggle_text = (ImageButton) findViewById(R.id.bt_toggle_text);
        bt_hide_text = (Button) findViewById(R.id.bt_hide_text);
        lyt_expand_text = (View) findViewById(R.id.lyt_expand_text);
        lyt_expand_text.setVisibility(View.GONE);

        bt_toggle_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSectionText(bt_toggle_text);
            }
        });

        bt_hide_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSectionText(bt_toggle_text);
            }
        });

        // input section
        bt_toggle_input = (ImageButton) findViewById(R.id.bt_toggle_input);
        bt_hide_input = (Button) findViewById(R.id.bt_hide_input);
        bt_save_input = (Button) findViewById(R.id.bt_save_input);
        lyt_expand_input = (View) findViewById(R.id.lyt_expand_input);
        //lyt_expand_input.setVisibility(View.GONE);

        bt_toggle_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSectionInput(bt_toggle_input);
            }
        });

        bt_hide_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSectionInput(bt_toggle_input);
            }
        });

        bt_save_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(parent_view, "Data saved", Snackbar.LENGTH_SHORT).show();
                toggleSectionInput(bt_toggle_input);
            }
        });

        // nested scrollview
        nested_scroll_view = (NestedScrollView) findViewById(R.id.nested_scroll_view);
    }
    private void toggleSectionText(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(lyt_expand_text, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    Tools.nestedScrollTo(nested_scroll_view, lyt_expand_text);
                }
            });
        } else {
            ViewAnimation.collapse(lyt_expand_text);
        }
    }

    private void toggleSectionInput(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(lyt_expand_input, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    Tools.nestedScrollTo(nested_scroll_view, lyt_expand_input);
                }
            });
        } else {
            ViewAnimation.collapse(lyt_expand_input);
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
    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_success_finger_print);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        Log.d("MyTag mickidadi", "My array: " +selectedItems_right);

        ((AppCompatButton) dialog.findViewById(R.id.bt_close)).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if(selectedItems_right.size()>1){
                     //save finger to database
                     TextView finger_capture = findViewById( R.id.finger_label_value );
                     String finger_label = finger_capture.getText().toString();
                     String clientId = "3";
                     byte[] biometricTemplate = new byte[0];
                     String entryDate = "12-11-2023";
                     String rowVersion = "11-09-2023";
                     String centralReferenceCode = "83123120193";
                     String isDuplicate = "2";
                     int quality = 3;
                     int actionTag = 2;
                     int biometricsRegOrigin = 4;
                     int changeTrackStatus = 1;
                     String recGUID = "8432984jajjeqwkejq8342340";
                     if (finger_label != "") {
                         ClientBiometricEntity clientBiometric = new ClientBiometricEntity( clientId, biometricTemplate, entryDate, rowVersion
                                 , centralReferenceCode, isDuplicate, quality, actionTag, biometricsRegOrigin, changeTrackStatus, recGUID,"" );
                         clientBiometricViewModel.insert( clientBiometric );
                     }
                     //end save
                     String right_pinky_fingers= selectedItems_right.get(0);
                     finger_label_value.setText(right_pinky_fingers);
                     getFinger(right_pinky_fingers);
                     int indexToRemove = selectedItems_right.indexOf(right_pinky_fingers);
                     // Remove the item at that index
                     selectedItems_right.remove( indexToRemove );
                   //  return;
                 }else{
                     handImage.setImageResource(R.mipmap.left_hand_empty);
                 }
                 Toast.makeText(getApplicationContext(), ((AppCompatButton) v).getText().toString() + " Close", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                //Send to Profile page

                //End
            }
        });
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
    public void getFinger(String finger){

        Finger selectedFinger = Finger.valueOf(finger); // Replace with code to get selected finger
        // finger_label_value.setText(item_right);
          switch (selectedFinger) {
            case RIGHT_THUMB:
                handImage.setImageResource( R.mipmap.right_thumb_finger );
                break;
            case RIGHT_INDEX_FINGER:
                handImage.setImageResource(R.mipmap.right_index_finger );
                break;
            case RIGHT_MIDDLE_FINGER:
                handImage.setImageResource(R.mipmap.right_middle_finger );
                break;
            case RIGHT_RING_FINGER:
                handImage.setImageResource(R.mipmap.right_ring_finger );
                break;
            case RIGHT_LITTLE_FINGER:
                handImage.setImageResource(R.mipmap.right_pinky_finger );
                break;
          case LEFT_THUMB:
              handImage.setImageResource(R.mipmap.left_thumb_finger );
              break;
          case LEFT_INDEX_FINGER:
              handImage.setImageResource(R.mipmap.left_index_finger );
              break;
          case LEFT_MIDDLE_FINGER:
              handImage.setImageResource(R.mipmap.left_middle_finger );
              break;
          case LEFT_RING_FINGER:
              handImage.setImageResource(R.mipmap.left_ring_finger );
              break;
          case LEFT_LITTLE_FINGER:
              handImage.setImageResource(R.mipmap.left_pinky_finger );
              break;
        }
    }
@SuppressLint("StringFormatInvalid")
private void capture() {
        try {
            NSubject subject = new NSubject();
            NFinger finger = new NFinger();

            NDeviceManager deviceManager = mBiometricClient.getDeviceManager();

            NDeviceManager.DeviceCollection devices = deviceManager.getDevices();
            if (devices.size() > 0) {
                System.out.format("Found %d fingerprint scanner\n", devices.size());
            } else {
                showMessage("msg_no_scanners_found");
                return;
            }

            showMessage(getString(R.string.capturing, mBiometricClient.getFingerScanner().getDisplayName()));

            subject.getFingers().add(finger);

            showMessage("Capturing....");
            //mBiometricClient.createTemplate(subject, subject, completionHandler);
        } catch (Exception ex) {
            showError(ex);
        }
    }
    private void showMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mResult.append(message + "\n");
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideProgress();
        if (mBiometricClient != null) {
            mBiometricClient.cancel();
            mBiometricClient.dispose();
            mBiometricClient = null;
        }
    }

    private void init() {
        mBiometricClient = new NBiometricClient();
        mBiometricClient.setUseDeviceManager(true);
        // Set type of the device used
        mBiometricClient.getDeviceManager().setDeviceTypes( EnumSet.of( NDeviceType.FINGER_SCANNER));
        // Set finger template size (large is recommended for enrolment to database) (optional)
        mBiometricClient.setFingersTemplateSize( NTemplateSize.LARGE);
        // Initialize NBiometricClient
        mBiometricClient.initialize();
    }
    protected void showProgress(final String message) {
        hideProgress();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               // mProgressDialog = ProgressDialog.show(BaseActivity.this, "", message);
            }
        });
    }

    protected void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                /*if (mProgressDialog != null && mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }*/
            }
        });
    }
    protected void showError(Throwable th) {
        Log.e(getClass().getSimpleName(), "Exception", th);
        showError(ExceptionUtils.getMessage(th), false);
    }

    protected void showError(String message, boolean close) {
        ErrorDialogFragment.newInstance(message, close).show(getFragmentManager(), "error");
    }
    protected void showInfo(int messageId) {
        showInfo(getString(messageId));
    }

    protected void showInfo(String message) {
        InfoDialogFragment.newInstance(message).show(getFragmentManager(), "info");
    }
}