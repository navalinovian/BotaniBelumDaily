package com.example.botanibelumdaily.Models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class TanamanList {

    public String nama_list_tanaman;
    public String deskripsi_list_tanaman;
    public int waktu_list_tanaman;
    public String gambar_list_tanaman;

    public TanamanList(){

    }

    public TanamanList(String nama_list_tanaman, String deskripsi_list_tanaman, int waktu_list_tanaman, String gambar_list_tanaman) {
        this.nama_list_tanaman = nama_list_tanaman;
        this.deskripsi_list_tanaman = deskripsi_list_tanaman;
        this.waktu_list_tanaman = waktu_list_tanaman;
        this.gambar_list_tanaman = gambar_list_tanaman;
    }

    public String getNama_list_tanaman() {
        return nama_list_tanaman;
    }

    public void setNama_list_tanaman(String nama_list_tanaman) {
        this.nama_list_tanaman = nama_list_tanaman;
    }

    public String getDeskripsi_list_tanaman() {
        return deskripsi_list_tanaman;
    }

    public void setDeskripsi_list_tanaman(String deskripsi_list_tanaman) {
        this.deskripsi_list_tanaman = deskripsi_list_tanaman;
    }

    public int getWaktu_list_tanaman() {
        return waktu_list_tanaman;
    }

    public void setWaktu_list_tanaman(int waktu_list_tanaman) {
        this.waktu_list_tanaman = waktu_list_tanaman;
    }

    public String getGambar_list_tanaman() {
        return gambar_list_tanaman;
    }

    public void setGambar_list_tanaman(String gambar_list_tanaman) {
        this.gambar_list_tanaman = gambar_list_tanaman;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nama_list_tanaman", nama_list_tanaman);
        result.put("deskripsi_list_tanaman", deskripsi_list_tanaman);
        result.put("waktu_list_tanaman", waktu_list_tanaman);
        result.put("gambar_list_tanaman", gambar_list_tanaman);
        return result;
    }

}
