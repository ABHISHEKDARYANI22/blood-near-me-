package com.example.bloodnearme2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bloodnearme2.R;

public class LogoutFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v  =  inflater.inflate(R.layout.fragment_receiver,null);

        return v;

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        AlertDialog.Builder builder =  new AlertDialog.Builder(getActivity());

        builder.setMessage("DO YOU WANT TO LOGOUT?").setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
                Intent  i = new Intent(getActivity(),MainActivity.class);
                startActivity(i);
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
               Intent i = new Intent(getActivity(),ReceiverFragment.class);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}