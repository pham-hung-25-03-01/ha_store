package com.ha_store.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.ha_store.R;
import com.ha_store.dto.AnhSanPhamDTO;
import com.ha_store.dto.Photo;


public class AnhSPFragment extends Fragment {

    private View mView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mView = inflater.inflate(R.layout.fragment_anh_sp, container, false);
       Bundle bundle = getArguments();
        AnhSanPhamDTO photo = (AnhSanPhamDTO) bundle.get("photo");
        Context context = mView.getContext();
        ShapeableImageView item_slider = mView.findViewById(R.id.anh_sp);
        Glide.with(context).load(photo.getAnh_san_pham_url()).placeholder(R.drawable.ic_launcher_background).into(item_slider);
       return mView;
    }
}