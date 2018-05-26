package com.shaurya.inventory.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shaurya.inventory.interfaces.FragmentClickListener;
import com.shaurya.inventory.R;

/**
 * Created by shaurya on 07/04/18.
 */

public class CompanyFragment extends Fragment {
    private FragmentClickListener mListener;

    private TextView tvCompany1, tvCompany2, tvCompany3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("tatti","C");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("tatti","CV");
        return inflater.inflate(R.layout.fragment_company,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("tatti","VC");
        initView(view);
    }

    private void initView(View view) {
        tvCompany1 = (TextView)view.findViewById(R.id.tvCompany1);
        tvCompany2 = (TextView)view.findViewById(R.id.tvCompany2);
        tvCompany3 = (TextView)view.findViewById(R.id.tvCompany3);
        tvCompany1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companySelected(1);
            }
        });
        tvCompany2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companySelected(2);
            }
        });
        tvCompany3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companySelected(3);
            }
        });
    }

    public static CompanyFragment getInstance(){
        return new CompanyFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("tatti","Attach");
        if (getActivity() instanceof FragmentClickListener)
            mListener = (FragmentClickListener) getActivity();
        else
            throw new ClassCastException(getActivity().toString() + " must implement FragmentClickListener");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HomeActivity)getActivity()).setActionBarTitle("Companies");
        Log.e("tatti","AC");
    }

    public void companySelected(int id){
        switch (id){
            case 1:
                mListener.onCompanySelected(1);
                break;
            case 2:
                mListener.onCompanySelected(2);
                break;
            case 3:
                mListener.onCompanySelected(3);
                break;
        }
    }
}

