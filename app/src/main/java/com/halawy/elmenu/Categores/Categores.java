package com.halawy.elmenu.Categores;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.halawy.elmenu.Click_Category.Click_category;
import com.halawy.elmenu.R;

import java.util.ArrayList;

public class Categores extends RecyclerView.Adapter<Categores.Categores_Holder> {
     ArrayList<Categores_Adapter> categores_adapters=new ArrayList<>();
    Context context;
    Click_category click_catehory;
    int select=0;

    public Categores(ArrayList<Categores_Adapter> categores_adapters, Context context, Click_category click_catehory) {
        this.categores_adapters = categores_adapters;
        this.context = context;
        this.click_catehory = click_catehory;
    }

    @NonNull
    @Override
    public Categores_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.category,parent,false);
        return new Categores.Categores_Holder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull Categores_Holder holder, int position) {
        holder.text_Category.setText(categores_adapters.get(position).getName());
        if(select==position) {
            holder.text_Category.setTextColor(context.getColor(R.color.white));
            holder.Category_CardView.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.Orange)));
        }
        else{
            holder.text_Category.setTextColor(context.getColor(R.color.black));
            holder.Category_CardView.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.white)));
        }
    }

    @Override
    public int getItemCount() {
        return categores_adapters.size();
    }

    public class Categores_Holder extends RecyclerView.ViewHolder {
        TextView text_Category;
        CardView Category_CardView;
        public Categores_Holder(@NonNull View itemView) {
            super(itemView);
            text_Category=itemView.findViewById(R.id.text_Category);
            Category_CardView=itemView.findViewById(R.id.Category_CardView);
            final int c=0;
           String frist_Click=categores_adapters.get(c).getCid();
            click_catehory.Click_cateogy(frist_Click);
            Category_CardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String Cid=categores_adapters.get(getAdapterPosition()).getCid();

                    select=getAdapterPosition();
                    click_catehory.Click_cateogy(Cid);

                    notifyDataSetChanged();
                }
            });

        }
    }
}
