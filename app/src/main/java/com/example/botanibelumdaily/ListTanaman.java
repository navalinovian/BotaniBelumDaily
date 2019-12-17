package com.example.botanibelumdaily;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.botanibelumdaily.Interface.ItemClickListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ListTanaman extends AppCompatActivity {

    String jenis;
    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<Tanaman, TanamanViewHolder> mAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tanaman);
        Intent intent = getIntent();
        jenis = intent.getStringExtra("jenis");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("macam").child(jenis);

        recyclerView = findViewById(R.id.list_tanaman);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        Query query = mDatabase;

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Tanaman>()
                .setQuery(query, Tanaman.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Tanaman, TanamanViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TanamanViewHolder tanamanViewHolder, int i, @NonNull Tanaman tanaman) {
                tanamanViewHolder.bindToTanaman(tanaman);
                Glide.with(ListTanaman.this).load(tanaman.getGambar()).into(tanamanViewHolder.ivGambar);
                tanamanViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent tanamanKlik = new Intent(ListTanaman.this,DetailTanaman.class);
                        tanamanKlik.putExtra("nama", tanaman.getNama());
                        tanamanKlik.putExtra("jenis", jenis);
                        startActivity(tanamanKlik);
                    }
                });

//                tanamanViewHolder.setItemClickListener(new  ItemClickListener() {
//                    @Override
//                    public void onClick(View view, int position, boolean isLongClick) {
////                        //Toast.makeText(getActivity(), String.format("%s|%s", adapter.getRef(position).getKey(), model.getName()), Toast.LENGTH_SHORT).show();
//                        Intent tanamanKlik = new Intent(ListTanaman.this,DetailTanaman.class);
//                        tanamanKlik.putExtra("nama", tanaman.getNama());
//                        tanamanKlik.putExtra("jenis", jenis);
//                        startActivity(tanamanKlik);
//
//                    }
//                });

            }

            @NonNull
            @Override
            public TanamanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new TanamanViewHolder(inflater.inflate(R.layout.item_pilih_tanaman, parent, false));
            }
        };

        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);

        if (mAdapter != null) {
            mAdapter.startListening();
        }

        System.out.println(mAdapter);

    }
}
