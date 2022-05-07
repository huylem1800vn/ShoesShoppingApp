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
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.adapter.ShoesAdapter;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.databinding.FragmentHomeBinding;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Shoes;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil.SOService;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    TextView textView;
    ArrayList<Shoes> shoeslist;
    ShoesAdapter shoesAdapter;
    SOService soService;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Anhxa();
        GetAllShoes();

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
        viewFlipper = binding.viewFlipper;
        recyclerView = binding.recycleView;
        textView = binding.textHome;
        shoeslist = new ArrayList<>();
        shoesAdapter = new ShoesAdapter(getActivity().getApplicationContext(), shoeslist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
        recyclerView.setAdapter(shoesAdapter);
        soService = Server.getSOService();
    }

//    private void GetAllShoes() {
//        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Server.connectString,
//                null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                //textView.setText(response.toString());
//                Toast.makeText(getActivity().getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity().getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
//            }
//        });
//        queue.add(jsonObjectRequest);
//    }
    public void GetAllShoes(){
        soService.getShoes().enqueue(new Callback<Shoes>() {
            @Override
            public void onResponse(Call<Shoes> call, Response<Shoes> response) {
                if(response.isSuccessful()){
                    Log.d("MainActivity", "posts loaded from API");
                } else {
                    Log.d("MainActivity", "fail load API");
                }
            }

            @Override
            public void onFailure(Call<Shoes> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });
    }




//                if (response != null) {
//                    int id = 0;
//                    String name = "";
//                    String brand = "";
//                    Double price = 0.0;
//                    String description = "";
//                    int in_stock = 0;
//                    String image = "";
//                    String short_des = "";
//                    for (int i = 0; i < response.length(); i++) {
//                        try {
//                            JSONObject jsonObject = response.getJSONObject(i);
//                            id = jsonObject.getInt("id");
//                            name = jsonObject.getString("name");
//                            brand = jsonObject.getString("brand");
//                            price = jsonObject.getDouble("price");
//                            description = jsonObject.getString("description");
//                            in_stock = jsonObject.getInt("in_stock");
//                            image = jsonObject.getString("image");
//                            short_des = jsonObject.getString("short_des");
//                            shoeslist.add(new Shoes(id, name, brand, price, description, in_stock, image, short_des));
//                            shoesAdapter.notifyDataSetChanged();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}