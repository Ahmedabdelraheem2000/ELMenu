package com.halawy.elmenu.Details_Order;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.halawy.elmenu.Order.Order_Adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public  class Get_Detials_Order    {

    ArrayList<Order_Adapter> order_detials_adapters=new ArrayList<>();

    public String getstatus(Integer i){
        String s;
        switch (i) {
            case -1:
                return  "جاري تاكيد الطلب";

            case 0:
                return"طلب مرفوض";

            case 1:
                return "تم تاكيد الطلب";

        }
        return null;
    }

    public String getdelivery(Integer i){
        switch (i) {
            case -1:
                return "------";
            case 0:
                return "طلبك في الطريق";
            case 1:
                return "تم توصيل الطلب";
        }
        return null;
    }

    public String getdata_prepairing(Integer i) {
        switch (i) {
            case -1:
                return "------";
            case 0:
                return"جاري تحضير الطلب";
            case 1:
                return "تم تحضير الطلب";
        }
        return null;
    }


    public RecyclerView Get_Order_(String id, Context context, RecyclerView recyclerView){
        FirebaseDatabase.getInstance().getReference("Required_Order")
                .child(id).child("Data_Order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                order_detials_adapters.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Order_Adapter order_detials_adapter=dataSnapshot.getValue(Order_Adapter.class);
                    if(order_detials_adapter==null){}
                    else{
                        order_detials_adapters.add(order_detials_adapter);
                    }
                }
                Order_Detials order_detials=new Order_Detials(context,order_detials_adapters);
                recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
                recyclerView.setAdapter(order_detials);
                order_detials.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return recyclerView;
    }
}
