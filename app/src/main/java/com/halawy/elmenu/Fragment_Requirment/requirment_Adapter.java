package com.halawy.elmenu.Fragment_Requirment;

public class requirment_Adapter {
    private String idorder;

    private  String id;
    private String Name_Shop;
    private String Address;
    private int status;
    private String Image_Shop;

    public requirment_Adapter() {
    }

    public requirment_Adapter(String idorder, String id, String name_Shop, String address, int status, String image_Shop) {
        this.idorder = idorder;
        this.id = id;
        Name_Shop = name_Shop;
        Address = address;
        this.status = status;
        Image_Shop = image_Shop;
    }

    public String getIdorder() {
        return idorder;
    }

    public void setIdorder(String idorder) {
        this.idorder = idorder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_Shop() {
        return Name_Shop;
    }

    public void setName_Shop(String name_Shop) {
        Name_Shop = name_Shop;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage_Shop() {
        return Image_Shop;
    }

    public void setImage_Shop(String image_Shop) {
        Image_Shop = image_Shop;
    }
}
