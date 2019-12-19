package com.example.botanibelumdaily.ui.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.botanibelumdaily.Models.TanamanList;
import com.example.botanibelumdaily.Models.TanamanRinci;
import com.example.botanibelumdaily.R;

public class HomeViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageListTanaman;
    public TextView namaListTanaman;
    public TextView desListTanaman;
    public TextView waktuListTanaman;

    public CardView cardListTanaman;


    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);

        imageListTanaman = itemView.findViewById(R.id.list_gambar_tanaman);

        namaListTanaman = itemView.findViewById(R.id.list_nama_tanaman);
        desListTanaman = itemView.findViewById(R.id.list_deskripsi_tanaman);
        waktuListTanaman = itemView.findViewById(R.id.list_waktu_tanaman);

        cardListTanaman = itemView.findViewById(R.id.cardListTanaman);
    }

    public void bindToTanaman(TanamanList tanamanList){
        namaListTanaman.setText(tanamanList.nama_list_tanaman);
        desListTanaman.setText(tanamanList.deskripsi_list_tanaman);
        waktuListTanaman.setText(String.valueOf(tanamanList.waktu_list_tanaman));
    }
}
