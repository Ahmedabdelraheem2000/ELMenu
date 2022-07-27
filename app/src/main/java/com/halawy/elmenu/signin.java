package com.halawy.elmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class signin extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth auth;
    private EditText email,password;
    private RelativeLayout signin_btn;
    private TextView signin_text;

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        auth=FirebaseAuth.getInstance();

        signin_btn=findViewById(R.id.btn_signin);
        email=findViewById(R.id.email_signin);
        password=findViewById(R.id.password_signin);
        signin_text=findViewById(R.id.btn_text_signin);
        progressBar=findViewById(R.id.progressbar_signin);
        signin_btn.setOnClickListener(this);
        sign_in_with_shared_preferences();

    }

    private void sign_in_with_shared_preferences(){
        SharedPreferences shared=getSharedPreferences("savefile",Context.MODE_PRIVATE);
        String email=shared.getString("email","");
        String password=shared.getString("password","");
        this.email.setText(email.toString().trim());
        this.password.setText(password.toString().trim());

        test();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signin:

                test();
                break;
        }
    }

    private void test() {
        String email=this.email.getText().toString().trim();
        String password=this.password.getText().toString().trim();

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

        this.progressBar.setVisibility(View.VISIBLE);
        this.signin_text.setVisibility(View.GONE);
        this.signin_btn.setBackgroundTintList(getResources().getColorStateList(R.color.Gray));
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                   progressBar.setVisibility(View.GONE);
                   signin_text.setVisibility(View.VISIBLE);
                   signin_btn.setBackgroundTintList(getResources().getColorStateList(R.color.Orange));
                    Toast.makeText(getApplicationContext(), "اهلا بعودتك", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    SharedPreferences shared=getSharedPreferences("savefile", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=shared.edit();
                    editor.putString("email",email);
                    editor.putString("password",password);
                    editor.apply();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Email or password not correct!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                signin_text.setVisibility(View.VISIBLE);
                signin_btn.setBackgroundTintList(getResources().getColorStateList(R.color.Orange));
            }
        });
    }

    public void onBackPressed() {

            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            super.onBackPressed();
            System.exit(1);
            finish();
    }
    public void Sign_up(View view) {
        startActivity(new Intent(this,signup.class));
    }

    public void onforgetbassord(View view) {
        startActivity(new Intent(signin.this,Forget_Password.class));
    }
}