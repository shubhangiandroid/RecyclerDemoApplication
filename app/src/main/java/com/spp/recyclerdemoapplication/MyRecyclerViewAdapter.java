package com.spp.recyclerdemoapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Product> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    Context mContext;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<Product> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        mContext=context;
    }
    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }
    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtProductName.setText(mData.get(position).getProductName());
        holder.txtPrice.setText(String.valueOf(mData.get(position).getPrice()));
        holder.txtQty.setText(String.valueOf(mData.get(position).getQuantity()));
        holder.txtUnit.setText(mData.get(position).getUnit());

        Picasso.with(mContext).load(mData.get(position).getImageUrl()).fit().into(holder.imgProductImage);
    }
    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }
    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtProductName,txtPrice,txtQty,txtUnit;

        ImageView imgProductImage;

        ViewHolder(View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.tvName);
            txtPrice = itemView.findViewById(R.id.tvPrice);
            txtQty = itemView.findViewById(R.id.tvQty);
            txtUnit = itemView.findViewById(R.id.tvUnit);
            imgProductImage=itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id).getProductName();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

