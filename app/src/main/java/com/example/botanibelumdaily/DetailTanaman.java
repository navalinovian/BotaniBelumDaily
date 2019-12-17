package com.example.botanibelumdaily;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.botanibelumdaily.Models.TanamanRinci;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DetailTanaman extends AppCompatActivity {

    String nmTanaman;
    String jnsTanaman;
    TextView namaTanaman, deskripsi, waktu;
    ImageView imgTnaman;

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<TanamanRinci, TanamanViewHolder> mAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tanaman);

        Intent intent = getIntent();
        nmTanaman = intent.getStringExtra("nama");
        jnsTanaman = intent.getStringExtra("jenis");
        deskripsi = findViewById(R.id.deskripsi_tanaman);
        namaTanaman = findViewById(R.id.nama_tanaman);
        waktu   = findViewById(R.id.waktu_tanaman);
        imgTnaman = findViewById(R.id.gambar_tanaman);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("macam").child(jnsTanaman).child(nmTanaman);


        Query query = mDatabase;

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                namaTanaman.setText(dataSnapshot.child("nama").getValue().toString());
                deskripsi.setText(dataSnapshot.child("deskripsi").getValue().toString());
                waktu.setText(dataSnapshot.child("waktu").getValue().toString());
                Glide.with(DetailTanaman.this).load(dataSnapshot.child("gambar").getValue()).into(imgTnaman);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
