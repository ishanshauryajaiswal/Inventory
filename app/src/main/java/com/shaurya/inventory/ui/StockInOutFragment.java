package com.shaurya.inventory.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shaurya.inventory.interfaces.FragmentClickListener;
import com.shaurya.inventory.R;

/**
 * Created by shaurya on 08/04/18.
 */

public class StockInOutFragment extends android.support.v4.app.Fragment {
    private FragmentClickListener mListener;

    private TextView tvStockIn, tvStockOut, tvPackaging;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stock_in_out,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        tvStockIn = (TextView)view.findViewById(R.id.tvStockIn);
        tvStockOut = (TextView)view.findViewById(R.id.tvStockOut);
        tvPackaging = (TextView)view.findViewById(R.id.tvPackaging);
        tvStockIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeSelected(1);
            }
        });
        tvStockOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeSelected(2);
            }
        });
        tvPackaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeSelected(3);
            }
        });
    }

    public static StockInOutFragment getInstance(){
        return new StockInOutFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof FragmentClickListener)
            mListener = (FragmentClickListener) getActivity();
        else
            throw new ClassCastException(getActivity().toString() + " must implement FragmentClickListener");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HomeActivity)getActivity()).setActionBarTitle("Select Stock Mode");
    }

    public void modeSelected(int id){
        mListener.onStockModeSelected(id);
    }
}