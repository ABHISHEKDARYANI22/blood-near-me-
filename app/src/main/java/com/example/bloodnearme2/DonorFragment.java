package com.example.bloodnearme2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bloodnearme2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonorFragment extends Fragment {
     String  uEmail,uStatus,id;
     String checkEmail="";
    Switch switchname;
    user u;
        String name,emailid,phonenumber,gender,dob,bloodgroup,city,password;
    DatabaseReference myreff;
    FirebaseAuth firebaseAuth;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View v  =  inflater.inflate(R.layout.fragment_donor,null);
        // for database


        firebaseAuth = FirebaseAuth.getInstance();
        switchname = v.findViewById(R.id.switchname);
        myreff = FirebaseDatabase.getInstance().getReference();

        final FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        final DatabaseReference usrRef=myreff.child("users");
        checkEmail = user.getEmail();

        ValueEventListener userListener = new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {

                    uEmail = ds.child("emailid").getValue(String.class);
                    assert uEmail != null;

                    if(uEmail.equals(checkEmail))
                    {
                        id= ds.child("id").getValue().toString();
                        uStatus = ds.child("donorstatus").getValue(String.class);
                        name = ds.child("name").getValue().toString();
                        emailid = ds.child("emailid").getValue().toString();
                        phonenumber = ds.child("phonenumber").getValue().toString();
                        gender = ds.child("gender").getValue().toString();
                        dob = ds.child("dob").getValue().toString();
                        bloodgroup = ds.child("bloodgroup").getValue().toString();
                        city = ds.child("city").getValue().toString();
                        password = ds.child("password").getValue().toString();
                        /**/

                        if (uStatus.equals("yes"))
                        {
                            switchname.setChecked(true);
                            Toast.makeText(getActivity(), "YOU ARE A DONOR", Toast.LENGTH_SHORT).show();
                        }
                        if (uStatus.equals("no"))
                        {
                            switchname.setChecked(false);
                            Toast.makeText(getActivity(), "YOU ARE NOT A DONOR", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){
                Toast.makeText(getActivity(), "error occurred", Toast.LENGTH_SHORT).show();
            }
        };
        usrRef.addListenerForSingleValueEvent(userListener);

         u  = new user();

        switchname.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {


               if (isChecked== true)
               {
                   u.setDonorstatus("yes");
                       u.setDob(dob);
                       u.setBloodgroup(bloodgroup);
                       u.setCity(city);
                       u.setEmailid(emailid);
                       u.setName(name);
                       u.setPhonenumber(phonenumber);
                       u.setGender(gender);
                       u.setPassword(password);
                       u.setId(id);
                   Toast.makeText(getActivity(), "CONGRATULATIONS.!! YOU ARE A DONOR", Toast.LENGTH_SHORT).show();
               }
               if (isChecked==false)
               {
                   u.setDonorstatus("no");
                   u.setDob(dob);
                   u.setBloodgroup(bloodgroup);
                   u.setCity(city);
                   u.setEmailid(emailid);
                   u.setName(name);
                   u.setPhonenumber(phonenumber);
                   u.setGender(gender);
                   u.setPassword(password);
                   u.setId(id);
                   Toast.makeText(getActivity(), "NOW YOU ARE NOT A DONOR", Toast.LENGTH_SHORT).show();
               }
                usrRef.child(id).setValue(u);

            }
        });



        return v;
    }

}