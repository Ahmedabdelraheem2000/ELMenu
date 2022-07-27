package com.halawy.elmenu.Categores;

public class Categores_Adapter {
    private String Cid;
    private String Name;
    private String Icon;
    private String Rid; // ID Shop //

    public Categores_Adapter() {
    }

    public Categores_Adapter(String cid, String name, String icon, String rid) {
        Cid = cid;
        Name = name;
        Icon = icon;
        Rid = rid;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getRid() {
        return Rid;
    }

    public void setRid(String rid) {
        Rid = rid;
    }
}
