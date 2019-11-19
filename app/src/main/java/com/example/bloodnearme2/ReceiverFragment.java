package com.example.bloodnearme2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bloodnearme2.R;

public class ReceiverFragment extends Fragment {
    String city = "";
    String bg  = "";
    /*private ReceiverViewModel receiverViewModel;*/

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

                    View v  =  inflater.inflate(R.layout.fragment_receiver,null);
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
                    return v;
            }

    @Override

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "hello myself", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
