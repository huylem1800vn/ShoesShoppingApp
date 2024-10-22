package com.ltdd.shoesshoppingapp.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ltdd.shoesshoppingapp.R;
import com.ltdd.shoesshoppingapp.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> implements Filterable {

   private final ArrayList<Product> arrayProduct;
   private final OnProductListener onProductListener;
   private final ArrayList<Product> backUpArrayProduct;
   private final Context context;

   public ProductAdapter(Context context, OnProductListener onProductListener, ArrayList<Product> productList) {
      this.arrayProduct = productList;
      this.onProductListener = onProductListener;
      this.context = context;
      this.backUpArrayProduct = new ArrayList<>(productList);
   }

   @NonNull
   @Override
   public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shoe, parent, false);
      ItemHolder itemHolder = new ItemHolder(v, onProductListener);

      return itemHolder;
   }

   @Override
   public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
      Product product = arrayProduct.get(position);
      holder.name.setText(product.getName());
      holder.price.setText(String.format("%,.0f₫", product.getPrice()));
      holder.price.setPaintFlags(holder.price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
      holder.promotionalPrice.setText(String.format("%,.0f₫", product.getPromotionalPrice()));
      holder.perRed.setText(String.format("-%d%%", product.getPerRed()));
      Picasso.with(context).load(product.getImage1())
              .placeholder(R.drawable.ic_logo)
              .resize(200,200)
              .error(R.drawable.ic_logo)
              .into(holder.image);
   }
   @Override
   public int getItemCount() {
      return arrayProduct.size();
   }

   @Override
   public Filter getFilter() {
      return filter;
   }

   private Filter filter = new Filter() {
      @Override
      protected FilterResults performFiltering(CharSequence constraint) {
         ArrayList<Product> filterList = new ArrayList<>();
         if(constraint.length() == 0){
            filterList.addAll(backUpArrayProduct);
         } else {
            String filterPattern = constraint.toString().toLowerCase().trim();
            for (Product p : backUpArrayProduct){
               if(p.getName().toLowerCase().trim().contains(filterPattern)){
                  filterList.add(p);
               }
            }
         }
         FilterResults results = new FilterResults();
         results.values = filterList;
         return results;
      }

      @Override
      protected void publishResults(CharSequence constraint, FilterResults results) {
         arrayProduct.clear();
         arrayProduct.addAll((ArrayList<Product>) results.values);
         notifyDataSetChanged();
      }
   };

   public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
      ImageView image;
      TextView name, price, promotionalPrice, perRed;
      OnProductListener onProductListener;

      public ItemHolder(@NonNull View itemView, OnProductListener onProductListener) {
         super(itemView);
         image = itemView.findViewById(R.id.imageShoes);
         name = itemView.findViewById(R.id.nameShoes);
         price = itemView.findViewById(R.id.priceShoes);
         promotionalPrice = itemView.findViewById(R.id.promotionalPriceShoes);
         perRed = itemView.findViewById(R.id.perRed);
         this.onProductListener = onProductListener;
         itemView.setOnClickListener(this);
      }

      @Override
      public void onClick(View v) {
         onProductListener.onProductClick(getAdapterPosition());
      }
   }
   public interface OnProductListener{
      void onProductClick(int position);
   }

}
