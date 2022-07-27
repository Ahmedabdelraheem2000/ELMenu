package com.halawy.elmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class Forget_Password extends AppCompatActivity {
    TextInputLayout email_forget;
    TextView test_fp;
    ProgressBar progressbar_fp;
    RelativeLayout btn_fp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        email_forget=findViewById(R.id.email_forget);
        test_fp=findViewById(R.id.test_fp);
        progressbar_fp=findViewById(R.id.progressbar_fp);
        btn_fp=findViewById(R.id.btn_fp);
    }



    public void signin(View view) {
        onBackPressed();
    }

    public void forget_password(View view) {
        String email=email_forget.getEditText().getText().toString().trim();
        if(email.isEmpty()){
            this.email_forget.getEditText().setError("الحقل فارغ");
            this.email_forget.getEditText().requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            this.email_forget.getEditText().setError("يوجد خطأ في البريد الالكتروني");
            this.email_forget.getEditText().requestFocus();
            return;
        }
        if(email.length()<8){
            this.email_forget.getEditText().setError("يوجد خطأ في البريد الالكتروني");
            this.email_forget.getEditText().requestFocus();
            return;
        }
        this.progressbar_fp.setVisibility(View.VISIBLE);
        this.test_fp.setVisibility(View.GONE);
        this.btn_fp.setBackgroundTintList(getResources().getColorStateList(R.color.Gray));
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Forget_Password.this, "تم ارسال رابط لأعادة تعيين كلمة السر", Toast.LENGTH_SHORT).show();
              progressbar_fp.setVisibility(View.GONE);
               test_fp.setVisibility(View.VISIBLE);
                btn_fp.setBackgroundTintList(getResources().getColorStateList(R.color.Orange));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Forget_Password.this, "حدث خطأ برجاء اعادة المحاولة لاحقا!!", Toast.LENGTH_SHORT).show();
                progressbar_fp.setVisibility(View.GONE);
                test_fp.setVisibility(View.VISIBLE);
                btn_fp.setBackgroundTintList(getResources().getColorStateList(R.color.Orange));
            }
        });
    }
}