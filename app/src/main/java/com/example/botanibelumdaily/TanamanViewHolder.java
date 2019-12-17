package com.example.botanibelumdaily;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.botanibelumdaily.Interface.ItemClickListener;

public class TanamanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tvNama;
    public ImageView ivGambar;
    private ItemClickListener itemClickListener;


    public TanamanViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNama = itemView.findViewById(R.id.tanaman_name);
        ivGambar = itemView.findViewById(R.id.tanaman_image);
    }

    public void bindToTanaman(Tanaman tanaman){
        tvNama.setText(tanaman.nama);
//        ivGambar.setImageResource(tanaman.gambar);
    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view){
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
