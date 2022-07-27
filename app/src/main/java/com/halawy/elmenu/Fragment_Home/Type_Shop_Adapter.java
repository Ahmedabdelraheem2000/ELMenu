package com.halawy.elmenu.Fragment_Home;

public class Type_Shop_Adapter {
   private String Name;
    private String TSid;
    private String Image;

    public Type_Shop_Adapter() {
    }

    public Type_Shop_Adapter(String name, String TSid, String image) {
        Name = name;
        this.TSid = TSid;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTSid() {
        return TSid;
    }

    public void setTSid(String TSid) {
        this.TSid = TSid;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
