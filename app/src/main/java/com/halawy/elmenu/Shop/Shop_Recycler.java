package com.halawy.elmenu.Shop;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.halawy.elmenu.R;
import com.halawy.elmenu.Show_Product.Show_Shop_Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Shop_Recycler extends RecyclerView.Adapter<Shop_Recycler.Shop_Holder> {
    private static String key_Rid=null;
    Intent intent ;
    private interface GetRid{
        void Get_Data_Rid(String Rid);
    }
    GetRid getRid;
    Context context;
    ArrayList<Shop_Adapter> shop_adapters=new ArrayList<>();

    public Shop_Recycler(Context context, ArrayList<Shop_Adapter> shop_adapters) {
        this.context = context;
        this.shop_adapters = shop_adapters;
    }

    @NonNull
    @Override
    public Shop_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.shop,parent,false);
        return new Shop_Recycler.Shop_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Shop_Holder holder, int position) {
         Picasso.with(context.getApplicationContext()).load(shop_adapters.get(position).getImage()).into(holder.Image_Shop);
        holder.Name_Shop.setText(shop_adapters.get(position).getName_Shop());
        holder.Label_Shop.setText(shop_adapters.get(position).getLabel());

    }

    @Override
    public int getItemCount() {
        return shop_adapters.size();
    }

    public class Shop_Holder extends RecyclerView.ViewHolder{
        ImageView Image_Shop;
        TextView Name_Shop,Label_Shop;

        public Shop_Holder(@NonNull View itemView) {
            super(itemView);
            Image_Shop=itemView.findViewById(R.id.image_shop);
            Name_Shop=itemView.findViewById(R.id.name_shop);
            Label_Shop=itemView.findViewById(R.id.label_shop);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Check_Card(getAdapterPosition(),view);
                    intent = new Intent(context.getApplicationContext(), Show_Shop_Product.class);
                    intent.putExtra("Rid", shop_adapters.get(getAdapterPosition()).getRid());
                    intent.putExtra("Name_Shop", shop_adapters.get(getAdapterPosition()).getName_Shop());
                    intent.putExtra("Address", shop_adapters.get(getAdapterPosition()).getAddress());
                    intent.putExtra("image_shop", shop_adapters.get(getAdapterPosition()).getImage());
                }
            });

        }
    }
    private void Check_Card(int pos,View view){
        FirebaseDatabase.getInstance().getReference("Order").child(FirebaseAuth.
                getInstance().getCurrentUser()
                .getUid()).child("Data_Resturent").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            String Rid = snapshot.child("Rid").getValue(String.class);
                            String Rid_Check = shop_adapters.get(pos).getRid().trim();
                            if (Rid.trim().equals(Rid_Check.trim())) {

                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            } else {
                                getdialog(view,pos);
                            }
                            return;
                        }


                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
    public void getdialog(View view,int pos){
        android.app.Dialog dialog = new android.app.Dialog(view.getRootView().getContext());
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(R.layout.dialog);
        final Button button_yes = dialog.findViewById(R.id.button_yes);
        final Button button_no = dialog.findViewById(R.id.botton_no);
        dialog.show();


        button_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View views) {


                FirebaseDatabase.getInstance().getReference("Order").child(FirebaseAuth.getInstance().getCurrentUser()
                        .getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                        }
                    }
                });
                dialog.dismiss();

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
        button_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

            }
        });

    }

}
