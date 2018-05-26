package com.shaurya.inventory.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shaurya.inventory.R;
import com.shaurya.inventory.interfaces.FragmentClickListener;
import com.shaurya.inventory.model.Product;

import java.util.List;

/**
 * Created by shaurya on 07/04/18.
 */

public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Product> mList;
    private FragmentClickListener mListener;
    public ProductListAdapter(Context mContext, List<Product> mList, FragmentClickListener mListener) {
        this.mContext = mContext;
        this.mList = mList;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cell_product_recycler_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Product product = mList.get(position);
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.tvProductName.setText(product.getProductName());
        viewHolder.tvCompanyName.setText(product.getCompanyName());
        viewHolder.ivProductImage.setImageBitmap(stringToBitmap(product.getProductImage()));
        viewHolder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onProductSelected(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private Bitmap stringToBitmap(String encodedImage){
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout llRow;
        TextView tvProductName, tvCompanyName;
        ImageView ivProductImage;
        public ViewHolder(View v){
            super(v);
            llRow = (LinearLayout)v.findViewById(R.id.ll_product_row);
            tvProductName = (TextView) v.findViewById(R.id.tvProductName);
            tvCompanyName = (TextView)v.findViewById(R.id.tvProductCompanyName);
            ivProductImage = (ImageView)v.findViewById(R.id.iv_product);
        }
    }
}
