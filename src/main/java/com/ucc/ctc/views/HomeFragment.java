package com.ucc.ctc.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.ucc.ctc.R;
import com.ucc.ctc.adapters.ClientRecyclerViewAdapter;
import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.viewsModel.ClientViewModel;
import com.ucc.ctc.viewsModel.DashboardViewModel;

import java.util.List;


public class HomeFragment extends Fragment {
    private DashboardViewModel clientViewModel;
    private RecyclerView mRecyclerView;
    private ClientRecyclerViewAdapter recyclerViewAdapter;
    ListView lst;

    String[] itemName = {
            "Item1",

    };

    String[] itemDesc = {
            "Descr"
    };

    int[] itemImg = {
            R.drawable.sky,
            R.drawable.beach,
            R.drawable.bridge,
            R.drawable.city,
            R.drawable.mountain,
            R.drawable.plage
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View views = inflater.inflate(R.layout.fragment_home,container, false);
        //Observe Client add
        clientViewModel = ViewModelProviders.of(this).get( DashboardViewModel.class);
        clientViewModel.getClientCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer totalCount) {
                 // Get the total count of records and display it in a TextView:
                 TextView countTextView = views.findViewById(R.id.total_count_text_view);
                countTextView.setText(""+totalCount);
            }
        });
    return views;
    }
}
