package com.halawy.elmenu.Card;

import android.content.Context;

import androidx.annotation.NonNull;

import com.halawy.elmenu.Order.Order_Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class GetPrice {
    interface interface_getprice{
        public void price(String pid,Order_Adapter order_adapter);
    }
    interface_getprice interface_getprice;
    private  DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Order")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Data_Order");
    private HashMap hashMap=new HashMap();
    //---------------------------------------------
    public void Price_Oid(Context context, String Oid) {

        FirebaseDatabase.getInstance().getReference("Order").child(FirebaseAuth.getInstance().getCurrentUser().getUid()
        ).child("Data_Order").child(Oid.trim()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Order_Adapter order_adapter = snapshot.getValue(Order_Adapter.class);
                String Pid = order_adapter.getPid();

                interface_getprice.price(Pid,order_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Get_Prices_Pid(Oid);
    }

    public void Get_Prices_Pid(String Oid){
        interface_getprice=new interface_getprice() {
            @Override
            public void price(String pid, Order_Adapter order_adapter) {
                FirebaseDatabase.getInstance().getReference("Price").child(pid.trim())
                        .child("Prices").addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Float price_in_firebase=snapshot.getValue(Float.class);
                                int counter = order_adapter.getCount();
                                Float All_Price = counter * price_in_firebase;
                                hashMap.put("price",All_Price);
                                databaseReference.child(Oid.trim()).updateChildren(hashMap);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        }
                );
            }
        };
    }


}
