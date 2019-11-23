package com.example.bloodnearme2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
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

public class ReceiverFragment extends Fragment
{

    String city = "";
    String bg  = "";
    ListView donorlist;
    DatabaseReference myreff;
    ArrayList<String> arrayList;
    Button search;

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
        donorlist = v.findViewById(R.id.donorlist);
        myreff = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

         search = v.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             
                // for data retrival
                /* checkEmail = user.getEmail()*/
                final ProgressDialog pd = ProgressDialog.show(getActivity(), "Searching", "Hang on", true);
                DatabaseReference usrRef=myreff.child("users");
                String donors[] = {};
                arrayList = new ArrayList<>(Arrays.asList(donors));
                final ArrayAdapter   arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayList);
                donorlist.setAdapter(arrayAdapter);
                Toast.makeText(getActivity(), "hello myself", Toast.LENGTH_SHORT).show();
                ValueEventListener userListener  = new ValueEventListener()
                {
                    @Override

                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        Toast.makeText(getActivity(), "bye", Toast.LENGTH_SHORT).show();
                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            Toast.makeText(getActivity(), "hello hi", Toast.LENGTH_SHORT).show();
                            String test = ds.child("donorstatus").getValue(String.class);
                           if ( test.equals("yes"))
                            {
                                Toast.makeText(getActivity(), " sucess ", Toast.LENGTH_LONG).show();
                                String test1 = ds.child("bloodgroup").getValue(String.class);
                                if (test1.equals(bg))
                                {
                                    Toast.makeText(getActivity(), "success 1", Toast.LENGTH_SHORT).show();
                                    String test2 = ds.child("city").getValue(String.class);
                                   if (test2.equals(city))
                                    {

                                        String uEmail = ds.child("emailid").getValue(String.class);
                                        arrayList.add(uEmail);
                                        arrayAdapter.notifyDataSetChanged();
                                        pd.dismiss();

                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(getActivity(), "not working", Toast.LENGTH_SHORT).show();
                        pd.dismiss();

                    }
                };
                usrRef.addListenerForSingleValueEvent(userListener);

            }
        });


        return v;
    }
}