package com.halawy.elmenu.Fragment_Requirment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.halawy.elmenu.R;

public class fragment_Requirment extends Fragment {
    RecyclerView requirment_recycler;
    Get_Requirment get_requirment=new Get_Requirment();
    ProgressBar progressBar;
    LinearLayout linear_empty;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment__card, container, false);
        requirment_recycler =view.findViewById(R.id.requirment_recycler);
        progressBar=view.findViewById(R.id.progress_fragment_card);
        linear_empty=view.findViewById(R.id.linear_empty);
        progressBar.setVisibility(View.VISIBLE);
        linear_empty.setVisibility(View.GONE);
        requirment_recycler= get_requirment.get_shop_and_status(getContext(),requirment_recycler, progressBar,linear_empty);

        return view;
    }
}