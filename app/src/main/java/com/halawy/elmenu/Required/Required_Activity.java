package com.halawy.elmenu.Required;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.halawy.elmenu.MainActivity;
import com.halawy.elmenu.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Required_Activity extends AppCompatActivity {
    TextInputLayout name_required,address_required,phone_required;
    Button button_required;
    ProgressBar progress_requirment;
    Add_Order_To_My_Purchases add_order_to_my_purchases=new Add_Order_To_My_Purchases();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requirment);
        name_required=findViewById(R.id.name_required);
        address_required=findViewById(R.id.address_required);
        phone_required=findViewById(R.id.phone_required);
        button_required=findViewById(R.id.button_required);
        progress_requirment=findViewById(R.id.progress_requirment);
        button_required.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Done();
            }
        });
    }
    public void Done(){
        String name=name_required.getEditText().getText().toString();
        String address=address_required.getEditText().getText().toString();
        String phone=phone_required.getEditText().getText().toString();

        if(name.isEmpty()){
            progress_requirment.setVisibility(View.GONE);
            this.name_required.getEditText().setError("ادخل الاسم بالكامل");
            this.name_required.getEditText().requestFocus();

            return;
        }
        if(name.length()<8){
            progress_requirment.setVisibility(View.GONE);

            this.name_required.getEditText().setError("بالرجاء ادخال الاسم بالكامل");
            this.name_required.getEditText().requestFocus();
            return;
        }

        if(address.isEmpty()){
            progress_requirment.setVisibility(View.GONE);


            this.address_required.getEditText().setError("ادخل العنوان بالكامل");
            this.address_required.getEditText().requestFocus();
            return;
        }

        if(name.length()<8){
            progress_requirment.setVisibility(View.GONE);

            this.name_required.getEditText().setError("بالرجاء ادخال العنوان بالكامل");
            this.name_required.getEditText().requestFocus();
            return;
        }

        if(phone.isEmpty()){
            progress_requirment.setVisibility(View.GONE);

            this.phone_required.getEditText().setError("ادخل رقم الهاتف بالكامل");
            this.phone_required.getEditText().requestFocus();
            return;
        }
        if(phone.length()<11){
            progress_requirment.setVisibility(View.GONE);

            this.phone_required.getEditText().setError("بالرجاء ادخال رقم الهاتف الصحيح");
            this.phone_required.getEditText().requestFocus();
        }
        if(phone.length()>=11) {
            progress_requirment.setVisibility(View.GONE);

            int counter = 0;
            for (int x = 0; x < phone.length(); x++) {
                int number_acsi = (int) phone.charAt(x) - 48;

                if (number_acsi > 9 || number_acsi < 0) {
                    counter++;
                    break;
                }
            }
            if (counter > 0) {
                progress_requirment.setVisibility(View.GONE);

                this.phone_required.getEditText().setError("لا يحتوي رقم الهاتف علي رموز");
                this.phone_required.getEditText().requestFocus();
                return;
            }

        }


        add_order_to_my_purchases.Order_Required(name,address,phone,getApplicationContext(),progress_requirment);


        FirebaseDatabase.getInstance().getReference("Order").child(FirebaseAuth.getInstance()
                .getCurrentUser().getUid()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(Required_Activity.this, "تم ارسال الطلب في انتظار الموافقة", Toast.LENGTH_SHORT).show();
            }
        });






        }

    public void back(View view) {
        onBackPressed();
    }
}
