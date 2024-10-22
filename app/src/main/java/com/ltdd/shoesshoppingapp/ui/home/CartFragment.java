package com.ltdd.shoesshoppingapp.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ltdd.shoesshoppingapp.MainActivity;
import com.ltdd.shoesshoppingapp.R;
import com.ltdd.shoesshoppingapp.adapter.CartAdapter;
import com.ltdd.shoesshoppingapp.adapter.OrderDetailAdapter;
import com.ltdd.shoesshoppingapp.databinding.FragmentCartBinding;
import com.ltdd.shoesshoppingapp.model.Cart;
import com.ltdd.shoesshoppingapp.ultil.SOService;
import com.ltdd.shoesshoppingapp.ultil.Server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment implements CartAdapter.OnCartListener {

    private static FragmentCartBinding binding;
    private CartAdapter cartAdapter;
    private OrderDetailAdapter orderDetailAdapter;
    private RecyclerView recyclerViewCart;
    List<Cart> orderDetails;
    SOService soService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle bundle = this.getArguments();
        recyclerViewCart = binding.recycleViewCart;
        recyclerViewCart.setHasFixedSize(true);
        recyclerViewCart.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        if (bundle != null) {
            String orderID = bundle.getString("orderID");
            soService = Server.getSOService();
            getOrderDetail(orderID);
            binding.btnOrder.setVisibility(View.GONE);
        } else {
            cartAdapter = new CartAdapter(getContext(), MainActivity.carts, CartFragment.this);
            recyclerViewCart.setAdapter(cartAdapter);
            CheckData();
            EvenUltil();
        }
        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.carts.size() > 0) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.setBackButton();
                    CustomerInformationFragment customerInformationFragment = new CustomerInformationFragment();
                    String backStateName = customerInformationFragment.getClass().getName();
                    getParentFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment_content_main, customerInformationFragment)
                            .addToBackStack(backStateName)
                            .commit();
                } else {
                    String text = getResources().getString(R.string.your_cart_has_no_products);
                    Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    private void getOrderDetail(String orderID) {
        soService.getOrderDetailByOrderID(orderID).enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    orderDetails = new ArrayList<>(response.body());
                    orderDetailAdapter = new OrderDetailAdapter(getActivity(), (ArrayList<Cart>) orderDetails);
                    recyclerViewCart.setAdapter(orderDetailAdapter);
                    double totalAmount = 0;
                    for (int i = 0; i < orderDetails.size(); i++) {
                        Cart cart = orderDetails.get(i);
                        totalAmount += cart.getPrice() * cart.getQuantity();
                    }
                    binding.totalAmountCart.setText(String.format("%,.0f₫", totalAmount));
                } else {
                    Log.d("MainActivity", "fail load API Get ORDER DETAIL List");
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                Log.d("MainActivity", "error loading from API Get DETAIL List");
            }

        });
    }

    private void CheckData() {
        if (MainActivity.carts.size() <= 0) {
            cartAdapter.notifyDataSetChanged();
            binding.recycleViewCart.setVisibility(View.GONE);
            binding.nullCart.setVisibility(View.VISIBLE);
        } else {
            cartAdapter.notifyDataSetChanged();
            binding.nullCart.setVisibility(View.GONE);
            binding.recycleViewCart.setVisibility(View.VISIBLE);
        }
    }

    public static void EvenUltil() {
        double totalAmount = 0;
        for (int i = 0; i < MainActivity.carts.size(); i++) {
            totalAmount += MainActivity.carts.get(i).getTotalAmount();
        }
        binding.totalAmountCart.setText(String.format("%,.0f₫", totalAmount));
    }

    @Override
    public void onCartLongClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        String title = getResources().getString(R.string.delete_title_dialog);
        String message = getResources().getString(R.string.delete_question_dialog);
        String positiveText = getResources().getString(R.string.yes);
        String nagativeText = getResources().getString(R.string.no);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (MainActivity.carts.size() <= 0) {
                    binding.nullCart.setVisibility(View.VISIBLE);
                } else {
                    MainActivity.carts.remove(position);
                    cartAdapter.notifyDataSetChanged();
                    EvenUltil();
                    if (MainActivity.carts.size() <= 0) {
                        binding.nullCart.setVisibility(View.VISIBLE);
                    } else {
                        binding.nullCart.setVisibility(View.INVISIBLE);
                        cartAdapter.notifyDataSetChanged();
                        EvenUltil();
                    }
                }
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.showNumberProductInCart();
            }
        });
        builder.setNegativeButton(nagativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cartAdapter.notifyDataSetChanged();
                EvenUltil();
            }
        });
        builder.show();
    }
}
