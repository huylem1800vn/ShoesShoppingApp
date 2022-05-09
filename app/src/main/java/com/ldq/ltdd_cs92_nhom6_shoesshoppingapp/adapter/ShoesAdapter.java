package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.R;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Shoes;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShoesAdapter extends RecyclerView.Adapter<ShoesAdapter.ItemHolder> {

    Context context;
    ArrayList<Shoes> arrayShoes;
//    PostItemListener postItemListener;

    public ShoesAdapter(Context applicationContext, ArrayList<Shoes> shoeslist) {
        this.context = applicationContext;
        this.arrayShoes = shoeslist;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shoe, null);
        ItemHolder itemHolder = new ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Shoes shoes = arrayShoes.get(position);
        holder.name.setText(shoes.getName());
        holder.price.setText((int) shoes.price);
        Picasso.with(context).load(shoes.getImage())
                .placeholder(R.drawable.ic_logo)
                .error(R.drawable.ic_logo)
                .into(holder.image);

    }
    @Override
    public int getItemCount() {
        return arrayShoes.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name, price;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageShoes);
            name = itemView.findViewById(R.id.nameShoes);
            price = itemView.findViewById(R.id.priceShoes);
        }
    }
//    public interface PostItemListener {
//        void onPostClick(long id);
//    }

}
