package com.example.botanibelumdaily;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Tanaman {

    public String nama;
    public String deskripsi;
    public int waktu;
    public String gambar;

    public Tanaman() {
    }

    public Tanaman(String nama, String deskripsi, int waktu, String gambar) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.waktu = waktu;
        this.gambar = gambar;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getWaktu() {
        return waktu;
    }

    public void setWaktu(int waktu) {
        this.waktu = waktu;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nama", nama);
        result.put("deskripsi", deskripsi);
        result.put("waktu", waktu);
        result.put("gambar", gambar);
        return result;
    }

}
