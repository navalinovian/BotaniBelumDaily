package com.example.botanibelumdaily.ui.notifications;

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

import com.example.botanibelumdaily.MainActivity;
import com.example.botanibelumdaily.MainMenu;
import com.example.botanibelumdaily.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    Button btnLogout;
    GoogleSignInClient mGoogleClient;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        btnLogout = root.findViewById(R.id.btn_logout);
        MainMenu activity = (MainMenu) getActivity();
        mGoogleClient = activity.getMyGoogleData();
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s)
            {

            }
        });
        btnLogout.setOnClickListener(v -> Logout());
        return root;
    }

    private void Logout() {
        FirebaseAuth.getInstance().signOut();
        mGoogleClient.signOut();
        Intent intent2 =  new Intent(getActivity(), MainActivity.class);
        startActivity(intent2);
    }
}