package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.adapter.ProductAdapter;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.adapter.ShoesAdapter;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.databinding.FragmentHomeBinding;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Product;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Shoes;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil.SOService;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements ProductAdapter.OnProductListener{
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    TextView textView;
    List<Product> productList;
    SOService soService;

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
                for (int i = 0; i < photos.size(); i++){
                    ImageView imageView = new ImageView(getActivity().getApplicationContext());
                    Picasso.with(getActivity().getApplicationContext()).load(photos.get(i)).into(imageView);
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


            return root;
    }

    private void Anhxa() {

    }

    public void GetAllProduct(){
        soService.getProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productList = response.body();
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    recyclerView.setAdapter(new ProductAdapter(getActivity(), HomeFragment.this, (ArrayList<Product>) productList));
                } else {
                    Log.d("MainActivity", "fail load API");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
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
        Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
    }
}