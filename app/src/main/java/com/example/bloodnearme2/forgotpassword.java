package com.example.bloodnearme2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class  forgotpassword extends AppCompatActivity {

    EditText etEmailidforgot;
    Button btnVerification;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        etEmailidforgot = findViewById(R.id.etEmailidforgot);
        btnVerification = findViewById(R.id.btnVerification);
        firebaseAuth = FirebaseAuth.getInstance();
        btnVerification.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final ProgressDialog pd = ProgressDialog.show(forgotpassword.this, "Sending", "wait..", true);
                firebaseAuth.sendPasswordResetEmail(etEmailidforgot.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            pd.dismiss();
                            Toast.makeText(forgotpassword.this, "reset link sent to your email", Toast.LENGTH_SHORT).show();
                        }
                        else 
                        {
                            Toast.makeText(forgotpassword.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



    }
}
