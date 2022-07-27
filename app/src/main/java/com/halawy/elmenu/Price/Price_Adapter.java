package com.halawy.elmenu.Price;

public class Price_Adapter {
    private String Pid;
    private float Prices;
    private String Cid;// Cid Category //
    private String Rid;// Rid Shop //
    private String Iid;// Iid Item //
    private String Tid;// Tid Type Size //

    public Price_Adapter() {
    }

    public Price_Adapter(String pid, float prices, String cid, String rid, String iid, String tid) {
        Pid = pid;
        Prices = prices;
        Cid = cid;
        Rid = rid;
        Iid = iid;
        Tid = tid;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public float getPrices() {
        return Prices;
    }

    public void setPrices(float prices) {
        Prices = prices;
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
}
