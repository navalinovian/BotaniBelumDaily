package com.example.botanibelumdaily;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TanamanViewHolder extends RecyclerView.ViewHolder {

    public TextView tvNama;
    public TextView tvDeskripsi;
    public TextView tvWaktu;
    public ImageView ivGambar;


    public TanamanViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNama = itemView.findViewById(R.id.tv_nama);
        tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);
        tvWaktu = itemView.findViewById(R.id.tv_waktu);
        ivGambar = itemView.findViewById(R.id.iv_gambar);
    }

    public void bindToTanaman(Tanaman tanaman){
        tvNama.setText(tanaman.nama);
        tvDeskripsi.setText(tanaman.deskripsi);
        tvWaktu.setText(String.valueOf(tanaman.waktu));
//        ivGambar.setImageResource(tanaman.gambar);
    }
}
