package com.halawy.elmenu.Shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.halawy.elmenu.Card.Card;
import com.halawy.elmenu.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Show_Shop extends AppCompatActivity {
    RecyclerView Recycler_Shop,Show_Image_Shop;
    Get_Shop get_shop = new Get_Shop();
    RelativeLayout Card_Home;
    String TSid;
    TextView title_shop,title_hint_shop,text_Shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_shop);
        Recycler_Shop = findViewById(R.id.recycler_resturants);
        Show_Image_Shop = findViewById(R.id.Show_Image_Shop);

        Card_Home = findViewById(R.id.Card_Home_);
        title_shop = findViewById(R.id.title_shop);
        title_hint_shop = findViewById(R.id.title_hint_shop);
        text_Shop = findViewById(R.id.text_Shop);

        CheckCard();



        GetDataShop();
        Show_Image_Shop();
    }

    private void GetDataShop(){
        TSid =getIntent().getStringExtra("TSid");
        String Name_Type_Shop =getIntent().getStringExtra("Name_Type_Shop");
        Recycler_Shop= get_shop.GetShop(Recycler_Shop,getApplicationContext(),TSid);
        title_shop.setText( "قسم ال"+Name_Type_Shop );
        title_hint_shop.setText( "اهلا بك في قسم ال"+Name_Type_Shop );
        text_Shop.setText( "جميع ال"+Name_Type_Shop );

    }

    public void CheckCard(){
        FirebaseDatabase.getInstance().getReference("Order").child(FirebaseAuth.getInstance().getCurrentUser().getUid()
        ).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Card_Home.setVisibility(View.VISIBLE);

                }
                else Card_Home.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Show_Image_Shop(){
        ArrayList<Image_Type_Shop > Image_Type_Shop=new ArrayList<>();

        TSid =getIntent().getStringExtra("TSid");
        FirebaseDatabase.getInstance().getReference("Type_Shope").child(TSid.trim()).child("Image_Type_Shop")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Image_Type_Shop.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    Image_Type_Shop image_type_shop=dataSnapshot.getValue(Image_Type_Shop.class);
                    Image_Type_Shop.add(image_type_shop);

                }
                Image_Recycler image_recycler=new Image_Recycler(Image_Type_Shop,getApplicationContext());
                Show_Image_Shop.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
                Show_Image_Shop.setAdapter(image_recycler);
                image_recycler.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void Card_Show_Shop(View view) {

                startActivity(new Intent(getApplicationContext(), Card.class));

    }


    public void back(View view) {
        onBackPressed();
    }
}