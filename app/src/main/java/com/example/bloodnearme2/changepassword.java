package com.example.bloodnearme2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changepassword extends Fragment
{
    EditText etnewpassword;
    Button   btnnewpassword;
    FirebaseUser user ;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_changepassword, container, false);
        etnewpassword = v.findViewById(R.id.etnewpassword);
        btnnewpassword = v.findViewById(R.id.btnnewpassword);
        user = FirebaseAuth.getInstance().getCurrentUser();

        btnnewpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user!=null)
                {
                    final ProgressDialog pd = ProgressDialog.show(getActivity(), "", "Wait", true);
                    user.updatePassword(etnewpassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                pd.dismiss();
                                Toast.makeText(getActivity(), "your password has been changed", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getActivity(),navigation.class);
                                startActivity(i);
                            }
                            else
                            {
                                pd.dismiss();
                                Toast.makeText(getActivity(), "password could not be changed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        return v;
    }


}
