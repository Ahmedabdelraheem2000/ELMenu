package com.halawy.elmenu.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.halawy.elmenu.R;
import com.halawy.elmenu.User;
import com.halawy.elmenu.signin;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fragment_setting extends Fragment {
    Button signout;
    RelativeLayout relative_setting;
    TextInputLayout f_name,l_name,email,phone;
    NestedScrollView nestedScrollView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_setting, container, false);
        f_name=view.findViewById(R.id.f_name);
        l_name=view.findViewById(R.id.l_name);
        email=view.findViewById(R.id.email);
        phone=view.findViewById(R.id.phone);
        nestedScrollView=view.findViewById(R.id.nested_setting);
        signout=view.findViewById(R.id.logout);
        relative_setting=view.findViewById(R.id.relative_setting);
        Get_Data_Person();
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relative_setting.setVisibility(View.VISIBLE);
                FirebaseAuth.getInstance().signOut();
                SharedPreferences sharedPreferences= getContext().getSharedPreferences("savefile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove("email");
                editor.remove("password");
                editor.clear();
                editor.apply();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        relative_setting.setVisibility(View.GONE);

                        startActivity(new Intent(getActivity().getApplicationContext(),signin.class));
                        getActivity().finish();
                    }
                }, 1000);

            }
        });
        return view;
    }

 public void Get_Data_Person(){
     FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()
     ).addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
            //    TextInputLayout f_name,l_name,email,address,phone;
             User user=snapshot.getValue(User.class);
             f_name.getEditText().setText(user.getFristname());
             l_name.getEditText().setText(user.getLastname());
             email.getEditText().setText(user.getEmail());
             phone.getEditText().setText(user.getPhone());


         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
     });
     if(f_name.getEditText().getText()!=null&&l_name.getEditText().getText()!=null&&email.getEditText().getText()!=null&&
             phone.getEditText().getText()!=null){
         nestedScrollView.setVisibility(View.VISIBLE);
         relative_setting.setVisibility(View.GONE);
     }

 }
}