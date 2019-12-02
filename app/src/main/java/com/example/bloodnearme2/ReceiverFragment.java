package com.example.bloodnearme2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReceiverFragment extends Fragment
{

    String city = "";
    String bg  = "";
    ListView donorlist1 ;
    DatabaseReference myreff;
    List<user> arrayList= new ArrayList<>();
    Button search;
    String test,test1,test2,test3;
    user u;

     Activity context;

     View v;
    /*private ReceiverViewModel receiverViewModel;*/

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

                     v  =  inflater.inflate(R.layout.fragment_receiver,null);
        Spinner spinnercity,spinnerbg;

                spinnerbg  = (Spinner)v.findViewById(R.id.spinnerbg);
                spinnercity = (Spinner)v.findViewById(R.id.spinnercity);

        ArrayAdapter<CharSequence> adapter1  = ArrayAdapter.createFromResource(getActivity(),R.array.cities,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercity.setAdapter(adapter1);
        spinnercity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // to get the city
                city  = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> adapter2  = ArrayAdapter.createFromResource(getActivity(),R.array.bloodgroup,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbg.setAdapter(adapter2);
        spinnerbg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // to get the bg
                bg  = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        donorlist1 = v.findViewById(R.id.donorlist1);
        myreff = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

         search = v.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                final ProgressDialog pd = ProgressDialog.show(getActivity(), "", "Searching", false);
                DatabaseReference usrRef=myreff.child("users");
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                final String email = user.getEmail();
                ValueEventListener userListener  = new ValueEventListener()
                {
                    @Override

                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        arrayList.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {

                             test = ds.child("donorstatus").getValue(String.class);
                             test1 = ds.child("bloodgroup").getValue(String.class);
                             test2 = ds.child("city").getValue(String.class);
                             test3 = ds.child("emailid").getValue().toString();

                           if ( test.equals("yes")/*&&test1.equals(bg)*/&&test2.equals(city)&&!test3.equals(email))
                            {
                                if (bg.equals("O+"))
                                {
                                    if (test1.equals("O+") || test1.equals("O-")) {
                                        user u = ds.getValue(user.class);
                                        arrayList.add(u);
                                        pd.dismiss();
                                    }
                                }
                                if (bg.equals("A+"))
                                {
                                    if (test1.equals("A+") || test1.equals("A-")|| test1.equals("O+")|| test1.equals("O-")) {
                                        user u = ds.getValue(user.class);
                                        arrayList.add(u);
                                        pd.dismiss();
                                    }
                                }
                                if (bg.equals("B+"))
                                {
                                    if (test1.equals("B+") || test1.equals("B-")|| test1.equals("O+")|| test1.equals("O-")) {
                                        user u = ds.getValue(user.class);
                                        arrayList.add(u);
                                        pd.dismiss();
                                    }
                                }
                                if (bg.equals("AB+"))
                                {
                                        user u = ds.getValue(user.class);
                                        arrayList.add(u);
                                        pd.dismiss();

                                }
                                if (bg.equals("O-"))
                                {
                                    if (test1.equals("O-")) {
                                        user u = ds.getValue(user.class);
                                        arrayList.add(u);
                                        pd.dismiss();
                                    }
                                }
                                if (bg.equals("A-"))
                                {
                                    if (test1.equals("A-") || test1.equals("O-")) {
                                        user u = ds.getValue(user.class);
                                        arrayList.add(u);
                                        pd.dismiss();
                                    }
                                }
                                if (bg.equals("B-"))
                                {
                                    if (test1.equals("B-") || test1.equals("O-")) {
                                        user u = ds.getValue(user.class);
                                        arrayList.add(u);
                                        pd.dismiss();
                                    }
                                }
                                if (bg.equals("AB-"))
                                {
                                    if (test1.equals("AB-") || test1.equals("A-")|| test1.equals("B-")|| test1.equals("O-")) {
                                        user u = ds.getValue(user.class);
                                        arrayList.add(u);
                                        pd.dismiss();
                                    }
                                }

                            }
                           /*else
                           {
                               Toast.makeText(getActivity(), "error occured", Toast.LENGTH_SHORT).show();
                           }*/
                           pd.dismiss();
                        }

                        DonorList trupti  = new DonorList(getActivity(),arrayList);
                        donorlist1.setAdapter(trupti);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(getActivity(), "not working", Toast.LENGTH_SHORT).show();
                        pd.dismiss();

                    }
                };

                usrRef.addListenerForSingleValueEvent(userListener);
               donorlist1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        u = (user)donorlist1.getItemAtPosition(position);
                        AlertDialog.Builder builder =  new AlertDialog.Builder(getActivity());
                        builder.setMessage("MEDIUM OF CONTACT?").setCancelable(false)
                                .setPositiveButton("EMAIL", new DialogInterface.OnClickListener() {
                                    @Override

                                    public void onClick(DialogInterface dialog, int which) {
                                      /* getActivity().finish();*/
                                         dialog.cancel();
                                        String emailid  = u.getEmailid();
                                     Toast.makeText(getActivity(), "forwarding to GMAIL", Toast.LENGTH_SHORT).show();
                                        try{
                                            Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + emailid));
                                            intent.putExtra(Intent.EXTRA_SUBJECT, "EMERGENCY");
                                            intent.putExtra(Intent.EXTRA_TEXT, "YOUR BLOOD IS REQUIRED, PLEASE DONATE" +
                                                    " CONTACT AS SOON AS POSSIBLE");
                                            startActivity(intent);
                                        }catch(ActivityNotFoundException e){

                                            Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_LONG)
                                                    .show();
                                        }
                                    }
                                }).setNegativeButton("CALL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String phone = u.getPhonenumber();

                              /*  getActivity().finish();*/
                                dialog.cancel();
                                Toast.makeText(getActivity(), "forwarding to DIALER", Toast.LENGTH_SHORT).show();
                                Uri u = Uri.parse("tel:" + phone);
                                Intent i = new Intent(Intent.ACTION_DIAL, u);

                                try
                                {
                                    // Launch the Phone app's dialer with a phone
                                    // number to dial a call.
                                    startActivity(i);
                                }
                                catch (SecurityException s)
                                {
                                    // show() method display the toast with
                                    // exception message.
                                    Toast.makeText(getActivity(), ""+s.getMessage(), Toast.LENGTH_LONG)
                                            .show();
                                }
                                /*String phoneno = phone.getText().toString().trim();*/

                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
        });
        return v;
    }
}