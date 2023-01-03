package com.ha_store.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ha_store.R;
import com.ha_store.adapter.SPKhuyenMaiAdapter;
import com.ha_store.adapter.SPTotNhatAdapter;
import com.ha_store.dao.SanPhamDAO;
import com.ha_store.dto.SanPhamDTO;

import java.util.ArrayList;
import java.util.List;


public class SpTotNhatFragment extends Fragment {

    private View mView;
    RecyclerView rc_sp_tot_nhat;
    SanPhamDAO sp_dao;
    List<SanPhamDTO> ds_sp_tot_nhat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sp_tot_nhat, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_sp_tot_nhat = view.findViewById(R.id.rc_sp_tot_nhat);
        ds_sp_tot_nhat = new ArrayList<>();
        sp_dao =new SanPhamDAO();
        ds_sp_tot_nhat = sp_dao.LayDanhSachSanPhamBanChay(null);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(),2,GridLayoutManager.VERTICAL, false);
        SPTotNhatAdapter spTotNhatAdapter = new SPTotNhatAdapter(ds_sp_tot_nhat);
        rc_sp_tot_nhat.setAdapter(spTotNhatAdapter);
        rc_sp_tot_nhat.setLayoutManager(layoutManager);
        rc_sp_tot_nhat.setHasFixedSize(true);
    }
}