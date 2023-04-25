package com.ucc.ctc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ucc.ctc.R;
import com.ucc.ctc.models.entity.ClientEntity;

import java.util.ArrayList;
import java.util.List;

public class ClientMainRecyclerViewAdapter extends ListAdapter<ClientEntity, ClientMainRecyclerViewAdapter.ClientHolder> {

    private OnItemClickListener listener;
    private List<ClientEntity> clients = new ArrayList<>();

    public ClientMainRecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<ClientEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<ClientEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull ClientEntity oldItem, @NonNull ClientEntity newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ClientEntity oldItem, @NonNull ClientEntity newItem) {
            return oldItem.getFullName().equals(newItem.getFullName()) &&
                    oldItem.getFirstName().equals(newItem.getFirstName());
        }
    };

    @NonNull
    @Override
    public ClientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate( R.layout.client_item, parent, false);
        return new ClientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientHolder holder, int position) {
        ClientEntity currentClient = getItem(position);
        holder.textViewFullName.setText(currentClient.getFullName());
        holder.textViewClientId.setText(currentClient.getClientId());
    }

    public ClientEntity getClientAt(int position) {
        return getItem(position);
    }

    class ClientHolder extends RecyclerView.ViewHolder {
        private TextView textViewFullName;
        private TextView textViewClientId;

        public ClientHolder(View itemView) {
            super(itemView);
            textViewFullName = itemView.findViewById(R.id.text_view_full_name);
            textViewClientId = itemView.findViewById(R.id.text_view_client_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ClientEntity client);
    }
    public void setClients(List<ClientEntity> clients) {
        this.clients = clients;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
