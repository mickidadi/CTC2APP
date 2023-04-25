package com.ucc.ctc.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ucc.ctc.R;
import com.ucc.ctc.utils.Tools;

public class CtcProfileActivity extends AppCompatActivity {
      ImageView capture_finger_print;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_client_profile);
        TextView profile_client_fullName =findViewById( R.id.profile_client_fullName);
        TextView gender =findViewById( R.id.profile_client_gender );
        //TextView profile_clientId =findViewById( R.id.profile_client_id );
        //intToolbar();
        initToolbar();
        //initComponent();
        Intent intent = getIntent();
        String fullName = intent.getStringExtra("fullName");
        String sex = intent.getStringExtra("sex");
        String clientId = intent.getStringExtra("clientId");
        profile_client_fullName.setText( fullName );
        gender.setText( sex+" "+clientId );
        //profile_clientId.setText(clientId );
        //End
       // tabLayout=findViewById(R.id.tab_layouts );
       // viewPager2=findViewById( R.id.view_pager);
        capture_finger_print=findViewById( R.id.capture_finger_print );
        /*profileViewPagerAdapter=new ProfileViewPagerAdapter( this);

        viewPager2.setAdapter(profileViewPagerAdapter);

        tabLayout.addOnTabSelectedListener( new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem( tab.getPosition() );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        } );
        viewPager2.registerOnPageChangeCallback( new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected( position );
                tabLayout.getTabAt(position).select();
            }
        });*/
        //CAPTURE FINGER PRINT
        capture_finger_print.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSessionData(fullName,clientId);
                Intent fingerActivity = new Intent( CtcProfileActivity.this, FingerClientActivity.class);
                startActivity(fingerActivity);
            }
        } );
        //END
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_client_profile,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_allClient:
              //  clientViewModel.deleteAllClients();
            case R.id.ctc_enrollment:
                startActivity(new Intent( CtcProfileActivity.this, CTCEnrollmentActivity.class));
              //  snackBar("All Client Deleted");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
     private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.colorPrimaryLight);
    }
    public void setSessionData(String ClientName,String ClientId){

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("ClientFingerSession", Context.MODE_PRIVATE);
          SharedPreferences.Editor editor = sharedPreferences.edit();
          editor.putString("fingerClientName", ClientName);
          editor.putString( "fingerClientId",ClientId);
          editor.apply();


    }

}