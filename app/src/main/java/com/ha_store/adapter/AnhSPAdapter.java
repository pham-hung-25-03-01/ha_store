package com.ha_store.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ha_store.dto.AnhSanPhamDTO;
import com.ha_store.fragment.AnhSPFragment;

import java.util.List;

public class AnhSPAdapter extends FragmentStateAdapter {
    List<AnhSanPhamDTO> ds_anh_sp;

    public AnhSPAdapter(@NonNull FragmentActivity fragmentActivity, List<AnhSanPhamDTO> ds_anh_sp) {
        super(fragmentActivity);
        this.ds_anh_sp = ds_anh_sp;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        AnhSanPhamDTO photo = ds_anh_sp.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("photo",photo);
        AnhSPFragment anhSPFragment = new AnhSPFragment();
        anhSPFragment.setArguments(bundle);
        return anhSPFragment;
    }

    @Override
    public int getItemCount() {
        if (ds_anh_sp!=null)
            return ds_anh_sp.size();
        return 0;
    }
}
