package com.ha_store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ha_store.R;
import com.ha_store.dto.Option;

import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder> {
    List<Option> options;

    public OptionAdapter(List<Option> options) {
        this.options = options;
    }

    @NonNull
    @Override
    public OptionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView = inflater.inflate(R.layout.item_option,parent,false);
        ViewHolder viewHolder = new ViewHolder(categoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OptionAdapter.ViewHolder holder, int position) {
        Option option = options.get(position);
        holder.option_title.setText(option.getTitle());
        Glide.with(holder.context).load(option.getUrl_img()).placeholder(R.drawable.ic_launcher_background).into(holder.option_img);
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView option_img;
        TextView option_title;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            option_img = itemView.findViewById(R.id.option_img);
            option_title = itemView.findViewById(R.id.option_title);
            context = itemView.getContext();
        }
    }
}
