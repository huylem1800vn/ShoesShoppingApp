package com.ltdd.shoesshoppingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ltdd.shoesshoppingapp.R;
import com.ltdd.shoesshoppingapp.model.Brand;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ItemHolder> {

    private final ArrayList<Brand> arrayBrand;
    private final OnBrandListener onBrandListener;
    private final Context context;

    public BrandAdapter(Context context, OnBrandListener onBrandListener, ArrayList<Brand> arrayBrand) {
        this.arrayBrand = arrayBrand;
        this.onBrandListener = onBrandListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brand, parent, false);
        ItemHolder itemHolder = new ItemHolder(v, onBrandListener);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Brand brand = arrayBrand.get(position);
        holder.name.setText(brand.getName());
        Picasso.with(context).load(brand.getImage())
                .placeholder(R.drawable.ic_logo)
                .resize(200,200)
                .error(R.drawable.ic_logo)
                .into(holder.image);
    }
    @Override
    public int getItemCount() {
        return arrayBrand.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        TextView name;
        OnBrandListener onBrandListener;

        public ItemHolder(@NonNull View itemView, OnBrandListener onBrandListener) {
            super(itemView);
            image = itemView.findViewById(R.id.image_brand);
            name = itemView.findViewById(R.id.name_brand);
            this.onBrandListener = onBrandListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onBrandListener.onBrandClick(getAdapterPosition());
        }
    }
    public interface OnBrandListener{
        void onBrandClick(int position);
    }

}
