package com.ltdd.shoesshoppingapp.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ltdd.shoesshoppingapp.MainActivity;
import com.ltdd.shoesshoppingapp.R;
import com.ltdd.shoesshoppingapp.adapter.ProductAdapter;
import com.ltdd.shoesshoppingapp.databinding.FragmentHomeBinding;
import com.ltdd.shoesshoppingapp.model.Product;
import com.ltdd.shoesshoppingapp.ultil.SOService;
import com.ltdd.shoesshoppingapp.ultil.Server;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements ProductAdapter.OnProductListener {
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    TextView textView;
    List<Product> productList;
    SOService soService;
    ProductAdapter productAdapter;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        viewFlipper = binding.viewFlipper;
        recyclerView = binding.recycleView;
        textView = binding.textHome;
        productList = new ArrayList<>();
        soService = Server.getSOService();
        View root = binding.getRoot();

//            Product product1 = new Product(1, "Nike Air Force 1 '07", "adidas", 2000000);
//            productList.add(product1);
        GetAllProduct();

        //Action Flipper
        homeViewModel.getPhotolists().observe(getViewLifecycleOwner(), photos -> {
            for (int i = 0; i < photos.size(); i++) {
                ImageView imageView = new ImageView(getActivity().getApplicationContext());
                Picasso.with(getContext()).load(photos.get(i)).into(imageView);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                viewFlipper.addView(imageView);
            }
            Animation in = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), android.R.anim.slide_in_left);
            Animation out = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), android.R.anim.slide_out_right);
            viewFlipper.setFlipInterval(3500);
            viewFlipper.setAutoStart(true);
            viewFlipper.setInAnimation(in);
            viewFlipper.setOutAnimation(out);
        });
        // search
        EditText editText = binding.titleSearch;
        editText.setSingleLine();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                productAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return root;
    }

    private void GetAllProduct() {
        soService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    productAdapter = new ProductAdapter(getActivity(), HomeFragment.this, (ArrayList<Product>) productList);
                    recyclerView.setAdapter(productAdapter);
                } else {
                    Log.d("MainActivity", "fail load API Get Product List");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("MainActivity", "error loading from API Get Product List");
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onProductClick(int position) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setBackButton();
        Bundle bundle = new Bundle();
        bundle.putString("productID", String.valueOf(productList.get(position).getId()));
        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        productDetailFragment.setArguments(bundle);
        String backStateName = productDetailFragment.getClass().getName();
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, productDetailFragment)
                .addToBackStack(backStateName)
                .commit();
    }
}