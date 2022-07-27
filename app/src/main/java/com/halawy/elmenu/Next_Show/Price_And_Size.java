package com.halawy.elmenu.Next_Show;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halawy.elmenu.R;

import java.util.ArrayList;

public class Price_And_Size extends RecyclerView.Adapter<Price_And_Size.Price_And_Size_Holder> {
    ArrayList<Price_And_Size_Adapter> price_and_size_adapters=new ArrayList<>();
    Context context;
    interface price_and_click{
        void click_price(float price,int postion);
    }
    price_and_click price_and_click;
    int select=0;
    public Price_And_Size(ArrayList<Price_And_Size_Adapter> price_and_size_adapters, Context context,price_and_click price_and_click) {
        this.price_and_size_adapters = price_and_size_adapters;
        this.context = context;
        this.price_and_click = price_and_click;

    }

    @NonNull
    @Override
    public Price_And_Size_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.radio_button_sizeandprice,parent,false);
        return new Price_And_Size.Price_And_Size_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Price_And_Size_Holder holder, int position) {
        holder.text_price_radio_button.setText(price_and_size_adapters.get(position).getPrice()+" جنيه ");
        holder.text_size_radio_button.setText(price_and_size_adapters.get(position).getSize());

        if(select==position){
            holder.radio_button.setButtonDrawable(R.drawable.checkbox_full);
        }
        else {
            holder.radio_button.setButtonDrawable(R.drawable.checkbox_empty);
        }

    }

    @Override
    public int getItemCount() {
        return price_and_size_adapters.size();
    }

    public class Price_And_Size_Holder extends RecyclerView.ViewHolder {
        RadioButton radio_button;
        TextView text_size_radio_button;
        TextView text_price_radio_button;

        public Price_And_Size_Holder(@NonNull View itemView) {
            super(itemView);
            radio_button=itemView.findViewById(R.id.radio_button);
            text_size_radio_button=itemView.findViewById(R.id.text_size_radio_button);
            text_price_radio_button=itemView.findViewById(R.id.text_price_radio_button);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    select=getAdapterPosition();
                    price_and_click.click_price(price_and_size_adapters.get(getAdapterPosition()).getPrice()
                            ,select);
                    notifyDataSetChanged();
                }
            });
            radio_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    select=getAdapterPosition();
                    price_and_click.click_price(price_and_size_adapters.get(getAdapterPosition()).getPrice()
                            ,select);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
