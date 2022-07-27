package com.halawy.elmenu;

public class User {
    private String email;
    private String fristname;
    private String lastname;
    private String phone;

    public User() {
    }

    public User(String email, String fristname, String lastname, String phone) {
        this.email = email;
        this.fristname = fristname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFristname() {
        return fristname;
    }

    public void setFristname(String fristname) {
        this.fristname = fristname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
