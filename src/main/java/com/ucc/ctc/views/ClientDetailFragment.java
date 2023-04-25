package com.ucc.ctc.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.ucc.ctc.R;
import com.ucc.ctc.utils.Tools;
import com.ucc.ctc.utils.ViewAnimation;


public class ClientDetailFragment extends Fragment {



    private View parent_view;
    private NestedScrollView nested_scroll_view;
    private ImageButton bt_toggle_client_address, bt_toggle_treatment_supporter,bt_toggle_community_group;
    private View lyt_expand_client_address, lyt_expand_treatment_supporter,lyt_expand_community_group;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate( R.layout.fragment_client_detail, container, false );
        initComponent(view);
        return view;
    }
    private void initComponent(View view) {
        // Client Address item_section
        bt_toggle_client_address = (ImageButton) view.findViewById(R.id.bt_toggle_client_address);
        lyt_expand_client_address = (View) view.findViewById(R.id.lyt_expand_client_address);
        bt_toggle_client_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSectionAddress(bt_toggle_client_address);
            }
        });


        // Treatment Supporter Section
        bt_toggle_treatment_supporter = (ImageButton) view.findViewById(R.id.bt_toggle_treatment_supporter);
        lyt_expand_treatment_supporter = (View) view.findViewById(R.id.lyt_expand_treatment_supporter);

        bt_toggle_treatment_supporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSectionSupporter(bt_toggle_treatment_supporter);
            }
        });
        // nested scrollview
        nested_scroll_view = (NestedScrollView) view.findViewById(R.id.nested_scroll_view);
    }

    private void toggleSectionAddress(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(lyt_expand_client_address, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    Tools.nestedScrollTo(nested_scroll_view, lyt_expand_client_address);
                }
            });
        } else {
            ViewAnimation.collapse(lyt_expand_client_address);
        }
    }

    private void toggleSectionSupporter(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(lyt_expand_treatment_supporter, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    Tools.nestedScrollTo(nested_scroll_view, lyt_expand_treatment_supporter);
                }
            });
        } else {
            ViewAnimation.collapse(lyt_expand_treatment_supporter);
        }
    }
    private void toggleSectionCommunityGroup(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(lyt_expand_community_group, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    Tools.nestedScrollTo(nested_scroll_view, lyt_expand_community_group);
                }
            });
        } else {
            ViewAnimation.collapse(lyt_expand_community_group);
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
}