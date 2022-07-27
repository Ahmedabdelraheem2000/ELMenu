package com.halawy.elmenu.Shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halawy.elmenu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Image_Recycler extends RecyclerView.Adapter<Image_Recycler.Image_Holder> {
    ArrayList<Image_Type_Shop> image_type_shops;
    Context context;

    public Image_Recycler(ArrayList<Image_Type_Shop> image_type_shops, Context context) {
        this.image_type_shops = image_type_shops;
        this.context = context;
    }

    @NonNull
    @Override
    public Image_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.image_type_shop,parent,false);
        return new Image_Recycler.Image_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Image_Holder holder, int position) {
         Picasso.with(context.getApplicationContext()).load(image_type_shops.get(position).getImage()).into(holder.image_shop_card);

    }

    @Override
    public int getItemCount() {
        return image_type_shops.size();
    }

    public class Image_Holder extends RecyclerView.ViewHolder {
        ImageView image_shop_card;
        public Image_Holder(@NonNull View itemView) {
            super(itemView);
            image_shop_card=itemView.findViewById(R.id.image_shop_card);
        }
    }
}
