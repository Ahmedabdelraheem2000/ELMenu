package com.halawy.elmenu.Details_Order;

public class Order_Detials_Adapter {
    String name_Product;
    String label;
    String size;
    int count;
    String price;
    String image;

    public Order_Detials_Adapter() {
    }

    public Order_Detials_Adapter(String name_Product, String label, String size, int count, String price, String image) {
        this.name_Product = name_Product;
        this.label = label;
        this.size = size;
        this.count = count;
        this.price = price;
        this.image = image;
    }

    public String getName_Product() {
        return name_Product;
    }

    public void setName_Product(String name_Product) {
        this.name_Product = name_Product;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
