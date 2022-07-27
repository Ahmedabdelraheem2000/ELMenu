package com.halawy.elmenu.Fragment_Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.halawy.elmenu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class fragment_Home extends Fragment {

    RecyclerView recyclerView_shop;
    ProgressBar progressBar;
    ArrayList<Type_Shop_Adapter> type_shop_adapters=new ArrayList<>();
    DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView_shop=view.findViewById(R.id.Recycler_Shop);
        progressBar=view.findViewById(R.id.prograss_typte_shop);
        Get_Type_Shop();
        return view;
    }
    void Get_Type_Shop(){

        databaseReference =FirebaseDatabase.getInstance().getReference("Type_Shope");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                type_shop_adapters.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Type_Shop_Adapter type_shop_adapter=dataSnapshot.getValue(Type_Shop_Adapter.class);
                    type_shop_adapters.add(type_shop_adapter);

                }
                progressBar.setVisibility(View.GONE);

                Type_Shop_Recycler type_shop_recycler=new Type_Shop_Recycler(type_shop_adapters,getActivity());
                recyclerView_shop.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
                recyclerView_shop.setAdapter(type_shop_recycler);
                type_shop_recycler.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
//    @Override
//    public void onBackPressed() {
//
//
//        moveTaskToBack(true);
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(1);
//        finish();
//        super.onBackPressed();
//
//
//
//    }
}