package com.halawy.elmenu;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Dialog  {


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void Get_Dialog(View view) {
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
