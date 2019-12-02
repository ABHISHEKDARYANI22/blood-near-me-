package com.example.bloodnearme2;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class EditprofileFragment extends Fragment
{
    String checkEmail,uEmail,uName,uphonenumber,uspinnerbg,uspinnercity,upassword,gen,dob,donor,id;
    DatePickerDialog.OnDateSetListener mDateSetListner;
    Spinner spinnerbg,spinnercity;
     EditText etnameedit,etphonenumber,etdob;
     TextView tvemailid;
     TextView bgname,cityname,tvgender;
     String bloodgroup,city;
    Button btnupdate,btncalender,btnselect,btnupload;
    Switch genderswitch;
    DatabaseReference myreff;
    StorageReference sr;
    String imagen;
    String picture;
    File file;
    String imagename;
    String pname,pbg,pcity,pphone,ppassword,pgen,pdob,pdonor,pid;
    private StorageReference mStorageRef;
    View v;
    Bitmap bitmap;
    public static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    user u,u1;
    ImageView image;
    int yearyear,month,day;
    DatabaseReference usrRef;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    StorageReference fileReference;
    public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState)
    {
        final ProgressDialog pd = ProgressDialog.show(getActivity(), "", "Wait..", true);
         v = inflater.inflate(R.layout.fragment_editprofile, null);
         etnameedit = v.findViewById(R.id.etname);

         tvemailid = v.findViewById(R.id.tvemailid);
         etphonenumber = v.findViewById(R.id.etphonenumber);

         bgname = v.findViewById(R.id.bgname);
         etdob = v.findViewById(R.id.etdob);
         cityname = v.findViewById(R.id.cityname);
         tvgender = v.findViewById(R.id.tvgender);
         genderswitch = v.findViewById(R.id.genderswitch);
         btncalender = v.findViewById(R.id.btnCalender);
         image =  (ImageView)v.findViewById(R.id.image);
         btnupdate = v.findViewById(R.id.btnupdate);
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");

        myreff = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
         usrRef=myreff.child("users");
        checkEmail = user.getEmail();


        ValueEventListener userListener = new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    uEmail = ds.child("emailid").getValue(String.class);


                    assert uEmail != null;
                    if(uEmail.equals(checkEmail))
                    {
                        uName = ds.child("name").getValue(String.class);
                        uspinnerbg= ds.child("bloodgroup").getValue().toString();
                        uspinnercity = ds.child("city").getValue().toString();
                        uphonenumber = ds.child("phonenumber").getValue().toString();
                        upassword = ds.child("password").getValue().toString();
                        gen = ds.child("gender").getValue().toString();
                        dob = ds.child("dob").getValue().toString();
                        donor = ds.child("donorstatus").getValue().toString();
                        id  = ds.child("id").getValue().toString();
                       /* imagen = ds.child("imageuri").getValue().toString();*/
                        etnameedit.setText(uName);
                        tvemailid.setText(uEmail);
                        etphonenumber.setText(uphonenumber);
                        bgname.setText(uspinnerbg);
                        cityname.setText(uspinnercity);
                        tvgender.setText(gen);
                        etdob.setText(dob);


                        //for image
                        /*sr  = mStorageRef.child(imagen);
                        //for image retrival



                        try {
                             file  =File.createTempFile("image","jpg");
                            sr.getFile(file).addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), "failed to upload", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        image.setImageBitmap(bitmap);*/
                        break;
                    }

                }

                pd.dismiss();
                //now for the spinner
                //spinner layout for blood group
                if (gen.equals("male"))
                {
                    genderswitch.setChecked(false);
                }
                if (gen.equals("female"))
                {
                    genderswitch.setChecked(true);
                }
