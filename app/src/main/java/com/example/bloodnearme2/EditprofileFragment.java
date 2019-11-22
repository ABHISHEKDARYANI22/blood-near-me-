package com.example.bloodnearme2;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

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

public class EditprofileFragment extends Fragment {
    String checkEmail,uEmail,uName,uphonenumber,uspinnerbg,uspinnercity,upassword,gen,dob;
    DatePickerDialog.OnDateSetListener mDateSetListner;
    RadioButton gender;
    String donor;
    RadioGroup radioGroup;
    Spinner spinnerbg,spinnercity;
     EditText etnameedit,etpassword,etphonenumber,etdob;
     TextView tvemailid;
     TextView bgname,cityname,tvgender;
     String bloodgroup,city;
    Button btnupdate;
    DatabaseReference myreff;
    View v;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_editprofile, null);




         etnameedit = v.findViewById(R.id.etname);
         etpassword = v.findViewById(R.id.etpassword);
         tvemailid = v.findViewById(R.id.tvemailid);
         etphonenumber = v.findViewById(R.id.etphonenumber);
         spinnerbg = v.findViewById(R.id.spinnerbgedit);
         spinnercity  =v.findViewById(R.id.spinnercityedit);
         radioGroup = v.findViewById(R.id.radiogroup);
         bgname = v.findViewById(R.id.bgname);
         etdob = v.findViewById(R.id.etdob);
         cityname = v.findViewById(R.id.cityname);
            tvgender = v.findViewById(R.id.tvgender);


        myreff = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        DatabaseReference usrRef=myreff.child("users");
        checkEmail = user.getEmail();


        ValueEventListener userListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    uEmail = ds.child("emailid").getValue(String.class);


                    assert uEmail != null;
                    if(uEmail.equals(checkEmail))
                    {
                        uName = ds.child("name").getValue(String.class);
                        uspinnerbg= ds.child("bloodgroup").getValue().toString();
                        uspinnercity = ds.child("city").getValue().toString();
                        uphonenumber = ds.child("phonenumber").getValue().toString();
                        upassword = ds.child("password").getValue().toString();
                        gen = ds.child("gender").getValue().toString();
                        dob = ds.child("dob").getValue().toString();
                        break;
                    }

                }




                etnameedit.setText(uName);
                tvemailid.setText(uEmail);
                etpassword.setText(upassword);
                etphonenumber.setText(uphonenumber);
                bgname.setText(uspinnerbg);
                cityname.setText(uspinnercity);
                tvgender.setText(gen);
                etdob.setText(dob);


                //now for the spinner


                //spinner layout for blood group

                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.bloodgroup, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerbg.setAdapter(adapter);
                spinnerbg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // to get the blood group
                       bloodgroup = parent.getItemAtPosition(position).toString();
                       bgname.setText(bloodgroup);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                //spinner layout for city



                ArrayAdapter<CharSequence> adapter1  = ArrayAdapter.createFromResource(getActivity(),R.array.cities,android.R.layout.simple_spinner_item);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnercity.setAdapter(adapter1);
                spinnercity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // to get the city
                        city  = parent.getItemAtPosition(position).toString();
                        cityname.setText(city);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
            }

            public void rbClickdonor(View view)
            {
                int radioid = radioGroup.getCheckedRadioButtonId();
                //blood donor status
               /* donor = v.findViewById(radioid);*/
            }
        };
        usrRef.addListenerForSingleValueEvent(userListener);
        return v;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}