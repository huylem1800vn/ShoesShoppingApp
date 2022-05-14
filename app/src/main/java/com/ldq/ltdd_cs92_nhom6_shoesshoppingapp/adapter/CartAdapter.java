package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.MainActivity;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.R;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Brand;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Cart;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.home.CartFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ItemHolder> {

    private final ArrayList<Cart> arrayCart;
    private final OnCartListener onCartListener;
    private final Context context;

    public CartAdapter(Context context, ArrayList<Cart> arrayCart, OnCartListener onCartListener) {
        this.arrayCart = arrayCart;
        this.context = context;
        this.onCartListener = onCartListener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        ItemHolder itemHolder = new ItemHolder(v, onCartListener);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, @SuppressLint("RecyclerView") int position) {
        Cart cart = arrayCart.get(position);
        holder.name.setText(cart.getName());
        holder.price.setText(String.format("%,.0f₫", cart.getPrice()));
        holder.quantity.setText(String.valueOf(cart.getQuantity()));
        holder.size.setText(String.valueOf(cart.getSize()));
        Glide.with(context).load(cart.getImage())
                .centerCrop()
                .error(R.drawable.ic_logo)
                .into(holder.image);

        int quantity = cart.getQuantity();
        if(quantity >= 10){
            holder.btnAdd.setEnabled(false);
            holder.btnSub.setVisibility(View.VISIBLE);
        } else if(quantity <= 1){
            holder.btnSub.setEnabled(false);
            holder.btnAdd.setEnabled(true);
        } else if(quantity >= 1){
            holder.btnAdd.setEnabled(true);
            holder.btnSub.setEnabled(true);
            holder.btnSub.setVisibility(View.VISIBLE);
            holder.btnAdd.setVisibility(View.VISIBLE);
        }
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = cart.getQuantity() + 1;
                holder.quantity.setText(String.valueOf(newQuantity));
                MainActivity.carts.get(position).setQuantity(newQuantity);
                MainActivity.carts.get(position).setTotalAmount(newQuantity*cart.getPrice());
                CartFragment.EvenUltil();
                if(newQuantity > 9){
                    holder.btnAdd.setEnabled(false);
                    String text = context.getResources().getString(R.string.maximum_limit_dialog);
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                } else {
                    holder.btnAdd.setEnabled(true);
                    holder.btnAdd.setVisibility(View.VISIBLE);
                }
                holder.btnSub.setEnabled(true);
                holder.btnSub.setVisibility(View.VISIBLE);
                holder.quantity.setText(String.valueOf(newQuantity));
            }
        });

        holder.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = cart.getQuantity() - 1;
                holder.quantity.setText(String.valueOf(newQuantity));
                MainActivity.carts.get(position).setQuantity(newQuantity);
                MainActivity.carts.get(position).setTotalAmount(newQuantity*cart.getPrice());
                CartFragment.EvenUltil();
                if(newQuantity < 2){
                    holder.btnSub.setEnabled(false);
                    String text = context.getResources().getString(R.string.minimum_limit_dialog);
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                } else {
                    holder.btnSub.setEnabled(true);
                    holder.btnSub.setVisibility(View.VISIBLE);
                }
                holder.btnAdd.setEnabled(true);
                holder.btnAdd.setVisibility(View.VISIBLE);
                holder.quantity.setText(String.valueOf(newQuantity));
            }
        });

    }
    @Override
    public int getItemCount() {
        return arrayCart.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        ImageView image;
        TextView name, price, quantity, size, btnAdd, btnSub;
        OnCartListener onCartListener;

        public ItemHolder(@NonNull View itemView, OnCartListener onCartListener) {
            super(itemView);
            image = itemView.findViewById(R.id.image_item_cart);
            name = itemView.findViewById(R.id.item_cart_name);
            price = itemView.findViewById(R.id.item_cart_price);
            size = itemView.findViewById(R.id.item_cart_size);
            quantity = itemView.findViewById(R.id.item_cart_quantity);
            btnAdd = itemView.findViewById(R.id.btn_add_quantity);
            btnSub = itemView.findViewById(R.id.btn_reduce_quantity);
            this.onCartListener = onCartListener;
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            onCartListener.onCartLongClick(getAdapterPosition());
            return true;
        }
    }

    public interface OnCartListener{
        void onCartLongClick(int position);
    }
}
