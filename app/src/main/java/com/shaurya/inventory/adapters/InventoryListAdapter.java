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
import com.shaurya.inventory.model.Inventory;
import com.shaurya.inventory.util.DateFormatterUtil;

import java.util.List;

/**
 * Created by shaurya on 07/04/18.
 */

public class InventoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Inventory> mList;
    public InventoryListAdapter(Context mContext, List<Inventory> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cell_inventory_recycler_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Inventory inventory = mList.get(position);
        ViewHolder viewHolder = (ViewHolder)holder;
        String dateDisplay = DateFormatterUtil.dateConverter(inventory.getDate()).concat("\n").concat(DateFormatterUtil.timeConverter(inventory.getDate()));
        viewHolder.tvDate.setText(dateDisplay);
        viewHolder.tvCompanyName.setText(inventory.getCompanyName());
        viewHolder.tvProductName.setText(inventory.getProduct());
        viewHolder.tvVariantName.setText(inventory.getVariant());
        viewHolder.tvQuantity.setText(String.valueOf(inventory.getQuantity()));
        viewHolder.tvMode.setText(inventory.getStockMode());
        viewHolder.tvSource.setText(inventory.getSource());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<Inventory> mInventoryList) {
        mList = mInventoryList;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvDate, tvCompanyName, tvProductName, tvVariantName, tvQuantity, tvMode, tvSource;
        public ViewHolder(View v){
            super(v);
            tvDate = (TextView)v.findViewById(R.id.tvDateInventoryCell);
            tvProductName = (TextView) v.findViewById(R.id.tvProductInventoryCell);
            tvCompanyName = (TextView)v.findViewById(R.id.tvCompanyInventoryCell);
            tvVariantName = (TextView)v.findViewById(R.id.tvVariantInventoryCell);
            tvQuantity = (TextView)v.findViewById(R.id.tvQuantityInventoryCell);
            tvMode = (TextView)v.findViewById(R.id.tvStockModeInventoryCell);
            tvSource = (TextView)v.findViewById(R.id.tvStockSourceInventoryCell);
        }
    }
}
