package com.halawy.elmenu.Required;

public class Information_Person {
    private String name;
    private String address;
    private String phone;
    private String uid;

    public Information_Person(String name, String address, String phone, String uid) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
