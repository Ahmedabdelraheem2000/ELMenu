package com.halawy.elmenu.Fragment_Requirment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halawy.elmenu.Details_Order.Details;
import com.halawy.elmenu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Requirment_Recycle extends RecyclerView.Adapter<Requirment_Recycle.Requirment_Hundler> {
    ArrayList<requirment_Adapter> requirment_adapters=new ArrayList<>();
    Context context;

    public Requirment_Recycle(ArrayList<requirment_Adapter> requirment_adapters, Context context) {
        this.requirment_adapters = requirment_adapters;
        this.context = context;
    }

    @NonNull
    @Override
    public Requirment_Hundler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);

        View view=layoutInflater.inflate(R.layout.requirments_process,parent,false);
        return new Requirment_Recycle.Requirment_Hundler(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull Requirment_Hundler holder, int position) {
        holder.name_shop_requirment.setText(requirment_adapters.get(position).getName_Shop());
        holder.address_requirment.setText(requirment_adapters.get(position).getAddress());
        int status=requirment_adapters.get(position).getStatus();

        Picasso.with(context.getApplicationContext()).load(requirment_adapters.get(position).getImage_Shop()).into(holder.image_shop_requirment);

        if(status==-1){
            holder.status_requirment.setText("جاري تاكيد الطلب");
            holder.status_requirment.setTextColor(context.getResources().getColor(R.color.Gray));

        }
        else if(status==1){
            holder.status_requirment.setText("تم تاكيد الطلب");
            holder.status_requirment.setTextColor(context.getResources().getColor(R.color.Orange));

        }

        else if(status==0){
            holder.status_requirment.setText("تم رفض الطلب");
            holder.status_requirment.setTextColor(context.getResources().getColor(R.color.Red));

        }
    }

    @Override
    public int getItemCount() {
        return requirment_adapters.size();
    }

    public class Requirment_Hundler extends RecyclerView.ViewHolder {
        TextView name_shop_requirment,address_requirment,status_requirment;
        ImageView image_shop_requirment;
        public Requirment_Hundler(@NonNull View itemView) {
            super(itemView);
            name_shop_requirment=itemView.findViewById(R.id.name_shop_requirment);
            address_requirment=itemView.findViewById(R.id.address_requirment);
            status_requirment=itemView.findViewById(R.id.status_requirment);
            image_shop_requirment=itemView.findViewById(R.id.image_shop_requirment);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                            String Id = requirment_adapters.get(getAdapterPosition()).getIdorder();
                             Toast.makeText(context.getApplicationContext(), ""+Id, Toast.LENGTH_SHORT).show();
                            if (Id != null) {
                                Intent intent = new Intent(context.getApplicationContext(), Details.class);
                                intent.putExtra("IdOrder", Id);
                                intent.putExtra("name_shop", requirment_adapters.get(getAdapterPosition()).getName_Shop());
                                intent.putExtra("address_shop", requirment_adapters.get(getAdapterPosition()).getAddress());
                                intent.putExtra("image_shop", requirment_adapters.get(getAdapterPosition()).getImage_Shop());

                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                notifyDataSetChanged();
                            }


                }
            });
        }
    }
}
