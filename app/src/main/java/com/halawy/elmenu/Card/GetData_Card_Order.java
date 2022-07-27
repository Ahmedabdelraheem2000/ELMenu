package com.halawy.elmenu.Card;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.halawy.elmenu.Order.Order_Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class GetData_Card_Order {

    ArrayList<Order_Adapter> Array_Order=new ArrayList<>();

    interface interface_getprice{
        public void price(String pid,Order_Adapter order_adapter);
    }
    GetPrice.interface_getprice interface_getprice;
    private  DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Order")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Data_Order");
    private HashMap hashMap=new HashMap();

   public RecyclerView GetData_Order(RecyclerView recyclerView,Context context){
        GetPrice getPrice=new GetPrice();
       DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Order")
               .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
               .child("Data_Order");
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               Array_Order.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Order_Adapter order_adapter=dataSnapshot.getValue(Order_Adapter.class);
                    if(order_adapter.getOid()==null){
                        Toast.makeText(context.getApplicationContext(), "حدث خطأ ما برجاء اعادة المحاولة مرة اخري", Toast.LENGTH_SHORT).show();
                    }
                    if(order_adapter.getOid()!=null){
                        FirebaseDatabase.getInstance().getReference("Order").child(FirebaseAuth.getInstance().getCurrentUser().getUid()
                        ).child("Data_Order").child(order_adapter.getOid()).addListenerForSingleValueEvent(new ValueEventListener() {
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

                    }
                    interface_getprice=new GetPrice.interface_getprice() {
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
                                            hashMap.put("price",All_Price );
                                            databaseReference.child(order_adapter.getOid().trim()).updateChildren(hashMap);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    }
                            );
                        }
                    };
                    Array_Order.add(order_adapter);

                }

               Card_Recycler card_recycler=new Card_Recycler(context,Array_Order);
                recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
                recyclerView.setAdapter(card_recycler);
                card_recycler.notifyDataSetChanged();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
       return recyclerView;
   }
}

