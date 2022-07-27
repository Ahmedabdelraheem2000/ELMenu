package com.halawy.elmenu.Fragment_Home;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halawy.elmenu.R;
import com.halawy.elmenu.Shop.Show_Shop;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Type_Shop_Recycler extends RecyclerView.Adapter<Type_Shop_Recycler.Type_Shop_Holder> {
    ArrayList<Type_Shop_Adapter> type_shop_adapters;
    Context context;

    public Type_Shop_Recycler(ArrayList<Type_Shop_Adapter> type_shop_adapters, Context context) {
        this.type_shop_adapters = type_shop_adapters;
        this.context = context;
    }

    @NonNull
    @Override
    public Type_Shop_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.data_type_shop,parent,false);

        return new Type_Shop_Recycler.Type_Shop_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Type_Shop_Holder holder, int position) {
        holder.Name_type_shop.setText(type_shop_adapters.get(position).getName());
        Picasso.with(context).load(type_shop_adapters.get(position).getImage()).into(holder.image_type_shop);


    }

    @Override
    public int getItemCount() {
        return type_shop_adapters.size();
    }

    public class Type_Shop_Holder extends RecyclerView.ViewHolder {
        ImageView image_type_shop;
        TextView Name_type_shop;
        public Type_Shop_Holder(@NonNull View itemView) {
            super(itemView);
            image_type_shop=itemView.findViewById(R.id.image_type_shop1);
            Name_type_shop=itemView.findViewById(R.id.Name_type_shop);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(context.getApplicationContext(), Show_Shop.class);
                            intent.putExtra("TSid",type_shop_adapters.get(getAdapterPosition()).getTSid().trim());
                            intent.putExtra("Name_Type_Shop",type_shop_adapters.get(getAdapterPosition()).getName().trim());
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            notifyDataSetChanged();
                        }
                    }, 200);

                }
            });
        }
    }
}
