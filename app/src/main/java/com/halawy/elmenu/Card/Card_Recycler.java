package com.halawy.elmenu.Card;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halawy.elmenu.Order.Order_Adapter;
import com.halawy.elmenu.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Card_Recycler extends RecyclerView.Adapter<Card_Recycler.Card_Holder> {
    Context context;
    ArrayList<Order_Adapter> order_card_adapters=new ArrayList<>();
   static int count ;
    static String s;
    GetPrice getPrice=new GetPrice();

    interface Price{
        void getprice(String s);
    }
    Price price;
   int select_Add;
    public Card_Recycler(Context context, ArrayList<Order_Adapter> order_card_adapters) {
        this.context = context;
        this.order_card_adapters = order_card_adapters;
    }

    @NonNull
    @Override
    public Card_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.card,parent,false);

        return new Card_Recycler.Card_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Card_Holder holder, @SuppressLint("RecyclerView") int position) {
        ;
        holder.order_text_card.setText(order_card_adapters.get(position).getName_Product());
      holder.count_card.setText(String.valueOf(order_card_adapters.get(position).getCount()));
        holder.size_order_card.setText(" الحجم : "+order_card_adapters.get(position).getSize());
        holder.add.setImageResource(R.drawable.addd);
        holder.min.setImageResource(R.drawable.min);
        holder.price_card.setText(" السعر "+order_card_adapters.get(position).getPrice());
        holder.remove_card.setImageResource(R.drawable.ic_cross);

    }

    @Override
    public int getItemCount() {
        return order_card_adapters.size();
    }

    public class Card_Holder extends RecyclerView.ViewHolder {

        TextView order_text_card,price_card,count_card;
        TextView size_order_card;
        ImageView add,min,remove_card;
        public Card_Holder(@NonNull View itemView) {
            super(itemView);

            order_text_card=itemView.findViewById(R.id.order_text_card);
            price_card=itemView.findViewById(R.id.price_card);
            count_card=itemView.findViewById(R.id.count_card);
            size_order_card=itemView.findViewById(R.id.size_order_card);
            add=itemView.findViewById(R.id.add_card_order);
            min=itemView.findViewById(R.id.min_card_order);
            remove_card=itemView.findViewById(R.id.remove_card);


            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    HashMap hashMap=new HashMap();
                    count=order_card_adapters.get(getAdapterPosition()).getCount();
                    hashMap.put("count",++count);
                    FirebaseDatabase.getInstance().getReference("Order").
                    child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Data_Order")
                            .child(order_card_adapters.get(getAdapterPosition())
                            .getOid().trim()).updateChildren(hashMap);

                    getPrice.Price_Oid(context.getApplicationContext()
                            ,order_card_adapters.get(getAdapterPosition()).getOid());
                    notifyDataSetChanged();

                }
            });
            min.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    HashMap hashMap = new HashMap();
                    count = order_card_adapters.get(getAdapterPosition()).getCount();
                    --count;
                    if (count != 0) {
                        hashMap.put("count", count);
                        FirebaseDatabase.getInstance().getReference("Order").
                                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Data_Order")
                                .child(order_card_adapters.get(getAdapterPosition())
                                        .getOid().trim()).updateChildren(hashMap);
                        getPrice.Price_Oid(context.getApplicationContext()
                                ,order_card_adapters.get(getAdapterPosition()).getOid());
                        notifyDataSetChanged();
                    }
                }
            });
            remove_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseDatabase.getInstance().getReference("Order").child(FirebaseAuth.getInstance().getCurrentUser().getUid()
                    ).child("Data_Order").child(order_card_adapters.get(getAdapterPosition()).getOid().trim()).removeValue().addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context.getApplicationContext(), "حدث خطأ ما برجاء المحاولة لاحقا", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            });
        }
    }

}
