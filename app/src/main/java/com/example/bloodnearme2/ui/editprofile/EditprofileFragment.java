package com.example.bloodnearme2.ui.editprofile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bloodnearme2.R;

public class EditprofileFragment extends Fragment {

    private EditproflieViewModel editproflieViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        editproflieViewModel =
                ViewModelProviders.of(this).get(EditproflieViewModel.class);
        View root = inflater.inflate(R.layout.fragment_editprofile, container, false);
        final TextView textView = root.findViewById(R.id.text_editprofile);
        editproflieViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}