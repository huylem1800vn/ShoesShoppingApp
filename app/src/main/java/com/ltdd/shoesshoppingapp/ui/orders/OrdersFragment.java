package com.ltdd.shoesshoppingapp.ui.orders;

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
import com.ltdd.shoesshoppingapp.adapter.OrderAdapter;
import com.ltdd.shoesshoppingapp.databinding.FragmentBrandsBinding;
import com.ltdd.shoesshoppingapp.databinding.FragmentOrdersBinding;
import com.ltdd.shoesshoppingapp.model.Order;
import com.ltdd.shoesshoppingapp.ui.home.CartFragment;
import com.ltdd.shoesshoppingapp.ultil.SOService;
import com.ltdd.shoesshoppingapp.ultil.Server;

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