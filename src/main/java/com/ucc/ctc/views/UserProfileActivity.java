package com.ucc.ctc.views;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.tabs.TabLayout;
import com.ucc.ctc.R;
import com.ucc.ctc.models.Users;
import com.ucc.ctc.utils.Tools;
import com.ucc.ctc.viewsModel.UserViewModel;
import com.ucc.ctc.viewsModel.UserViewTest;

public class UserProfileActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView emailTextView;
    private EditText userId;
    private UserViewModel userViewModels;
    private UserViewTest userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_client_profile_tab );
        initToolbar();
        initComponent();
    }
        private void initToolbar() {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            Tools.setSystemBarColor(this, R.color.colorPrimaryLight);
        }

        private void initComponent() {

            ((TabLayout) findViewById(R.id.tab_layouts)).addOnTabSelectedListener( new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    Toast.makeText(getApplicationContext(), "Tab " + tab.getText() + " Clicked", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
           // getMenuInflater().inflate(R.menu.menu_search_setting, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == android.R.id.home) {
                finish();
            } else {
                Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
            }
            return super.onOptionsItemSelected(item);
        }
    }