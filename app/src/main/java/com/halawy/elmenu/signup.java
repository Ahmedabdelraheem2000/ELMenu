package com.halawy.elmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth auth;
    private EditText fristname,lastname,email,password,phone;
    private RelativeLayout register_btn;
    private TextView register_text;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        auth=FirebaseAuth.getInstance();
        register_btn = (RelativeLayout) findViewById(R.id.register_btn_signup);
        register_text = (TextView) findViewById(R.id.register_text_signup);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_signup);
        fristname = (EditText) findViewById(R.id.fristname_signup);
        lastname = (EditText) findViewById(R.id.lastname_signup);
        email = (EditText) findViewById(R.id.email_signup_signup);
        password = (EditText) findViewById(R.id.password_signup_signup);
        phone = (EditText) findViewById(R.id.phonenumber_signup);

        register_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_btn_signup:
                test();
                break;
        }
    }

    public void test(){
        String fristname=this.fristname.getText().toString().trim();
        String lastname=this.lastname.getText().toString().trim();
        String email=this.email.getText().toString().trim();
        String password=this.password.getText().toString().trim();
        String phone=this.phone.getText().toString().trim();
        if(fristname.isEmpty()){
            this.fristname.setError("ادخل الاسم الاول");
            this.fristname.requestFocus();
            return;
        }
        if(lastname.isEmpty()){
            this.lastname.setError("ادخل الاسم الاخير");
            this.lastname.requestFocus();
            return;
        }
        if(email.isEmpty()){
            this.email.setError("ادخل البريد الالكتروني");
            this.email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            this.email.setError("يوجد خطأ في البريد الالكتروني");
            this.email.requestFocus();
            return;
        }
        if(password.isEmpty()){
            this.password.setError("ادخل الرقم السري");
            this.password.requestFocus();
            return;
        }
        if(password.length()<8){
            this.password.setError("رقم السري ضعيف");
            this.password.requestFocus();
            return;
        }
        if(phone.length()<11){
            this.phone.setError("رقم الهاتف ناقص");
            this.phone.requestFocus();
            return;
        }
        register_btn.setBackgroundTintList(getResources().getColorStateList(R.color.Gray));
        register_text.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user1=new User(email,fristname,lastname,phone);
                    FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),signin.class));
                                register_btn.setBackgroundTintList(getResources().getColorStateList(R.color.Orange));
                                register_text.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                Toast.makeText(getApplicationContext(), "البريد الالكتروني موجود بالفعل", Toast.LENGTH_SHORT).show();
                register_btn.setBackgroundTintList(getResources().getColorStateList(R.color.Orange));
                register_text.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }



}