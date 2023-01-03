package com.ha_store.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ha_store.fragment.SpChoBanFragment;
import com.ha_store.fragment.SpDuoi99KFragment;
import com.ha_store.fragment.SpKhuyenMaiFragment;


public class OptionProductPageAdapter extends FragmentStateAdapter {


    public OptionProductPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new SpChoBanFragment();
            case 1:
                return new SpKhuyenMaiFragment();

            case 2:
                return new SpDuoi99KFragment();

            case 3:
                return new SpChoBanFragment();

            default:
                return new SpChoBanFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }


}
