package com.example.botanibelumdaily.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.botanibelumdaily.MainMenu;
import com.example.botanibelumdaily.Models.TanamanList;
import com.example.botanibelumdaily.Models.User;
import com.example.botanibelumdaily.R;
import com.example.botanibelumdaily.ui.notifications.NotificationsFragment;
import com.example.botanibelumdaily.ui.notifications.NotificationsViewModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class HomeFragment extends Fragment {

    View myFragment;

    FirebaseAuth mAuth;

    User userdat;

    MainMenu toket;

    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<TanamanList, HomeViewHolder> mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public HomeFragment(){

    }

    public static HomeFragment newInstances(){
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        myFragment = inflater.inflate(R.layout.fragment_home,container,false);

        MainMenu activity = (MainMenu) getActivity();

        String token = activity.getToken();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("user").child(token).child("tanaman");

        recyclerView = myFragment.findViewById(R.id.list_tanaman_user);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(container.getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        Query query = mDatabase;

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<TanamanList>()
                .setQuery(query, TanamanList.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<TanamanList, HomeViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull HomeViewHolder homeViewHolder, int i, @NonNull TanamanList tanamanList) {
                homeViewHolder.bindToTanaman(tanamanList);
                Glide.with(HomeFragment.this).load(tanamanList.getGambar_list_tanaman()).into(homeViewHolder.imageListTanaman);

                homeViewHolder.cardListTanaman.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), AlarmActivity.class);

                        startActivity(intent);
                    }
                });


            }

            @NonNull
            @Override
            public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                LayoutInflater inflater = LayoutInflater.from(parent.getContext());

                return new HomeViewHolder(inflater.inflate(R.layout.item_list_tanaman, parent, false));
            }
        };

        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);

        if(mAdapter != null){
            mAdapter.startListening();
        }

        System.out.println(mAdapter);

        FloatingActionButton buttonAdd = (FloatingActionButton) myFragment.findViewById(R.id.nambah);

        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TambahListTanaman.class);

                intent.putExtra("token", token);
                startActivity(intent);
            }
        });



        return myFragment;
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAdapter != null){
            mAdapter.stopListening();
        }
    }
}