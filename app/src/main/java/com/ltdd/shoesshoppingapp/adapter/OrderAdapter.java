package com.ltdd.shoesshoppingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ltdd.shoesshoppingapp.R;
import com.ltdd.shoesshoppingapp.model.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ItemHolder> {

    private final ArrayList<Order> arrayOrder;
    private final OnOrderListener onOrderListener;
    private final Context context;

    public OrderAdapter(Context context, OnOrderListener onOrderListener, ArrayList<Order> arrayOrder) {
        this.arrayOrder = arrayOrder;
        this.onOrderListener = onOrderListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        ItemHolder itemHolder = new ItemHolder(v, onOrderListener);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Order order = arrayOrder.get(position);
        holder.orderID.setText(order.getId());
        holder.name.setText(order.getCustomerName());
        holder.address.setText(order.getCustomerAddress());
        holder.phone.setText(order.getCustomerPhone());
        holder.totalAmount.setText(String.format("%,.0f₫", order.getTotalAmount()));
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String orderDate = dt.format(order.getOrderDate());
        holder.orderDate.setText(orderDate);

    }
    @Override
    public int getItemCount() {
        return arrayOrder.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView orderID, name, address, phone, totalAmount, orderDate;
        OnOrderListener onOrderListener;

        public ItemHolder(@NonNull View itemView, OnOrderListener onOrderListener) {
            super(itemView);
            name = itemView.findViewById(R.id.customer_name);
            orderID = itemView.findViewById(R.id.order_id);
            address = itemView.findViewById(R.id.customer_address);
            phone = itemView.findViewById(R.id.customer_phone);
            totalAmount = itemView.findViewById(R.id.total_amount);
            orderDate = itemView.findViewById(R.id.order_date);
            this.onOrderListener = onOrderListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onOrderListener.onOrderClick(getAdapterPosition());
        }
    }
    public interface OnOrderListener{
        void onOrderClick(int position);
    }

}
