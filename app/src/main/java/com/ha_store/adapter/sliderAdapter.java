package com.ha_store.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.ha_store.dto.Photo;
import com.ha_store.fragment.SliderFragment;

import java.util.List;

public class sliderAdapter extends FragmentStateAdapter {
    List<Photo> photoList;

    public sliderAdapter(@NonNull FragmentActivity fragmentActivity, List<Photo> photoList) {
        super(fragmentActivity);
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Photo photo =photoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("photo",photo);
        SliderFragment sliderFragment = new SliderFragment();
        sliderFragment.setArguments(bundle);
        return sliderFragment;
    }

    @Override
    public int getItemCount() {
        if (photoList!=null)
            return photoList.size();
        return 0;
    }
}
