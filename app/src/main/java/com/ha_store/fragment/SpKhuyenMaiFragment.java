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
import com.ha_store.adapter.SPChoBanAdapter;
import com.ha_store.adapter.SPKhuyenMaiAdapter;
import com.ha_store.dao.SanPhamDAO;
import com.ha_store.dto.SanPhamDTO;

import java.util.ArrayList;
import java.util.List;


public class SpKhuyenMaiFragment extends Fragment {

    private View mView;
    RecyclerView rc_sp_khuyen_mai;
    SanPhamDAO sp_dao;
    List<SanPhamDTO> ds_sp_khuyen_mai;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sp_khuyen_mai, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_sp_khuyen_mai = view.findViewById(R.id.rc_sp_khuyen_mai);
        ds_sp_khuyen_mai = new ArrayList<>();
        sp_dao =new SanPhamDAO();
        ds_sp_khuyen_mai = sp_dao.LayDanhSachSanPhamGiamGia(null);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(),2,GridLayoutManager.VERTICAL, false);
        SPKhuyenMaiAdapter spKhuyenMaiAdapter = new SPKhuyenMaiAdapter(ds_sp_khuyen_mai);
        rc_sp_khuyen_mai.setAdapter(spKhuyenMaiAdapter);
        rc_sp_khuyen_mai.setLayoutManager(layoutManager);
        rc_sp_khuyen_mai.setHasFixedSize(true);
    }
}