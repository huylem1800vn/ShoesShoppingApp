package com.ltdd.shoesshoppingapp.ui.home;

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

import com.ltdd.shoesshoppingapp.MainActivity;
import com.ltdd.shoesshoppingapp.R;
import com.ltdd.shoesshoppingapp.adapter.PhotoAdapter;
import com.ltdd.shoesshoppingapp.databinding.FragmentProductDetailBinding;
import com.ltdd.shoesshoppingapp.model.Cart;
import com.ltdd.shoesshoppingapp.model.Photo;
import com.ltdd.shoesshoppingapp.model.Product;
import com.ltdd.shoesshoppingapp.ultil.SOService;
import com.ltdd.shoesshoppingapp.ultil.Server;

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
    private Product product;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        soService = Server.getSOService();
        Bundle bundle = this.getArguments();

        if(bundle != null){
            int productID = Integer.parseInt(bundle.getString("productID"));
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
            }
        });

        // sự kiện click nút thêm vào giỏ
        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = Integer.parseInt(binding.sizeProduct.getText().toString());
                double price = product.getPromotionalPrice();
                int id = product.getId();
                if(MainActivity.carts.size() > 0){
                    boolean exists = false;
                    for(int i = 0; i < MainActivity.carts.size(); i++){
                        Cart cart = MainActivity.carts.get(i);
                        if(cart.getProductID() == id && cart.getSize() == size){
                            // nếu sản phẩm này đã có trong giỏ hàng rồi
                            // set lại số lượng tăng 1 và tính lại tổng tiền totalAmount
                            cart.setQuantity(cart.getQuantity()+1);
                            if(cart.getQuantity() >= 10){
                                cart.setQuantity(10);
                                String text = getResources().getString(R.string.maximum_limit_dialog);
                                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                            }
                            cart.setTotalAmount(price * cart.getQuantity());
                            exists = true;
                        }
                    }
                    if(exists == false){
                        // nếu sản phẩm này chưa được thêm vào giỏ hàng
                        double totalAmount = price;
                        MainActivity.carts.add(new Cart(id, product.getName(),
                                product.getImage1(), size, 1, price, totalAmount));
                    }
                } else {
                    // thêm 1 sản phẩm vào giỏ hàng khi giỏ hàng chưa có sản phẩm nào
                    double totalAmount = price;
                    MainActivity.carts.add(new Cart(id, product.getName(),
                            product.getImage1(), size, 1, price, totalAmount));
                }
                // hiện số lượng sản phẩm trên giỏ hàng
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.showNumberProductInCart();
            }
        });
        return root;
    }

    private void getProductDetail(int productID) {
        soService.getProductByID(productID).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    product = response.body();
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
