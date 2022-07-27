package com.halawy.elmenu.Item;

public class Item_Adapter {

    private String Iid;
    private String name;
    private String image;
    private String price;
    private String Conists_of;
    private String Cid;// Cid Category //
    private String Rid;// Rid Shop //
    public Item_Adapter() {
    }

    public Item_Adapter(String iid, String name, String image, String price, String conists_of, String cid, String rid) {
        Iid = iid;
        this.name = name;
        this.image = image;
        this.price = price;
        Conists_of = conists_of;
        Cid = cid;
        Rid = rid;
    }

    public String getIid() {
        return Iid;
    }

    public void setIid(String iid) {
        Iid = iid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getConists_of() {
        return Conists_of;
    }

    public void setConists_of(String conists_of) {
        Conists_of = conists_of;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getRid() {
        return Rid;
    }

    public void setRid(String rid) {
        Rid = rid;
    }
}
