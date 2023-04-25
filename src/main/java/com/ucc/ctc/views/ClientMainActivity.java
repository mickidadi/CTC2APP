package com.ucc.ctc.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ucc.ctc.R;
import com.ucc.ctc.adapters.ClientMainRecyclerViewAdapter;
import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.utils.AddEditClientActivity;
import com.ucc.ctc.viewsModel.ClientViewModel;

import java.util.List;

public class ClientMainActivity extends AppCompatActivity {

    private ClientViewModel clientViewModel;
    private RecyclerView recyclerView;
    private ClientMainRecyclerViewAdapter adapter;
    public static final int ADD_CLIENT_REQUEST=1;
    public static final int EDIT_CLIENT_REQUEST=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_client_maintest );

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new ClientMainRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        clientViewModel.getAllClients().observe(this, new Observer<List<ClientEntity>>() {
            @Override
            public void onChanged(List<ClientEntity> clients) {

               // adapter.submitList(clients);
                adapter.setClients(clients);
            }
        });

        FloatingActionButton buttonAddClient = findViewById(R.id.fab_add_user);
        buttonAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientMainActivity.this, AddEditClientActivity.class);
                startActivityForResult(intent,ADD_CLIENT_REQUEST);
            }
        });

        adapter.setOnItemClickListener(new ClientMainRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ClientEntity client) {
                Intent intent = new Intent(ClientMainActivity.this, AddEditClientActivity.class);
                intent.putExtra(AddEditClientActivity.EXTRA_ID, client.getId());
                intent.putExtra(AddEditClientActivity.EXTRA_FIRST_NAME, client.getFirstName());
                intent.putExtra(AddEditClientActivity.EXTRA_MIDDLE_NAME, client.getMiddleName());
                intent.putExtra(AddEditClientActivity.EXTRA_LAST_NAME, client.getLastName());
                intent.putExtra(AddEditClientActivity.EXTRA_CLIENT_ID, client.getClientId());
                intent.putExtra(AddEditClientActivity.EXTRA_REFERENCECODE, client.getReferenceCode());
                startActivityForResult(intent,EDIT_CLIENT_REQUEST);
            }
        });

        ItemTouchHelper.Callback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                ClientEntity client = adapter.getClientAt(position);
                clientViewModel.delete(client);
                Toast.makeText(ClientMainActivity.this, "Client deleted", Toast.LENGTH_SHORT).show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_allClient:
                clientViewModel.deleteAllClients();
                Toast.makeText(this, "All Client deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
         if(requestCode==ADD_CLIENT_REQUEST&&resultCode==RESULT_OK){

               String firstName=data.getStringExtra( AddEditClientActivity.EXTRA_FIRST_NAME );
               String middleName=data.getStringExtra( AddEditClientActivity.EXTRA_MIDDLE_NAME );
               String lastName=data.getStringExtra( AddEditClientActivity.EXTRA_LAST_NAME );
               String clientId=data.getStringExtra( AddEditClientActivity.EXTRA_CLIENT_ID );
               String referenceCode=data.getStringExtra( AddEditClientActivity.EXTRA_REFERENCECODE );
               String sex="Male";
               String dob="2019-11-12";
               String fingerPrintConcept="Yes";

               SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("facilitySession", Context.MODE_PRIVATE);
               String facilityId = sharedPreferences.getString("facilityIdsession", "");

            ClientEntity client =new ClientEntity(clientId,firstName,middleName,lastName,sex,dob,referenceCode,facilityId,fingerPrintConcept );
            clientViewModel.insert( client );
            Toast.makeText(this, "Client Saved Successfully", Toast.LENGTH_SHORT).show();
         }else{
             Toast.makeText(this, "Client Not Saved", Toast.LENGTH_SHORT).show();
         }
    }
}

