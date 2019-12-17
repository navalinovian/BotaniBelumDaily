package com.example.botanibelumdaily.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.botanibelumdaily.Models.TanamanRinci;
import com.example.botanibelumdaily.R;

public class TanamanRinciViewHolder extends RecyclerView.ViewHolder {

    public TextView NamaTanaman;
    public TextView DesTanaman;
    public TextView WaktuTanaman;
    public ImageView GambarTanaman;

    public TanamanRinciViewHolder(@NonNull View itemView) {
        super(itemView);
        NamaTanaman = itemView.findViewById(R.id.nama_tanaman);
        DesTanaman = itemView.findViewById(R.id.deskripsi_tanaman);
        WaktuTanaman = itemView.findViewById(R.id.waktu_tanaman);
        GambarTanaman = itemView.findViewById(R.id.gambar_tanaman);
    }

    public void bindToTanamanRinci(TanamanRinci tari){
        NamaTanaman.setText(tari.getNama());
        DesTanaman.setText(tari.getDeskripsi());
        WaktuTanaman.setText(String.valueOf(tari.getWaktu()));
//        ivGambar.setImageResource(tanaman.gambar);
    }
}
