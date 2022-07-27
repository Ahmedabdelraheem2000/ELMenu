package com.halawy.elmenu.Required;

import android.content.Context;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.halawy.elmenu.Order.Order_Adapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_Order_To_My_Purchases {




     com.halawy.elmenu.Card.Fee fee ;
     float total_amount;

    DatabaseReference Fee= FirebaseDatabase.getInstance().getReference("Fee");
    DatabaseReference data_information= FirebaseDatabase.getInstance().getReference("Required_Order")

            .push();

    DatabaseReference Database_Shop= FirebaseDatabase.getInstance().getReference("Order").child(FirebaseAuth.getInstance().getCurrentUser()
        .getUid()).child("Data_Resturent");

            DatabaseReference Data_Order= FirebaseDatabase.getInstance().getReference("Order").child(FirebaseAuth.getInstance().getCurrentUser()
            .getUid()).child("Data_Order");

        public void Order_Required(String name, String Address, String Phone, Context context,ProgressBar progressBar){

            Information_Person information_person=new Information_Person(name,Address,Phone,FirebaseAuth.getInstance().getCurrentUser().getUid());
            data_information.child("Information_Person").setValue(information_person).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
            Database_Shop.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String Sid=snapshot.child("Rid").getValue(String.class);
                    data_information.child("Data_Shop").child("Sid").setValue(Sid).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Get_Order(context);
            data_information.child("status_order").setValue(-1).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
            data_information.child("prepairing").setValue(-1).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
            data_information.child("deliver").setValue(-1).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });


        }
        public void Get_Order(Context context){
            try{
                Data_Order.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            Order_Adapter order_adapter=dataSnapshot.getValue(Order_Adapter.class);
                            total_amount+=dataSnapshot.child("price").getValue(Float.class);

                            data_information.child("Data_Order").push().setValue(order_adapter).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                }
                            });
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                get_fee();

            }
            catch (Exception e){


            }


        }
        public void get_fee(){

            Fee.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    fee=snapshot.getValue(com.halawy.elmenu.Card.Fee.class);
                    data_information.child("Total_Fee").setValue(fee);

                            float total=total_amount;
                            total_amount+=fee.getDelivery_fee()+fee.getService_Fee();
                            data_information.child("Total_Fee").child("Total").setValue(total);
                            data_information.child("Total_Fee").child("Total_Amount").setValue(total_amount).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                }
                            });



                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
}
