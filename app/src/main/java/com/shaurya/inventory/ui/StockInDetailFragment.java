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

public class StockInDetailFragment extends android.support.v4.app.Fragment {
    private FragmentClickListener mListener;

    private TextView tvFactory, tvRepack, tvRepair;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stock_in_detail,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }

    private void initView(View view) {
        tvFactory = (TextView)view.findViewById(R.id.tvFactory);
        tvRepack = (TextView)view.findViewById(R.id.tvRepackaging);
        tvRepair = (TextView)view.findViewById(R.id.tvRepair);
        tvFactory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockInModeSelected(1);
            }
        });
        tvRepack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockInModeSelected(2);
            }
        });
        tvRepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockInModeSelected(3);
            }
        });
    }

    public static StockInDetailFragment getInstance(){
        return new StockInDetailFragment();
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
        ((HomeActivity)getActivity()).setActionBarTitle("Stock In");
    }

    public void stockInModeSelected(int id){
        mListener.onStockInModeSelected(id);
    }
}