package com.halawy.elmenu.Item;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halawy.elmenu.Next_Show.Next_Show_Shop;
import com.halawy.elmenu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Item extends RecyclerView.Adapter<Item.Item_Holder> {
    ArrayList<Item_Adapter> item_adapters=new ArrayList<>();
    Context context;

    public Item(ArrayList<Item_Adapter> item_adapters, Context context) {
        this.item_adapters = item_adapters;
        this.context = context;
    }

    @NonNull
    @Override
    public Item_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.item,parent,false);
        return new Item.Item_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Item_Holder holder, int position) {
         Picasso.with(context.getApplicationContext()).load(item_adapters.get(position).getImage()).into(holder.image_item);
         holder.name_item.setText(item_adapters.get(position).getName());
        holder.price_item.setText(item_adapters.get(position).getPrice()+" جنيه ");


    }

    @Override
    public int getItemCount() {
        return item_adapters.size();
    }

    public class Item_Holder extends RecyclerView.ViewHolder{
        ImageView image_item;
        TextView name_item,price_item;
        public Item_Holder(View view){
            super(view);
            image_item=view.findViewById(R.id.image_item);
            name_item=view.findViewById(R.id.name_item);
            price_item=view.findViewById(R.id.price_item);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent=new Intent(context.getApplicationContext(), Next_Show_Shop.class);

                    intent.putExtra("Iid",item_adapters.get(getAdapterPosition()).getIid());
                    intent.putExtra("Cid",item_adapters.get(getAdapterPosition()).getCid());
                    intent.putExtra("Rid",item_adapters.get(getAdapterPosition()).getRid());
                    intent.putExtra("Name_Product",item_adapters.get(getAdapterPosition()).getName());
                    intent.putExtra("Label",item_adapters.get(getAdapterPosition()).getConists_of());
                    intent.putExtra("image",item_adapters.get(getAdapterPosition()).getImage());

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
