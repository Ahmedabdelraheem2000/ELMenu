package com.halawy.elmenu.Show_Product;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.halawy.elmenu.Card.Card;
import com.halawy.elmenu.Categores.Categores;
import com.halawy.elmenu.Categores.Categores_Adapter;
import com.halawy.elmenu.Click_Category.Click_category;
import com.halawy.elmenu.Item.Item;
import com.halawy.elmenu.Item.Item_Adapter;
import com.halawy.elmenu.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Show_Shop_Product extends AppCompatActivity {
    RecyclerView recyclerItem;
    RecyclerView recyclerCategory;
    CircleImageView image_Shop_show;
    TextView name_Shop_show,Address_shop;
    ProgressBar prograss_shop_product;
    Button btn_show_product;
    private DatabaseReference databaseReference;
    private ArrayList<Item_Adapter> item_adapters=new ArrayList<>();
    private DatabaseReference databaseReference_category;
    private ArrayList<Categores_Adapter> categores_adapters=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_product);
        recyclerItem=findViewById(R.id.Recycler_Item);
        recyclerCategory=findViewById(R.id.Recycler_Category);
        image_Shop_show=findViewById(R.id.image_Shop_show);
        name_Shop_show=findViewById(R.id.name_Shop_show);
        Address_shop=findViewById(R.id.Address_shop);
        name_Shop_show.setText(getIntent().getStringExtra("Name_Shop"));
        Address_shop.setText(getIntent().getStringExtra("Address"));
        prograss_shop_product=findViewById(R.id.prograss_shop_product);
        btn_show_product=findViewById(R.id.btn_show_product);
        Check_card();
        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("image_shop")).into(image_Shop_show);
        GetdataCategory(getIntent().getStringExtra("Rid"));
    }
    public void Check_card(){
       DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Order").child(FirebaseAuth
               .getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    btn_show_product.setVisibility(View.VISIBLE);
                }
                else btn_show_product.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }) ;
        btn_show_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Card.class));
            }
        });
    }

    public void GetdataCategory( String ResturentId){
        this.databaseReference_category= FirebaseDatabase.getInstance().getReference("Categores");
        this.databaseReference_category.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categores_adapters.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Categores_Adapter categores_adapter=dataSnapshot.getValue(Categores_Adapter.class);
                    String Rid=categores_adapter.getRid().trim();
                    if(Rid.equals(ResturentId.trim())){
                        categores_adapters.add(categores_adapter);
                    }
                }


                Categores categores=new Categores(categores_adapters, getApplicationContext(), new Click_category() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void Click_cateogy( String Cid) {
                        databaseReference= FirebaseDatabase.getInstance().getReference("Item");
                        databaseReference.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                item_adapters.clear();


                                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                                    Item_Adapter item_adapter=dataSnapshot.getValue(Item_Adapter.class);
                                    String Cid_check=item_adapter.getCid().trim();
                                    if(Cid.trim().equals(Cid_check)){
                                        item_adapters.add(item_adapter);
                                    }

                                }
                                Item item=new Item(item_adapters,getApplicationContext());
                                recyclerItem.setLayoutManager(new LinearLayoutManager(recyclerItem.getContext(),RecyclerView.VERTICAL,false));
                                recyclerItem.setAdapter(item);
                                item.notifyDataSetChanged();
                                prograss_shop_product.setVisibility(View.GONE);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


                recyclerCategory.setLayoutManager(new LinearLayoutManager(recyclerCategory.getContext(),RecyclerView.HORIZONTAL,false));
                recyclerCategory.setAdapter(categores);

                categores.notifyDataSetChanged();

    }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


    });

    }


    public void back(View view) {
        onBackPressed();
    }
}
