package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.home;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.R;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.adapter.PhotoAdapter;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.databinding.FragmentProductDetailBinding;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Photo;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Product;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil.SOService;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil.Server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailFragment extends Fragment {
    private SOService soService;
    private AutoCompleteTextView autoCompleteTextView;
    private ViewPager viewPager;
    private PhotoAdapter photoAdapter;
    private FragmentProductDetailBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        soService = Server.getSOService();
        Bundle bundle = this.getArguments();

        if(bundle != null){
            int productID = Integer.parseInt(bundle.getString("productID"));
            Toast.makeText(getContext(), ""+productID, Toast.LENGTH_SHORT).show();
            getProductDetail(productID);
        }
        // load size
        autoCompleteTextView = binding.sizeProduct;
        String[] size = new String[] {"39", "40", "41", "42"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                root.getContext(), R.layout.drop_down_item, size);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setText(autoCompleteTextView.getAdapter().getItem(0).toString(), false);

        // sự kiện click chọn item của dropdown size
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), ""+size[position], Toast.LENGTH_SHORT).show();
            }
        });

        // sự kiện click nút thêm vào giỏ
        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return root;
    }

    private void getProductDetail(int productID) {
        soService.getProductByID(productID).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Product product = response.body();
                    binding.nameProductDetail.setText(product.getName());
                    binding.descriptionProduct.setText(product.getDescription());
                    binding.priceProduct.setText(String.format("%,.0f₫", product.getPrice()));
                    binding.priceProduct.setPaintFlags(binding.priceProduct.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    binding.promotionalPriceProduct.setText(String.format("%,.0f₫", product.getPromotionalPrice()));
                    binding.perRedProduct.setText(String.format("-%d%%", product.getPerRed()));
                    binding.nameBrandProductDetail.setText(product.getBrand());
                    List<Photo> listPhoto = new ArrayList<>();
                    listPhoto.add(new Photo(product.getImage1()));
                    listPhoto.add(new Photo(product.getImage2()));
                    listPhoto.add(new Photo(product.getImage3()));
                    photoAdapter = new PhotoAdapter(getContext(), listPhoto);
                    viewPager = binding.viewpager;
                    viewPager.setAdapter(photoAdapter);
                } else {
                    Log.d("MainActivity", "fail load API Get Product Detail");
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.d("MainActivity", "error loading from API Get Product Detail");
            }

        });
    }
}
