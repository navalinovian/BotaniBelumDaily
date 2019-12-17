package com.example.botanibelumdaily.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.botanibelumdaily.ListTanaman;
import com.example.botanibelumdaily.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    Button btnAkar, btnBatang, btnDaun, btnBunga, btnBuah;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        btnAkar = root.findViewById(R.id.btn_akar);
        btnBatang = root.findViewById(R.id.btn_batang);
        btnDaun = root.findViewById(R.id.btn_daun);
        btnBunga = root.findViewById(R.id.btn_bunga);
        btnBuah = root.findViewById(R.id.btn_buah);

        btnAkar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListTanaman.class);
                intent.putExtra("jenis", "akar");
                startActivity(intent);
            }
        });

        btnBatang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListTanaman.class);
                intent.putExtra("jenis", "batang");
                startActivity(intent);
            }
        });

        btnDaun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListTanaman.class);
                intent.putExtra("jenis", "daun");
                startActivity(intent);
            }
        });

        btnBunga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListTanaman.class);
                intent.putExtra("jenis", "bunga");
                startActivity(intent);
            }
        });

        btnBuah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListTanaman.class);
                intent.putExtra("jenis", "buah");
                startActivity(intent);
            }
        });

        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}