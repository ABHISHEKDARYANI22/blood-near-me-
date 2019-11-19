package com.example.bloodnearme2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bloodnearme2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonorFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View v  =  inflater.inflate(R.layout.fragment_donor,null);

        return v;
    }

    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // for database
        FirebaseAuth firebaseAuth;

        firebaseAuth = FirebaseAuth.getInstance();


        final Button btnon,btnoff ;
        btnon = view.findViewById(R.id.buttonon);
        btnoff = view.findViewById(R.id.buttonoff);
        btnon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnon.setBackgroundResource(R.color.green);
                btnoff.setBackgroundResource(R.color.red);
                Toast.makeText(getActivity(), "ON", Toast.LENGTH_SHORT).show();
                DatabaseReference databaseusers;
                databaseusers = FirebaseDatabase.getInstance().getReference("users");
                String id = databaseusers.push().getKey();
                user u = new user(id,null,null,null,null,null,null,null,null,"1");


            }
        });


        btnoff.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                btnoff.setBackgroundResource(R.color.green);
                btnon.setBackgroundResource(R.color.red);
                Toast.makeText(getActivity(), "OFF", Toast.LENGTH_SHORT).show();
                DatabaseReference databaseusers;
                databaseusers = FirebaseDatabase.getInstance().getReference("users");
                String id = databaseusers.push().getKey();
                user u = new user(id,null,null,null,null,null,null,null,null,"0");
            }
        });
    }
}