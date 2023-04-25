package com.ucc.ctc.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ucc.ctc.views.ClientDetailFragment;
import com.ucc.ctc.views.VisitDetailFragment;

public class ProfileViewPagerAdapter  extends FragmentStateAdapter {


    public ProfileViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super( fragmentActivity );
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ClientDetailFragment();
            case 1:
                return new VisitDetailFragment();
            default:
                return new ClientDetailFragment();
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return 2;
    }
}
