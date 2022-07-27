package com.halawy.elmenu.Details_Order;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.halawy.elmenu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity  {
    DatabaseReference data_Details=FirebaseDatabase.getInstance().getReference("Required_Order");
    TextView title_resturent_card_details, address_resturent_card_details, anser_req_status_details,
            prepairing_details, deliver_details, total_details,
            service_fee_details, delivery_fee_details, total_amount_details;
    RecyclerView recycler_order_details;
    ImageView image_shop_details;
    Get_Detials_Order get_detials_order=new Get_Detials_Order();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detials_order);
        title_resturent_card_details = findViewById(R.id.title_resturent_card_details);
        address_resturent_card_details = findViewById(R.id.address_resturent_card_details);
        anser_req_status_details = findViewById(R.id.anser_req_status_details);
        prepairing_details = findViewById(R.id.prepairing_details);
        deliver_details = findViewById(R.id.deliver_details);
        total_details = findViewById(R.id.total_details);
        service_fee_details = findViewById(R.id.service_fee_details);
        delivery_fee_details = findViewById(R.id.delivery_fee_details);
        total_amount_details = findViewById(R.id.total_amount_details);
        recycler_order_details = findViewById(R.id.recycler_order_details);
        image_shop_details = findViewById(R.id.image_shop_details);
        String Id_Order = getIntent().getStringExtra("IdOrder");
        Get_Status(Id_Order);
        recycler_order_details=get_detials_order.Get_Order_(Id_Order,this,recycler_order_details);
        Get_Data_Shop();
    }

    public void Get_Status(String id){
        data_Details.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Integer status=snapshot.child("status_order").getValue(Integer.class);
                Integer preparing=snapshot.child("prepairing").getValue(Integer.class);
                Integer deliver=snapshot.child("deliver").getValue(Integer.class);
                anser_req_status_details.setText(get_detials_order.getstatus(status));
                prepairing_details.setText(get_detials_order.getdata_prepairing(preparing));
                deliver_details.setText(get_detials_order.getdelivery(deliver));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        data_Details.child(id).child("Total_Fee").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Float Total=snapshot.child("Total").getValue(Float.class);

                Float Total_Amount =snapshot.child("Total_Amount").getValue(Float.class);
                Float delivery_fee =snapshot.child("delivery_fee").getValue(Float.class);
                Float service_Fee =snapshot.child("service_Fee").getValue(Float.class);

                total_details.setText(Total+"");
                service_fee_details.setText(service_Fee+"");
                delivery_fee_details.setText(delivery_fee+"");
                total_amount_details.setText(Total_Amount+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Get_Data_Shop(){
        Picasso.with(Details.this).load(getIntent().getStringExtra("image_shop")).into(image_shop_details);
        title_resturent_card_details.setText(getIntent().getStringExtra("image_shop"));
        title_resturent_card_details.setText(getIntent().getStringExtra("name_shop")+"");
        address_resturent_card_details.setText(getIntent().getStringExtra("address_shop")+"");


    }

    public void back(View view) {
        onBackPressed();
    }
}
