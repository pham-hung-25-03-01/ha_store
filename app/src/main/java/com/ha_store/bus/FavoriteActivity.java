package com.ha_store.bus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.ha_store.R;
import com.ha_store.adapter.DiscountAdapter;
import com.ha_store.adapter.FavoriteAdapter;
import com.ha_store.dao.KhachHangDAO;
import com.ha_store.dao.YeuThichDAO;
import com.ha_store.dto.SanPhamDTO;
import com.ha_store.dto.YeuThichDTO;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    RecyclerView r_yeu_thich;
    List<SanPhamDTO> ds_sp;
    FavoriteAdapter adapter;
    AppCompatImageButton btn_back;
    YeuThichDAO yeuThichDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        Init();
        yeuThichDAO = new YeuThichDAO();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(FavoriteActivity.this, AccountActivity.class));
            }
        });
        setDSSP();
    }
    public void Init(){
        r_yeu_thich = findViewById(R.id.r_yeu_thich);
        btn_back = findViewById(R.id.btn_back);
    }
    private void setDSSP() {
        ds_sp = yeuThichDAO.LayDanhSachSanPhamYeuThichTheoKhachHangId(KhachHangDAO.khach_hang_hien_tai.get_id());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL, false);
        adapter = new FavoriteAdapter(ds_sp);
        r_yeu_thich.setAdapter(adapter);
        r_yeu_thich.setLayoutManager(layoutManager);
        r_yeu_thich.setHasFixedSize(true);
        set_swipe_recycle_view(r_yeu_thich);
    }
    public void set_swipe_recycle_view(RecyclerView rv){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                hienDiaLogHuyYeuThich(viewHolder);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rv);
    }
    private void hienDiaLogHuyYeuThich(RecyclerView.ViewHolder viewHolder) {
        AlertDialog.Builder builder = new AlertDialog.Builder(FavoriteActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Thông báo");
        builder.setMessage("Hủy yêu thích?");
        builder.setPositiveButton("Có",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SanPhamDTO sp_xoa = ds_sp.get(viewHolder.getAdapterPosition());
                        int position = viewHolder.getAdapterPosition();
                        ds_sp.remove(position);
                        yeuThichDAO.XoaYeuThich(KhachHangDAO.khach_hang_hien_tai.get_id(), sp_xoa.get_id());
                        adapter.notifyDataSetChanged();
                        Snackbar.make(r_yeu_thich, sp_xoa.get_ten_san_pham(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                yeuThichDAO.ThemYeuThich(new YeuThichDTO(
                                        KhachHangDAO.khach_hang_hien_tai.get_id(),
                                        sp_xoa.get_id(),
                                        1
                                ));
                                ds_sp.add(position, sp_xoa);
                                adapter.notifyDataSetChanged();
                            }
                        }).show();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.notifyDataSetChanged();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}