package com.halawy.elmenu.Shop;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Get_Shop {
    private DatabaseReference GetShopsFromFirebase;
    private ArrayList<Shop_Adapter> shop_adapters_Array_List=new ArrayList<>();
    public RecyclerView GetShop(RecyclerView recyclerView, Context context,String TSid){


        GetShopsFromFirebase= FirebaseDatabase.getInstance().getReference("Resturents");
        GetShopsFromFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                shop_adapters_Array_List.clear();


                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Shop_Adapter shop_adapter = dataSnapshot.getValue(Shop_Adapter.class);
                    
                    if (shop_adapter.getTSid().trim().equals(TSid.trim())) {

                        shop_adapters_Array_List.add(shop_adapter);
                    }
                }
                Shop_Recycler shop = new Shop_Recycler(context, shop_adapters_Array_List);
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),
                        LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(shop);
                shop.notifyDataSetChanged();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return recyclerView;

}
}

