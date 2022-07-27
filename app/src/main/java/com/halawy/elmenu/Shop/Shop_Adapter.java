package com.halawy.elmenu.Shop;

public class Shop_Adapter {
    private String Rid;
    private String Name_Shop;
    private String Label;
    private String Phone;
    private String Address;
    private String Image;
    private String Delivery;
    private String TSid;


    public Shop_Adapter(String rid, String name_Shop, String label, String phone, String address, String Image, String delivery, String TSid) {
        Rid = rid;
        Name_Shop = name_Shop;
        Label = label;
        Phone = phone;
        Address = address;
        Image = Image;
        Delivery = delivery;
        this.TSid = TSid;
    }

    public String getTSid() {
        return TSid;
    }

    public void setTSid(String TSid) {
        this.TSid = TSid;
    }

    public Shop_Adapter() {
    }

    public String getRid() {
        return Rid;
    }

    public void setRid(String rid) {
        Rid = rid;
    }

    public String getName_Shop() {
        return Name_Shop;
    }

    public void setName_Shop(String name_Shop) {
        Name_Shop = name_Shop;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String iamge) {
        Image = iamge;
    }

    public String getDelivery() {
        return Delivery;
    }

    public void setDelivery(String delivery) {
        Delivery = delivery;
    }
}
