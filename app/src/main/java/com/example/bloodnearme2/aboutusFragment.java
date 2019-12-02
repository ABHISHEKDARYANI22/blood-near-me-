package com.example.bloodnearme2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class aboutusFragment extends Fragment {

    ImageView githubimage;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_aboutus, container, false);

        githubimage = v.findViewById(R.id.githubimage);
        githubimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                /*i.setAction(Intent.ACTION_VIEW);*/
                i.setData(Uri.parse("https://github.com/ABHISHEKDARYANI22/blood-near-me-"));
                startActivity(i);
            }
        });
        return v;
    }


}
