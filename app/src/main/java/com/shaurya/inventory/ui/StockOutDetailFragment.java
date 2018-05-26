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

public class StockOutDetailFragment extends android.support.v4.app.Fragment {
    private FragmentClickListener mListener;

    private TextView tvDistributor, tvStockist, tvSalesman, tvRetail, tvCash, tvSample;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stock_out_detail,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }

    private void initView(View view) {
        tvDistributor = (TextView)view.findViewById(R.id.tvDistributor);
        tvStockist = (TextView)view.findViewById(R.id.tvStockist);
        tvSalesman = (TextView)view.findViewById(R.id.tvSalesman);
        tvRetail = (TextView)view.findViewById(R.id.tvRetail);
        tvCash = (TextView)view.findViewById(R.id.tvCashSale);
        tvSample = (TextView)view.findViewById(R.id.tvSample);
        tvDistributor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockOutModeSelected(1);
            }
        });
        tvStockist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockOutModeSelected(2);
            }
        });
        tvSalesman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockOutModeSelected(3);
            }
        });
        tvRetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockOutModeSelected(4);
            }
        });
        tvCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockOutModeSelected(5);
            }
        });
        tvSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockOutModeSelected(6);
            }
        });
    }

    public static StockOutDetailFragment getInstance(){
        return new StockOutDetailFragment();
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
        ((HomeActivity)getActivity()).setActionBarTitle("Stock Out");
    }

    public void stockOutModeSelected(int id){
        mListener.onStockOutModeSelected(id);
    }
}