package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.brands;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.R;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.databinding.FragmentBrandDetailBinding;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Brand;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil.SOService;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil.Server;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandDetailFragment extends Fragment {
   private SOService soService;
   private FragmentBrandDetailBinding binding;

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      binding = FragmentBrandDetailBinding.inflate(inflater, container, false);

      View root = binding.getRoot();
      soService = Server.getSOService();
      Bundle bundle = this.getArguments();

      if(bundle != null){
         int brandID = Integer.parseInt(bundle.getString("brandID"));
         getBrandDetail(brandID);
      }
      return root;
   }


   private void getBrandDetail(int brandID) {
      soService.getBrandByID(brandID).enqueue(new Callback<Brand>() {
         @Override
         public void onResponse(Call<Brand> call, Response<Brand> response) {
            if (response.isSuccessful()) {
               Brand brand = response.body();
               binding.nameBrandDetail.setText(brand.name);
               binding.descriptionBrand.setText(brand.description);
               Picasso.with(getContext()).load(brand.getImage())
                       .placeholder(R.drawable.ic_logo)
                       .resize(200,200)
                       .error(R.drawable.ic_logo)
                       .into(binding.imageBrandDetail);
            } else {
               Log.d("MainActivity", "fail load API Get Brand List");
            }
         }

         @Override
         public void onFailure(Call<Brand> call, Throwable t) {
            Log.d("MainActivity", "error loading from API Get Brand List");
         }

      });
   }


}
