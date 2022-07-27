package com.halawy.elmenu.Order;

public class Order_Adapter {
    private String Oid;
    private String Name_Product;
    private float Price;
    private String label;
    private String size;
    private String Rid;
    private String Cid;
    private String Iid;
    private String Tid;
    private String Pid;
    private int count;
    private String image;

    public Order_Adapter() {
    }

    public Order_Adapter(String same_Product, float price, String label, String size, String rid, String cid, String iid, String tid, String pid, int count, String image) {
        this.Name_Product = same_Product;
        Price = price;
        this.label = label;
        this.size = size;
        Rid = rid;
        Cid = cid;
        Iid = iid;
        Tid = tid;
        Pid = pid;
        this.count = count;
        this.image = image;
    }

    public Order_Adapter(String oid, String name_Product, float price, String label, String size, String rid, String cid, String iid, String tid, String pid, int count, String image) {
        Oid = oid;
        Name_Product = name_Product;
        Price = price;
        this.label = label;
        this.size = size;
        Rid = rid;
        Cid = cid;
        Iid = iid;
        Tid = tid;
        Pid = pid;
        this.count = count;
        this.image = image;
    }

    public String getOid() {
        return Oid;
    }

    public void setOid(String oid) {
        Oid = oid;
    }

    public String getName_Product() {
        return Name_Product;
    }

    public void setName_Product(String name_Product) {
        this.Name_Product = name_Product;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRid() {
        return Rid;
    }

    public void setRid(String rid) {
        Rid = rid;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getIid() {
        return Iid;
    }

    public void setIid(String iid) {
        Iid = iid;
    }

    public String getTid() {
        return Tid;
    }

    public void setTid(String tid) {
        Tid = tid;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