/*
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.bloodgroup, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
               /* spinnerbg.setAdapter(adapter);
                spinnerbg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // to get the blood group
                      bloodgroup = parent.getItemAtPosition(position).toString();
                      bgname.setText(bloodgroup);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });*/

                //spinner layout for city
               /* ArrayAdapter<CharSequence> adapter1  = ArrayAdapter.createFromResource(getActivity(),R.array.cities,android.R.layout.simple_spinner_item);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnercity.setAdapter(adapter1);
                spinnercity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // to get the city
                        city  = parent.getItemAtPosition(position).toString();
                        cityname.setText(city);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });*/
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
            }


        };
        usrRef.addListenerForSingleValueEvent(userListener);




        //for calender
        btncalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c  = Calendar.getInstance();
                yearyear  = c.get(Calendar.YEAR);
                 month = c.get(Calendar.MONTH);
                 day  = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog  = new DatePickerDialog(getActivity(),android.R.style.Theme_DeviceDefault_Light,mDateSetListner,yearyear,month,day);
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
        //for gender
        genderswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true)
                {
                    tvgender.setText("female");
                }
                if (isChecked==false)
                {
                    tvgender.setText("male");
                }
            }
        });

        //for saving new data
        btnupdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                String name  = etnameedit.getText().toString().trim();
                String phonenumber  = etphonenumber.getText().toString().trim();
                String malefemale = tvgender.getText().toString();
                String dob  = etdob.getText().toString();
                String bloodgroup  = bgname.getText().toString();
                String city1 = cityname.getText().toString();



                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getActivity(), "Please enter the name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phonenumber)) {
                    Toast.makeText(getActivity(), "Please enter the phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(malefemale)) {
                    Toast.makeText(getActivity(), "Please enter the gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(dob)) {
                    Toast.makeText(getActivity(), "Please enter the Date of Birth", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(city1)) {
                    Toast.makeText(getActivity(), "Please enter the city", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(bloodgroup)) {
                    Toast.makeText(getActivity(), "Please enter the Blood Group", Toast.LENGTH_SHORT).show();
                    return;
                }
                u  = new user();
                u.setDob(dob);
                u.setBloodgroup(bloodgroup);
                u.setCity(uspinnercity);
                u.setEmailid(checkEmail);
                u.setName(name);
                u.setPassword(upassword);
                u.setPhonenumber(phonenumber);
                u.setGender(malefemale);
                u.setId(id);
                u.setDonorstatus(donor);
               /* u.setImageuri(imagen);*/
                usrRef.child(id).setValue(u);
                Toast.makeText(getActivity(), "data updated", Toast.LENGTH_SHORT).show();
            }
        });


        /*btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openfilechooser();
            }
        });

        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadpicture();
            }
        });*/
        return v;

    }
    /*private void openfilechooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK &&data!=null && data.getData()!=null)
        {
            imageUri = data.getData();
            Picasso.with(getActivity()).load(imageUri).into(image);
        }
    }

    public void uploadpicture()
    {
        if (imageUri != null)
        {
            imagename = System.currentTimeMillis()+"."+getFileExtention(imageUri);
             fileReference  = mStorageRef.child(imagename);
            fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getActivity(), "uploaded", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "error occured"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            u1 = new user();

            //for putting the name of file in realtime database
            ValueEventListener userListener1 = new ValueEventListener()
            {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        uEmail = ds.child("emailid").getValue(String.class);


                        assert uEmail != null;
                        if (uEmail.equals(checkEmail)) {

                             pname = ds.child("name").getValue(String.class);
                            pbg= ds.child("bloodgroup").getValue().toString();
                            pcity = ds.child("city").getValue().toString();
                            pphone = ds.child("phonenumber").getValue().toString();
                            ppassword = ds.child("password").getValue().toString();
                            pgen = ds.child("gender").getValue().toString();
                            pdob = ds.child("dob").getValue().toString();
                            pdonor = ds.child("donorstatus").getValue().toString();
                            pid  = ds.child("id").getValue().toString();
                            u1.setImageuri(imagename);
                            u1.setDob(pdob);
                            u1.setBloodgroup(pbg);
                            u1.setCity(pcity);
                            u1.setEmailid(checkEmail);
                            u1.setName(pname);
                            u1.setPassword(ppassword);
                            u1.setPhonenumber(pphone);
                            u1.setGender(pgen);
                            u1.setId(pid);
                            u1.setDonorstatus(pdonor);
                            usrRef.child(id).setValue(u1);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError)
                {
                }
            };
            usrRef.addListenerForSingleValueEvent(userListener1);
        }
        else
        {
            Toast.makeText(getActivity(), "no file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtention(Uri uri)
    {
        ContentResolver cR  = getActivity().getContentResolver();
        MimeTypeMap mime  = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }*/
}