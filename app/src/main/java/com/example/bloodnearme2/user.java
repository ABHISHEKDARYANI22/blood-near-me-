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
    String donorstatus;


    public user(String id,String name, String emailid, String phonenumber, String password,String dob,String bloodgroup,String gender,String city,String donorstatus) {
        this.id  =id;
        this.name = name;
        this.emailid = emailid;
        this.phonenumber = phonenumber;
        this.password = password;
        this.bloodgroup = bloodgroup;
       this.dob = dob;
       this.gender = gender;
       this.city  =city;
       this.donorstatus = donorstatus;


    }



    public user()
    {

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

    public String getDonorstatus() {
        return donorstatus;
    }

    public void setDonorstatus(String donorstatus) {
        this.donorstatus = donorstatus;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
