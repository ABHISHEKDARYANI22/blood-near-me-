package com.example.bloodnearme2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;


public class registration extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText etname,etpassword,etemailid,etphonenumber,etpasswordagain;
   private Button btnSignUp;
   private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseusers;
    private EditText etdob;
    private DatePickerDialog.OnDateSetListener mDateSetListner;
    private RadioButton gender,donor;
    Button btncalenderregister;
    private RadioGroup radioGroup,radioGroupdonor;
    private Spinner spinner,spinnercity;
    private String blood,city;
    private int yearyear;
   @Override
    protected void onCreate(Bundle savedInstanceState)
   {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.registration);
       firebaseAuth = FirebaseAuth.getInstance();
       progressDialog = new ProgressDialog(this);
       etname = (EditText)findViewById(R.id.etname);
        etemailid = (EditText)findViewById(R.id.etemailid);
        etpassword  = (EditText)findViewById(R.id.etpassword);
        etpasswordagain  =(EditText)findViewById(R.id.etpasswordagain);
        etphonenumber = (EditText)findViewById(R.id.etphonenumber);
        btnSignUp = (Button)findViewById(R.id.btnsignup);
        etdob = (EditText)findViewById(R.id.etdob);
        btncalenderregister = findViewById(R.id.btnCalenderregister);
       databaseusers = FirebaseDatabase.getInstance().getReference("users");

       radioGroup  = (RadioGroup)findViewById(R.id.radiogroup);
        radioGroupdonor  =(RadioGroup)findViewById(R.id.radiogroupdonor);
       btnSignUp.setOnClickListener(this);
       btncalenderregister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Calendar c  = Calendar.getInstance();
                yearyear  = c.get(Calendar.YEAR);
               int month = c.get(Calendar.MONTH);
               int day  = c.get(Calendar.DAY_OF_MONTH);

               DatePickerDialog dialog  = new DatePickerDialog(registration.this,android.R.style.Theme_DeviceDefault_Light,mDateSetListner,yearyear,month,day);
               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
               dialog.show();
           }
       });
        mDateSetListner  = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
               month = month +1 ;
                String date  = month+"/"+dayOfMonth+"/"+year;
                if (yearyear-year>=18){
                    etdob.setText(date);
                    etdob.setError(null);
                }
                else
                {
                    etdob.setError("age should be more than 18");
                }

            }
        };
        //spinner layout for blood group
       spinner = findViewById(R.id.spinner);
       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.bloodgroup, android.R.layout.simple_spinner_item);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spinner.setAdapter(adapter);
       spinner.setOnItemSelectedListener(this);

       //spinner layout for city
       spinnercity = findViewById(R.id.spinnercity);
       ArrayAdapter<CharSequence> adapter1  = ArrayAdapter.createFromResource(this,R.array.cities,android.R.layout.simple_spinner_item);
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
    }


        public void rbClick(View v)
        {
            int radioid  = radioGroup.getCheckedRadioButtonId();
            //gender will tell the checked one
            gender = findViewById(radioid);
        }
        public void rbClickdonor(View v)
        {
            int radioid = radioGroupdonor.getCheckedRadioButtonId();
            //blood donor status
            donor = findViewById(radioid);


        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //text will give the blood group
        blood  =  parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void registerUser() {
        String emailid = etemailid.getText().toString().trim();
        String password = etpassword.getText().toString().trim();
        String name = etname.getText().toString().trim();
        String passwordagain = etpasswordagain.getText().toString().trim();
        String phonenumber = etphonenumber.getText().toString().trim();
        String malefemale = gender.getText().toString();
        String dob = etdob.getText().toString();
        String bloodgroup = blood;
        String city1 = city;
        String donordonor = donor.getText().toString();

        if (TextUtils.isEmpty(emailid)) {
            Toast.makeText(this, "Please enter the Email Id", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter the password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter the name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passwordagain)) {
            Toast.makeText(this, "Please enter the password again", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phonenumber)) {
            Toast.makeText(this, "Please enter the phone number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(malefemale)) {
            Toast.makeText(this, "Please enter the gender", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(dob)) {
            Toast.makeText(this, "Please enter the Date of Birth", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(city1)) {
            Toast.makeText(this, "Please enter the city", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(bloodgroup)) {
            Toast.makeText(this, "Please enter the Blood Group", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(passwordagain)) {
            Toast.makeText(this, "Both passwords are not same", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(donordonor)) {
            Toast.makeText(this, "Please enter the donor preference", Toast.LENGTH_SHORT).show();
            return;
        }

        //if credentials are ok
        //then create account


        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(emailid, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //user is successfully registered
                    adduser();
                    progressDialog.cancel();
                    Toast.makeText(registration.this, "Registered Successfully....", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(registration.this, MainActivity.class);
                    startActivity(i);
                } else {
                    progressDialog.cancel();
                    Toast.makeText(registration.this, "Error occurred, please try again", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    public void onClick(View view)
    {
        if(view == btnSignUp)
        {
            try {
                registerUser();
            }
            catch (Exception e)
            {
                Toast.makeText(this, "ENTER THE DETAILS", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void adduser()
    {
        String name = etname.getText().toString().trim();
        String emailid  = etemailid.getText().toString().trim();
        String phonenumber = etphonenumber.getText().toString().trim();
        String password  = etpassword.getText().toString() ;
        String malefemale = gender.getText().toString();
        String dob  = etdob.getText().toString();
        String bloodgroup  = blood;
        String city2  = city;
        String donord =  donor.getText().toString();
        String id = databaseusers.push().getKey();
        user u = new user(id,name,emailid,phonenumber,password,dob,bloodgroup,malefemale,city2,donord);
        databaseusers.child(id).setValue(u);


    }


}
