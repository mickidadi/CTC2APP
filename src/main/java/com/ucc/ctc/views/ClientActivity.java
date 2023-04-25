package com.ucc.ctc.views;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.ucc.ctc.R;
import com.ucc.ctc.adapters.ClientRecyclerViewAdapter;
import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.utils.CreateClientDialog;
import com.ucc.ctc.utils.LineItemDecoration;
import com.ucc.ctc.utils.Tools;
import com.ucc.ctc.utils.UpdateClientDialog;
import com.ucc.ctc.viewsModel.ClientViewModel;

import java.util.List;

public class ClientActivity extends AppCompatActivity implements CreateClientDialog.CreateClientListener,
        ClientRecyclerViewAdapter.OnClientClickListner,
        UpdateClientDialog.UpdateClientListener,SearchView.OnQueryTextListener{

    private ClientViewModel clientViewModel;
    private RecyclerView mRecyclerView;
    private ClientRecyclerViewAdapter recyclerViewAdapter;
    private NestedScrollView nested_scroll_view;
    private ImageButton bt_toggle_text, bt_toggle_input;
    private Button bt_hide_text, search_client;
    private View lyt_expand_text, lyt_expand_input,parent,parent_view;
    private EditText searchClientCTCId,searchClientName;
    SearchView editsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_client_main );
        parent_view = findViewById(android.R.id.content);
       // editsearch = (SearchView) findViewById( R.id.search );
       /// editsearch.setOnQueryTextListener(this);
        // Set the search view to be iconified by default
        //editsearch.setIconifiedByDefault(true);
        //  editsearch.setIconifiedByDefault(true);
         // editsearch.setGravity( Gravity.RIGHT|Gravity.CENTER_VERTICAL);
          intToolbar();
          intView();
          initComponent();
        //Observe Client add
        clientViewModel = ViewModelProviders.of(this).get(ClientViewModel.class);
        clientViewModel.getAllClients().observe(this, new Observer<List<ClientEntity>>() {
            @Override
            public void onChanged(List<ClientEntity> clients) {
                recyclerViewAdapter.setClients(clients);
                // Get the total count of records and display it in a TextView:
                int totalCount = recyclerViewAdapter.getItemCount();
                TextView countTextView = findViewById(R.id.total_count_text_view);
                countTextView.setText("Total Records: " + totalCount);
            }
        });
          new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                clientViewModel.delete(recyclerViewAdapter.getClientAt(viewHolder.getAdapterPosition()));
                snackBar("Client Deleted");
            }
        }).attachToRecyclerView(mRecyclerView);
    }

    private void intView() {

        parent = findViewById(android.R.id.content);
        FloatingActionButton fab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new LineItemDecoration(this, LinearLayout.VERTICAL));
        mRecyclerView.setHasFixedSize(true);

        //Set adapter to RecyclerView
        recyclerViewAdapter = new ClientRecyclerViewAdapter();
        recyclerViewAdapter.setItemOnClick(this);
        mRecyclerView.setAdapter(recyclerViewAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openCreateClientDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.client_search_menu,menu);
         MenuItem searchItem = menu.findItem(R.id.action_client_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search...");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_client_search) {
            SearchView searchView = (SearchView) item.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // Handle search query submit
                    performSearch(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    // Handle search query text change
                    performSearch(newText);
                    return false;
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void performSearch(String query) {
        // Handle search query here
        // You can use the query string to search your data source or perform any other action
        if(query!=""){
            Log.d("IN validate ok", query);
            clientViewModel.getClientSearch("%"+query+"%","%"+query+"%").observe(ClientActivity.this, new Observer<List<ClientEntity>>() {
                @Override
                public void onChanged(List<ClientEntity> clients) {
                    recyclerViewAdapter.setClients(clients);
                    // Get the total count of records and display it in a TextView:
                    int totalCount = recyclerViewAdapter.getItemCount();
                    TextView countTextView = findViewById(R.id.total_count_text_view);
                    countTextView.setText("Total Records: " + totalCount);
                }
            });
        }else{
            Log.d("In Valid", query);
        }

    }
    private void openCreateClientDialog() {
        CreateClientDialog createClientDialog = new CreateClientDialog();
        createClientDialog.show(getSupportFragmentManager(),"Create Client");
        /*Intent intent = new Intent(ClientActivity.this, AddEditClientActivity.class);
         startActivity(intent);*/
    }

    private void intToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Clients");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    @Override
    public void saveNewClient(ClientEntity client) {
        clientViewModel.insert(client);
       /* if(client.get=="Yes"){
            //Send to Finger Print Capture
            getFingerPrint();
            //end
*/        snackBar("Client Saved");
    }

    public void snackBar(String message) {
        Snackbar.make(parent, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClientClick(ClientEntity client) {
        Log.d("ClientActivity_Log",""+client.getId());
        //openUpdateClientDialog(client);
        openClientProfile(client);
    }
    //open client profile
    private void openClientProfile(ClientEntity client){

       Intent intent = new Intent(this, ClientProfileActivity.class);


          intent.putExtra("fullName", client.getFullName());
          intent.putExtra("clientId", client.getClientId());

          intent.putExtra("sex", client.getSex());
          intent.putExtra("facilityId", client.getFacilityId());
          startActivity(intent);
        /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new BookFragmet()).commit();*/
    }
    //end
    private void openUpdateClientDialog(ClientEntity client) {
        UpdateClientDialog updateClientDialog = new UpdateClientDialog();
        updateClientDialog.setClient(client);
        updateClientDialog.show(getSupportFragmentManager(),"Update Client");
    }

    @Override
    public void updateNewClient(ClientEntity client) {
        clientViewModel.update(client);
        snackBar("Client Updated");
    }
    private void initComponent() {
       // nested scrollview

    }
    public void getFingerPrint(){
        startActivity(new Intent( ClientActivity.this, ClientFingerPrintActivity.class));
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }


}