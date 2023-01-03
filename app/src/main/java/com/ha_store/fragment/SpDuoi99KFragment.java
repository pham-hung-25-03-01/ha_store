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
import com.ha_store.adapter.SPDuoi99KAdapter;
import com.ha_store.adapter.SPKhuyenMaiAdapter;
import com.ha_store.dao.SanPhamDAO;
import com.ha_store.dto.SanPhamDTO;

import java.util.ArrayList;
import java.util.List;


public class SpDuoi99KFragment extends Fragment {

    private View mView;
    RecyclerView rc_sp_duoi_99k;
    SanPhamDAO sp_dao;
    List<SanPhamDTO> ds_sp_duoi_99k;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sp_duoi_99_k, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_sp_duoi_99k = view.findViewById(R.id.rc_sp_duoi_99k);
        ds_sp_duoi_99k = new ArrayList<>();
        sp_dao = new SanPhamDAO();
        ds_sp_duoi_99k = sp_dao.LayDanhSachSanPhamDuoi99k(null);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(),2,GridLayoutManager.VERTICAL, false);
        SPDuoi99KAdapter spDuoi99KAdapter = new SPDuoi99KAdapter(ds_sp_duoi_99k);
        rc_sp_duoi_99k.setAdapter(spDuoi99KAdapter);
        rc_sp_duoi_99k.setLayoutManager(layoutManager);
        rc_sp_duoi_99k.setHasFixedSize(true);
    }
}