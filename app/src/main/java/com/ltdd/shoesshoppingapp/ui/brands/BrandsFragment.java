package com.ltdd.shoesshoppingapp.ui.brands;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ltdd.shoesshoppingapp.MainActivity;
import com.ltdd.shoesshoppingapp.R;
import com.ltdd.shoesshoppingapp.adapter.BrandAdapter;
import com.ltdd.shoesshoppingapp.databinding.FragmentBrandsBinding;
import com.ltdd.shoesshoppingapp.model.Brand;
import com.ltdd.shoesshoppingapp.ultil.SOService;
import com.ltdd.shoesshoppingapp.ultil.Server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandsFragment extends Fragment implements BrandAdapter.OnBrandListener {
    private RecyclerView recyclerView;
    private List<Brand> brandList;
    private SOService soService;
    private FragmentBrandsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBrandsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        brandList = new ArrayList<>();
        soService = Server.getSOService();
        recyclerView = binding.recycleViewBrands;

        GetAllBrand();
        return root;
    }

    private void GetAllBrand() {
        soService.getBrands().enqueue(new Callback<List<Brand>>() {
            @Override
            public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                if (response.isSuccessful()) {
                    brandList = response.body();
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    recyclerView.setAdapter(new BrandAdapter(getActivity(), BrandsFragment.this, (ArrayList<Brand>) brandList));
                } else {
                    Log.d("MainActivity", "fail load API Get Brand List");
                }
            }

            @Override
            public void onFailure(Call<List<Brand>> call, Throwable t) {
                Log.d("MainActivity", "error loading from API Get Brand List");
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onBrandClick(int position) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setBackButton();
        Bundle bundle = new Bundle();
        bundle.putString("brandID", String.valueOf(brandList.get(position).getId())); // Put anything what you want
        BrandDetailFragment brandDetailFragment = new BrandDetailFragment();
        brandDetailFragment.setArguments(bundle);
        String backStateName = brandDetailFragment.getClass().getName();
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, brandDetailFragment)
                .addToBackStack(backStateName)
                .commit();
    }
}