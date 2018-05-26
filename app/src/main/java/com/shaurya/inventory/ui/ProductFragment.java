package com.shaurya.inventory.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import com.shaurya.inventory.interfaces.FragmentClickListener;
import com.shaurya.inventory.model.Product;
import com.shaurya.inventory.model.Variant;
import com.shaurya.inventory.adapters.ProductListAdapter;
import com.shaurya.inventory.R;
import com.shaurya.inventory.db.DBHelper;
import com.shaurya.inventory.util.Screen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaurya on 07/04/18.
 */

public class ProductFragment extends android.support.v4.app.Fragment {

    RecyclerView rvProducts;
    ProductListAdapter mAdapter;
    List<Product> mProductList = new ArrayList<>();
    List<Variant> mVariantList = new ArrayList<>();
    private FragmentClickListener mListener;
    int companyID, productID, variantQuantity;
    ProgressBar progressBar;
    DBHelper mDBHelper;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBHelper = DBHelper.getInstance(getActivity().getApplication());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvProducts = (RecyclerView)view.findViewById(R.id.rvProducts);
        mAdapter = new ProductListAdapter(getContext(),mProductList,mListener);
        rvProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        rvProducts.setAdapter(mAdapter);
        companyID = getArguments().getInt(getString(R.string.key_company_id));
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar_home);
        if (mProductList.size()==0) {
            mProductList.addAll(mDBHelper.getProductsBasedOnCompanyID(companyID));
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
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
        ((HomeActivity)getActivity()).setActionBarTitle("Products");
    }

    public static ProductFragment getInstance(Context c,int companyID){
        ProductFragment productFragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(c.getString(R.string.key_company_id),companyID);
        productFragment.setArguments(args);
        return productFragment;
    }

    public void showDialog(int productID) {
        this.productID = productID;
        List<String> listForListView = new ArrayList<>();
        mVariantList = mDBHelper.getVariantsBasedOnProductID(productID);
        for (int i=0; i<mVariantList.size(); i++)
            listForListView.add(String.valueOf(mVariantList.get(i).getQuantity()).concat(" ").concat(mVariantList.get(i).getUnit()));
        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,listForListView);
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams( Screen.getWidth(getActivity())/2, ViewGroup.LayoutParams.WRAP_CONTENT);
        View popupView = layoutInflater.inflate(R.layout.popup_layout_product, null);
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.addContentView(popupView, params);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        ListView lvVariant = (ListView) dialog.findViewById(R.id.lvProductVariant);
        lvVariant.setAdapter(mArrayAdapter);
        dialog.show();
        lvVariant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                dialog.dismiss();
                mListener.onVariantSelected(mVariantList.get(position));
            }
        });
    }

}
