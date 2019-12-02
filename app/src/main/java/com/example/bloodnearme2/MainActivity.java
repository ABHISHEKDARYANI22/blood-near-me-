package com.example.bloodnearme2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private  EditText eteemailid,etpassword;
    private Button btnlogin;
    private TextView tvsignup,tvforgotpassword;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!= null)
        {
            //profile activity
            /*finish();
            Intent i = new Intent(getApplicationContext(),welcome.class);
            startActivity(i);*/

        }
        tvforgotpassword = (TextView)findViewById(R.id.tvforgotpassword) ;
        eteemailid = (EditText)findViewById(R.id.etemailid);
        etpassword = (EditText)findViewById(R.id.etpassword);
        btnlogin = (Button)findViewById(R.id.btnlogin);
        tvsignup = (TextView)findViewById(R.id.tvsignup);
        btnlogin.setOnClickListener(this);
        tvsignup.setOnClickListener(this);
        tvforgotpassword.setOnClickListener(this);
    }


    public void onClick(View view)
    {

        String emailid = eteemailid.getText().toString().trim();
        String password  = etpassword.getText().toString();
            if(view == btnlogin)
            {
                if(TextUtils.isEmpty(emailid))
                {
                    Toast.makeText(this, "Enter Email Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("LOGGING IN");
                progressDialog.show();

                firebaseAuth.signInWithEmailAndPassword(emailid,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            progressDialog.cancel();
                            finish();
                            Intent i = new Intent(getApplicationContext(),navigation.class);
                            startActivity(i);
                            
                        }
                        else
                        {
                            progressDialog.cancel();
                            Toast.makeText(MainActivity.this, "Email ID or Password is wrong", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
            if(view == tvsignup)
            {
                Intent intent = new Intent(MainActivity.this,registration.class);
                startActivity(intent);
            }
            if (view == tvforgotpassword)
            {
                Intent intent = new Intent(MainActivity.this,forgotpassword.class);
                startActivity(intent);
            }
    }

}
