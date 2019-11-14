package com.example.bloodnearme2;

public class user {
    String id;
    String name;
    String emailid ;
    String phonenumber ;
    String password ;
   String dob;
    String bloodgroup;
    String gender;
    String city;

    public user(String id,String name, String emailid, String phonenumber, String password,String dob,String bloodgroup,String gender,String city) {
        this.id  =id;
        this.name = name;
        this.emailid = emailid;
        this.phonenumber = phonenumber;
        this.password = password;
        this.bloodgroup = bloodgroup;
       this.dob = dob;
       this.gender = gender;
       this.city  =city;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getPassword() {
        return password;
    }

  public String getDob() {
        return dob;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }
}
