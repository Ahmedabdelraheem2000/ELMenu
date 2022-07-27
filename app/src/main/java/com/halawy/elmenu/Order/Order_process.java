package com.halawy.elmenu.Order;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.halawy.elmenu.Card.GetPrice;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Order_process {
    boolean check=true;
    private Context context;
    private HashMap hashMap=new HashMap<>();
    private DatabaseReference databaseReference;
   private static  String Snab=null;
    private static int conter;
    private String Rid=null;
    private final String Data_Resturent="Data_Resturent";
    private final String Data_Order="Data_Order";
    private String Oid;
    GetPrice getPrice=new GetPrice();

    private void Check_Resturent(Order_Adapter order_adapter){
        String Rid=order_adapter.getRid().trim();
        String Cid=order_adapter.getCid().trim();
        String Tid=order_adapter.getTid().trim();
        String Iid=order_adapter.getIid().trim();

        databaseReference=FirebaseDatabase.getInstance().getReference("Order")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(Data_Order);


        FirebaseDatabase.getInstance().getReference("Order").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(Data_Order)
    .addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
                     for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                         Order_Adapter Data=dataSnapshot.getValue(Order_Adapter.class);


                         if(Rid.equals(Data.getRid())&&Data.getCid().equals(Cid)&&
                                 Data.getTid().equals(Tid)&&Data.getIid().equals(Iid)){
                             Snab=dataSnapshot.getKey();
                             conter=Data.getCount();

                             return;
                         }

                     }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

    }


    public boolean SetData_Order(Order_Adapter order_adapter, Context context, String Rid) {

        this.Rid=Rid;
        this.context = context;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Check_Resturent(order_adapter);

            }
        },300);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Snab != null) {
                    conter += order_adapter.getCount();

                    hashMap.put("count", conter);
                    databaseReference.child(Snab).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            check=true;
                            Toast.makeText(context.getApplicationContext(), "تم الاضافة الي السلة", Toast.LENGTH_SHORT).show();
                        }
                    });

                    return;
                } else {
                    HashMap hashMap=new HashMap();
                    hashMap.put("Rid",Rid);
                    FirebaseDatabase.getInstance().getReference("Order")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(Data_Resturent)
                            .setValue(hashMap);

                Oid  = FirebaseDatabase.getInstance().getReference().push().getKey();
                    order_adapter.setOid(Oid);
                    FirebaseDatabase.getInstance().getReference("Order")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(Data_Order).child(Oid)
                            .setValue(order_adapter).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            check=true;
                            Toast.makeText(context.getApplicationContext(), "تم الاضافة الي السلة", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context.getApplicationContext(), "حدث خطأ بالرجاء اعادة المحاولة", Toast.LENGTH_SHORT).show();

                        }
                    });
                    //Oid.setValue(order_adapter);
                }
            }
        }, 1000);
        Snab=null;
        return check;
    }
}
