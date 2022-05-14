package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.orders;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.MainActivity;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.R;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.adapter.OrderAdapter;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.adapter.ProductAdapter;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.databinding.FragmentBrandsBinding;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.databinding.FragmentOrdersBinding;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Order;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Product;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.home.CartFragment;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.home.HomeFragment;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.home.ProductDetailFragment;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil.SOService;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil.Server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersFragment extends Fragment implements OrderAdapter.OnOrderListener {

    private FragmentOrdersBinding binding;
    RecyclerView recyclerView;
    List<Order> orderList;
    SOService soService;
    OrderAdapter orderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = binding.recycleViewOrder;
        orderList = new ArrayList<>();
        soService = Server.getSOService();

        GetAllOrder();
        return root;
    }

    private void GetAllOrder() {
        soService.getOrders().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    orderList = response.body();
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                    orderAdapter = new OrderAdapter(getActivity(), OrdersFragment.this, (ArrayList<Order>) orderList);
                    recyclerView.setAdapter(orderAdapter);
                } else {
                    Log.d("MainActivity", "fail load API Get ORDER List");
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("MainActivity", "error loading from API Get ORDER List");
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onOrderClick(int position) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setBackButton();
        Bundle bundle = new Bundle();
        bundle.putString("orderID", String.valueOf(orderList.get(position).getId()));
        CartFragment cartFragment = new CartFragment();
        cartFragment.setArguments(bundle);
        String backStateName = cartFragment.getClass().getName();
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, cartFragment)
                .addToBackStack(backStateName)
                .commit();
    }
}