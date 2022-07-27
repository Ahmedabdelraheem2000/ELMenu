package com.halawy.elmenu.Next_Show;

public class Price_And_Size_Adapter {
    String Rid;
    String Cid;
    String Iid;
    String Tid;
    String Pid;
    float price;
    String size;

    public Price_And_Size_Adapter(String rid, String cid, String iid, String tid, String pid, float price, String size) {
        Rid = rid;
        Cid = cid;
        Iid = iid;
        Tid = tid;
        Pid = pid;
        this.price = price;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
