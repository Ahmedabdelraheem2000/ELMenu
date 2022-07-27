package com.halawy.elmenu.Fragment_Requirment;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.halawy.elmenu.Shop.Shop_Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Get_Requirment {
    DatabaseReference Data_Shop = FirebaseDatabase.getInstance().getReference("Resturents");
    DatabaseReference Data_Requirment = FirebaseDatabase.getInstance().getReference("Required_Order");
    String fbauth=FirebaseAuth.getInstance().getCurrentUser().getUid().trim();

    set_list set_list;
    String ID_Order;
    public RecyclerView get_shop_and_status(Context context, RecyclerView recyclerView, ProgressBar progressBar, LinearLayout linearLayout) {

        Data_Requirment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<requirment_Adapter> _list_requirments = new ArrayList<>();
                _list_requirments.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String uid=dataSnapshot.child("Information_Person").child("uid").getValue(String.class).trim();
                    String Sid = dataSnapshot.child("Data_Shop").child("Sid").getValue(String.class);
                    if(uid.equals(fbauth)) {
                        if (Sid != null) {

                            int status = dataSnapshot.child("status_order").getValue(Integer.class);

                            Data_Shop.child(Sid).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {


                                    ID_Order = dataSnapshot.getKey().toString();
                                    Shop_Adapter shop_adapters = snapshot.getValue(Shop_Adapter.class);
                                    String Name_Shop = shop_adapters.getName_Shop();
                                    String Address = shop_adapters.getAddress();
                                    String Photo = shop_adapters.getImage();
                                    //set_data.dddata(Name_Shop, Address, Photo, status);


                                            _list_requirments.add(new requirment_Adapter(ID_Order, Sid, Name_Shop, Address, status, Photo));
                                            set_list.dddata(_list_requirments);




                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });



                        }
                    }

                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        set_list=new set_list() {
            @Override
            public void dddata(ArrayList<requirment_Adapter> requirment_adapters) {

                Requirment_Recycle requirment_recycle = new Requirment_Recycle(requirment_adapters, context.getApplicationContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext(),
                        RecyclerView.VERTICAL, false));
                recyclerView.setAdapter(requirment_recycle);
                requirment_recycle.notifyDataSetChanged();

                if (requirment_adapters.isEmpty()){
                    linearLayout.setVisibility(View.VISIBLE);
                }
                else{
                    linearLayout.setVisibility(View.GONE);
                }
             }
        };

        return recyclerView;
    }


    interface set_list {

        public void dddata(ArrayList<requirment_Adapter> requirment_adapters);
    }
}

