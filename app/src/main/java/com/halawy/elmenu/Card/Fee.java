package com.halawy.elmenu.Card;

public class Fee {
     float Delivery_fee;
     float Service_Fee;

    public Fee(float delivery_fee, float service_Fee) {
        Delivery_fee = delivery_fee;
        Service_Fee = service_Fee;
    }

    public Fee() {
    }

    public float getDelivery_fee() {
        return Delivery_fee;
    }

    public void setDelivery_fee(float delivery_fee) {
        Delivery_fee = delivery_fee;
    }

    public float getService_Fee() {
        return Service_Fee;
    }

    public void setService_Fee(float service_Fee) {
        Service_Fee = service_Fee;
    }
}
