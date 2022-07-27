package com.halawy.elmenu.Next_Show;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.halawy.elmenu.Order.Order_Adapter;
import com.halawy.elmenu.Order.Order_process;
import com.halawy.elmenu.Price.Price_Adapter;
import com.halawy.elmenu.R;
import com.halawy.elmenu.Type_Size.Type_Size_Adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Next_Show_Shop extends AppCompatActivity  {
    RecyclerView recycler_size_and_price;
    String Name_Produt,Label;
     float mix_pric= 0.0F;
    private int count=1;
    ImageView add,min,image_item_show;
    RelativeLayout btn_add_to_card;
    Order_Adapter order_adapter;
    Order_process order_process=new Order_process();
    ProgressBar prograss_next_show;
    ArrayList<Price_And_Size_Adapter> price_and_size_adapters=new ArrayList<>();

    TextView price_item_next_shop,count_next_show,text_item_next_shop,lable_item_next_shop;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_show_shop);
        Name_Produt=getIntent().getStringExtra("Name_Product");
        Label=getIntent().getStringExtra("Label");
        recycler_size_and_price=findViewById(R.id.recycler_size_and_price);
        image_item_show=findViewById(R.id.image_item_show);
        text_item_next_shop=findViewById(R.id.text_item_next_shop);
        lable_item_next_shop=findViewById(R.id.lable_item_next_shop);
        prograss_next_show=findViewById(R.id.prograss_next_show);

        price_item_next_shop=findViewById(R.id.price_item_next_shop);
        count_next_show=findViewById(R.id.count_next_show);
        btn_add_to_card=findViewById(R.id.btn_add_to_card);
        add=findViewById(R.id.add);
        min=findViewById(R.id.min);
        count_next_show.setText(String.valueOf(count));

        select();
        text_item_next_shop.setText(getIntent().getStringExtra("Name_Product"));
        lable_item_next_shop.setText(getIntent().getStringExtra("Label"));
        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("image")).into(image_item_show);

    }



    void select(){

        FirebaseDatabase.getInstance().getReference("Type_Size").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Type_Size_Adapter type_size_adapter=dataSnapshot.getValue(Type_Size_Adapter.class);
                        String Cid=type_size_adapter.getCid().trim();
                        String Rid=type_size_adapter.getRid().trim();

                    if(Cid.equals(getIntent().getStringExtra("Cid").trim())&&
                            Rid.equals(getIntent().getStringExtra("Rid").trim())){

                        FirebaseDatabase.getInstance().getReference("Price").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                price_and_size_adapters.clear();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        for(DataSnapshot dataSnapshot1:snapshot1.getChildren()) {
                                            Price_Adapter price_adapter=dataSnapshot1.getValue(Price_Adapter.class);

                                            if(price_adapter.getTid().trim().equals(type_size_adapter.getTid().trim())&&
                                                    Cid.equals(price_adapter.getCid().trim())&&Rid.equals(price_adapter.getRid().trim())){

                                                String Iid=price_adapter.getIid().trim();
                                                String Tid=price_adapter.getTid().trim();
                                                String Pid=price_adapter.getPid().trim();

                                                price_and_size_adapters.add(new Price_And_Size_Adapter(Rid,Cid,Iid,Tid,Pid,
                                                        price_adapter.getPrices(),type_size_adapter.getName_size()));

                                            }
                                        }
                                        mix_pric= price_and_size_adapters.get(0).getPrice();
                                        count_next_show.setText(String.valueOf(count));
                                        mix_pric=mix_pric*count;
                                        price_item_next_shop.setText(mix_pric+" جنيه ");
                                        add.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if(count<1){}
                                                count+=1;
                                                count_next_show.setText(String.valueOf(count));
                                                mix_pric= price_and_size_adapters.get(0).getPrice();
                                                count_next_show.setText(String.valueOf(count));
                                                mix_pric=mix_pric*count;
                                                price_item_next_shop.setText(mix_pric+" جنيه ");

                                            }
                                        });
                                        min.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                count-=1;
                                                if(count<1){
                                                    count=1;
                                                }

                                                count_next_show.setText(String.valueOf(count));
                                                mix_pric= price_and_size_adapters.get(0).getPrice();
                                                count_next_show.setText(String.valueOf(count));
                                                mix_pric=mix_pric*count;
                                                price_item_next_shop.setText(mix_pric+" جنيه ");

                                            }
                                        });
                                        btn_add_to_card.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                count_next_show.setText(String.valueOf(count));
                                                mix_pric= price_and_size_adapters.get(0).getPrice();
                                                count_next_show.setText(String.valueOf(count));
                                                mix_pric=mix_pric*count;

                                                float Price= mix_pric;
                                                String Size=price_and_size_adapters.get(0).getSize();

                                                String Rid_=price_and_size_adapters.get(0).getRid();
                                                String Cid_=price_and_size_adapters.get(0).getCid();

                                                String Iid_=price_and_size_adapters.get(0).getIid();
                                                String Tid_=price_and_size_adapters.get(0).getTid();
                                                String Pid_=price_and_size_adapters.get(0).getPid();


                                               order_adapter=  new Order_Adapter(Name_Produt,Price,Label,Size,Rid_,Cid_,Iid_,Tid_,Pid_,count,"");

                                                boolean check=  order_process.SetData_Order(order_adapter,getApplicationContext(),getIntent().getStringExtra("Rid"));
                                              new Handler().postDelayed(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      prograss_next_show.setVisibility(View.VISIBLE);
                                                      if(check==true) {
                                                          onBackPressed();
                                                      }
                                                  }
                                              },700);


                                            }
                                        });
                                        Price_And_Size price_and_size=new Price_And_Size(price_and_size_adapters, getApplicationContext(), new Price_And_Size.price_and_click() {
                                            @Override
                                            public void click_price(float price, int postion) {
                                                if (count < 1) {
                                                    count = 1;
                                                }
                                                mix_pric = price;
                                                count_next_show.setText(String.valueOf(count));

                                                mix_pric = mix_pric * count;
                                                price_item_next_shop.setText(mix_pric + " جنيه ");
                                                add.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        count += 1;
                                                        if (count < 1) {
                                                            count = 1;
                                                        }
                                                        count_next_show.setText(String.valueOf(count));
                                                        mix_pric = price;
                                                        count_next_show.setText(String.valueOf(count));

                                                        mix_pric = mix_pric * count;
                                                        price_item_next_shop.setText(mix_pric + " جنيه ");

                                                    }
                                                });
                                                min.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        count -= 1;
                                                        if (count < 1) {
                                                            count = 1;
                                                        }
                                                        count_next_show.setText(String.valueOf(count));
                                                        mix_pric = price;
                                                        count_next_show.setText(String.valueOf(count));

                                                        mix_pric = mix_pric * count;
                                                        price_item_next_shop.setText(mix_pric + " جنيه ");

                                                    }
                                                });

                                                btn_add_to_card.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        float Price= mix_pric;
                                                        String Size=price_and_size_adapters.get(postion).getSize();
                                                        String Rid_=price_and_size_adapters.get(postion).getRid();
                                                        String Cid_=price_and_size_adapters.get(postion).getCid();

                                                        String Iid_=price_and_size_adapters.get(postion).getIid();
                                                        String Tid_=price_and_size_adapters.get(postion).getTid();
                                                        String Pid_=price_and_size_adapters.get(postion).getPid();

                                                        order_adapter=  new Order_Adapter(Name_Produt,Price,Label,Size,Rid_,Cid_,Iid_,Tid_,Pid_,count,"");
                                                       boolean check = order_process.SetData_Order(order_adapter,getApplicationContext(),getIntent().getStringExtra("Rid"));
                                                        new Handler().postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                prograss_next_show.setVisibility(View.VISIBLE);
                                                                if(check==true) {
                                                                    onBackPressed();
                                                                }
                                                            }
                                                        },700);

                                                    }
                                                });
                                            }
                                        });
                                        prograss_next_show.setVisibility(View.GONE);

                                        recycler_size_and_price.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
                                        recycler_size_and_price.setAdapter(price_and_size);
                                        price_and_size.notifyDataSetChanged();
                                    }

                                },100);


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

   }


}
