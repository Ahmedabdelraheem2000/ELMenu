package com.halawy.elmenu.Type_Size;

public class Type_Size_Adapter {
    private String Tid;
    private String name_size;
    private String Cid;// Cid Category //
    private String Rid;// Rid Shop //

    public Type_Size_Adapter(String tid, String name_size, String cid, String rid) {
        Tid = tid;
        this.name_size = name_size;
        Cid = cid;
        Rid = rid;
    }

    public Type_Size_Adapter() {
    }

    public String getTid() {
        return Tid;
    }

    public void setTid(String tid) {
        Tid = tid;
    }

    public String getName_size() {
        return name_size;
    }

    public void setName_size(String name_size) {
        this.name_size = name_size;
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
