package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.R;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Cart;

import java.util.ArrayList;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ItemHolder> {

    private final ArrayList<Cart> arrayOrderDetail;
    private final Context context;

    public OrderDetailAdapter(Context context, ArrayList<Cart> arrayOrderDetail) {
        this.arrayOrderDetail = arrayOrderDetail;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail, parent, false);
        ItemHolder itemHolder = new ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, @SuppressLint("RecyclerView") int position) {
        Cart cart = arrayOrderDetail.get(position);
        holder.name.setText(cart.getName());
        holder.price.setText(String.format("%,.0f₫", cart.getPrice()));
        holder.quantity.setText(String.valueOf(cart.getQuantity()));
        holder.size.setText(String.valueOf(cart.getSize()));
        Glide.with(context).load(cart.getImage())
                .centerCrop()
                .error(R.drawable.ic_logo)
                .into(holder.image);
    }
    @Override
    public int getItemCount() {
        return arrayOrderDetail.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price, quantity, size, btnAdd, btnSub;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.order_detail_image);
            name = itemView.findViewById(R.id.order_detail_name);
            price = itemView.findViewById(R.id.order_detal_price);
            size = itemView.findViewById(R.id.order_detail_size);
            quantity = itemView.findViewById(R.id.order_detail_quantity);
        }

    }
}
