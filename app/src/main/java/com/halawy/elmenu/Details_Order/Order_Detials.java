package com.halawy.elmenu.Details_Order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halawy.elmenu.Order.Order_Adapter;
import com.halawy.elmenu.R;

import java.util.ArrayList;

public class Order_Detials extends RecyclerView.Adapter<Order_Detials.Order_Detials_Holder> {
    Context context;
    ArrayList<Order_Adapter> order_detials_adapternew =new ArrayList<>();

    public Order_Detials(Context context, ArrayList<Order_Adapter> order_detials_adapternew) {
        this.context = context;
        this.order_detials_adapternew = order_detials_adapternew;
    }

    @NonNull
    @Override
    public Order_Detials_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.order_details,parent,false);
        return new Order_Detials.Order_Detials_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Order_Detials_Holder holder, int position) {
        holder.title_item_detials.setText(order_detials_adapternew.get(position).getName_Product());
        holder.lable_item_detials.setText(order_detials_adapternew.get(position).getLabel());
        holder.price_item_detials.setText(order_detials_adapternew.get(position).getPrice()+"");
        holder.count_detials.setText(" x "+order_detials_adapternew.get(position).getCount());
        holder.size_item_detials.setText(order_detials_adapternew.get(position).getSize()+" الحجم ");

    }

    @Override
    public int getItemCount() {
        return order_detials_adapternew.size();
    }

    public class Order_Detials_Holder extends RecyclerView.ViewHolder {
        TextView size_item_detials;
        TextView title_item_detials;
        TextView lable_item_detials;
        TextView count_detials;
        TextView price_item_detials;
        public Order_Detials_Holder(@NonNull View itemView) {
            super(itemView);
            size_item_detials=itemView.findViewById(R.id.size_item_detials);
            title_item_detials=itemView.findViewById(R.id.title_item_detials);
            lable_item_detials=itemView.findViewById(R.id.lable_item_detials);
            count_detials=itemView.findViewById(R.id.count_detials);
            price_item_detials=itemView.findViewById(R.id.price_item_detials);

        }
    }
}
