package com.example.bloodnearme2;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class usercount extends Fragment
{

    String uEmail;
    int count = 0;
    DatabaseReference myreff,usrRef;
    TextView tvcount;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final ProgressDialog pd = ProgressDialog.show(getActivity(), "", "Counting", true);
        View v = inflater.inflate(R.layout.fragment_usercount, container, false);
        myreff = FirebaseDatabase.getInstance().getReference();
        usrRef=myreff.child("users");
        tvcount = v.findViewById(R.id.countno);
        ValueEventListener userListener = new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                for (DataSnapshot ds : dataSnapshot.getChildren())
                {

                    uEmail = ds.child("emailid").getValue(String.class);



                    if (!uEmail.equals("xxx@gmail.com")) {
                            count++;
                    }


                }
                tvcount.setText(Integer.toString(count));
                pd.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pd.dismiss();
                Toast.makeText(getActivity(), "error occured", Toast.LENGTH_SHORT).show();

            }

        };


        usrRef.addListenerForSingleValueEvent(userListener);


        return v;
    }

}