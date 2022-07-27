package com.halawy.elmenu.Click_Category;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseError;

public interface Click_category {

     void Click_cateogy(String Cid);

    void onCancelled(@NonNull DatabaseError error);
}
