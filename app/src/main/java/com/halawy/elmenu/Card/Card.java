package com.halawy.elmenu.Card;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.halawy.elmenu.Order.Order_Adapter;
import com.halawy.elmenu.R;
import com.halawy.elmenu.Required.Required_Activity;
import com.halawy.elmenu.Shop.Shop_Adapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class Card extends AppCompatActivity {
    RecyclerView recyclerView_Order_Card;
    GetData_Card_Order getData_card_order;
   static float price= 0.0F;
    static float Delivery_fee;
    static float Service_Fee;
    ImageView image_shop_card_1;
    TextView title_resturent_card,address_resturent_card;

    TextView total,service_fee,delivery_fee;
    Button button_card;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_order);

        image_shop_card_1=findViewById(R.id.image_shop_card_1);
        title_resturent_card=findViewById(R.id.title_resturent_card);
        address_resturent_card=findViewById(R.id.address_resturent_card);

        getData_card_order=new GetData_Card_Order();
        recyclerView_Order_Card=findViewById(R.id.recycler_card);
        total=findViewById(R.id.total);
        button_card=findViewById(R.id.button_card);
        service_fee=findViewById(R.id.service_fee);
        delivery_fee=findViewById(R.id.delivery_fee);
        Fee();
        Fun();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                fun_totla();
            }
        },100);
        Get_Data_Shop();
        recyclerView_Order_Card=getData_card_order.GetData_Order(recyclerView_Order_Card,getApplicationContext());


    }

    public void fun_totla(){


        FirebaseDatabase.getInstance().getReference("Order").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Data_Order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Order_Adapter order_adapter = dataSnapshot.getValue(Order_Adapter.class);
                   price += Float.valueOf(order_adapter.getPrice());
                }
                total.setText(""+price);
                price=0;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void Fee(){
        FirebaseDatabase.getInstance().getReference("Fee").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Delivery_fee= snapshot.child("Delivery_fee").getValue(Float.class);
                Service_Fee= snapshot.child("Service_Fee").getValue(Float.class);
                service_fee.setText(""+Service_Fee);
                delivery_fee.setText(""+Delivery_fee);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void Fun(){
        FirebaseDatabase.getInstance().getReference("Order").child(FirebaseAuth.getInstance().getCurrentUser().getUid()
        ).child("Data_Order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists()){

                    FirebaseDatabase.getInstance().getReference("Order").child(FirebaseAuth.getInstance().getCurrentUser().getUid()
                    ).removeValue().addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "حدث خطأ ما اعد المحاولة لاحقا", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    button_card.setVisibility(View.GONE);

                                    onBackPressed();
                                }
                            },300);

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void Get_Data_Shop(){
        FirebaseDatabase.getInstance().getReference("Order").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Data_Resturent")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        FirebaseDatabase.getInstance().getReference("Resturents").child(snapshot.child("Rid").getValue(String.class))
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        Shop_Adapter shop_adapter=snapshot.getValue(Shop_Adapter.class);
                                        String image=shop_adapter.getImage();
                                        String name_shop=shop_adapter.getName_Shop();
                                        String address=shop_adapter.getAddress();
                                        title_resturent_card.setText(name_shop+"");
                                        address_resturent_card.setText(address+"");
                                        Picasso.with(Card.this).load(image).into(image_shop_card_1);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void push_now(View view) {
        startActivity(new Intent(Card.this, Required_Activity.class));

    }
}
