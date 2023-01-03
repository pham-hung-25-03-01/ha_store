package com.ha_store.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ha_store.R;
import com.ha_store.adapter.SPChoBanAdapter;
import com.ha_store.dao.SanPhamDAO;
import com.ha_store.dto.SanPhamDTO;

import java.util.ArrayList;
import java.util.List;


public class SpChoBanFragment extends Fragment {

    private View mView;
    RecyclerView rc_sp_cho_ban;
    SanPhamDAO sp_dao;
    List<SanPhamDTO> ds_sp_cho_ban;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sp_cho_ban, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_sp_cho_ban = view.findViewById(R.id.rc_sp_cho_ban);
        ds_sp_cho_ban = new ArrayList<>();
        sp_dao =new SanPhamDAO();
        ds_sp_cho_ban = sp_dao.LayDanhSachSanPhamMoi(null);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(),2,GridLayoutManager.VERTICAL, false);
        SPChoBanAdapter spChoBanAdapter = new SPChoBanAdapter(ds_sp_cho_ban);
        rc_sp_cho_ban.setAdapter(spChoBanAdapter);
        rc_sp_cho_ban.setLayoutManager(layoutManager);
        rc_sp_cho_ban.setHasFixedSize(true);
    }
}