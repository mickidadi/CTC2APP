
package com.ucc.ctc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucc.ctc.R;
import com.ucc.ctc.models.entity.ClientEntity;

import java.util.ArrayList;
import java.util.List;

public class ClientRecyclerViewAdapter extends RecyclerView.Adapter<ClientRecyclerViewAdapter.RecyclerViewHolder>{

        private List<ClientEntity> clients = new ArrayList<>();
        private OnClientClickListner onClientClickListner;

        public void setClients(List<ClientEntity> clients) {
            this.clients = clients;
            notifyDataSetChanged();
        }

        public void setItemOnClick(OnClientClickListner onClientClickListner){
            this.onClientClickListner = onClientClickListner;
        }

        public ClientEntity getClientAt(int position)
        {
            ClientEntity client = clients.get(position);
            client.setId(clients.get(position).getId());
            return client;
        }


        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_recyclerview_layout,null);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,onClientClickListner);
            return recyclerViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

            ClientEntity currentClient = clients.get(position);

            holder.fullName.setText(currentClient.getFullName()+"("+currentClient.getSex()+")");
           // holder.sex.setText("Client Id: " +currentClient.getClientId());
            holder.clientId.setText("Client Id: " +currentClient.getClientId());

        }

        @Override
        public int getItemCount() {
            return clients.size();
        }

        class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


            private TextView fullName;
            private TextView sex;
            private TextView clientId;
            private OnClientClickListner mListener;

            public RecyclerViewHolder(@NonNull View itemView, OnClientClickListner onClientClickListner) {
                super(itemView);
                this.mListener = onClientClickListner;
                itemView.setOnClickListener(this);
                fullName = itemView.findViewById( R.id.fullName);
                //sex = itemView.findViewById(R.id.sex);
                clientId = itemView.findViewById(R.id.clientId);

            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                ClientEntity currentClient = clients.get(position);
                //String clientId, String firstName, String middleName, String lastName, String sex, Date dateOfBirth,
                // String referenceCode, String facilityId
                ClientEntity client = new ClientEntity(currentClient.getClientId(),currentClient.getFirstName(),
                        currentClient.getMiddleName(),currentClient.getLastName(),currentClient.getSex(),currentClient.getDateOfBirth()
                ,currentClient.getReferenceCode(),currentClient.getFacilityId(),currentClient.getFingerPrintConcept());
                client.setId(currentClient.getId());
                mListener.onClientClick(client);
            }
        }

        public interface OnClientClickListner{
            void onClientClick(ClientEntity client);
        }
    }