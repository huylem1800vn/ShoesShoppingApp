package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.home;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.MainActivity;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.R;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.databinding.FragmentCustomerInformationBinding;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Cart;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Order;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.ResponseAPI;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil.SOService;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil.Server;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerInformationFragment extends Fragment {

   private SOService soService;
   private FragmentCustomerInformationBinding binding;
   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      binding = FragmentCustomerInformationBinding.inflate(inflater, container, false);
      View root = binding.getRoot();
      soService = Server.getSOService();

      binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            String customerName = binding.editTextCustomerName.getText().toString();
            String customerAddress = binding.editTextCustomerAddress.getText().toString();
            String customerPhone = binding.editTextCustomerPhone.getText().toString();
            if(checkData(customerName, customerAddress, customerPhone)){
               addOrder(customerName, customerAddress, customerPhone);
            }
         }
      });

      return root;
   }

   private void addOrder(String customerName, String customerAddress, String customerPhone) {
      double totalAmount = 0;
      for(int i = 0; i < MainActivity.carts.size(); i++){
         Cart cart = MainActivity.carts.get(i);
         totalAmount += cart.getQuantity() * cart.getPrice();
      }
      long orderID = new Date().getTime()/1000;
      Date date = new Date();
      Order order = new Order(String.valueOf(orderID), customerName, customerAddress, customerPhone,
              totalAmount, date);

      // thêm order
      soService.addOrder(order).enqueue(new Callback<ResponseAPI>() {
         @Override
         public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
            Log.i("API", "CALL API ADD ORDER SUCCESSFUL");
            // thêm order detail
            boolean isSuccess = false;
            for(int i = 0; i < MainActivity.carts.size(); i++){
               Cart cart = MainActivity.carts.get(i);
               double totalAmount = cart.getPrice() * cart.getQuantity();
               Cart orderDetail = new Cart(cart.getProductID(), cart.getSize(), cart.getQuantity(),
                       totalAmount, String.valueOf(orderID));
               soService.addOrderDetail(orderDetail).enqueue(new Callback<ResponseAPI>() {
                  @Override
                  public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                     Log.i("API", "CALL API ADD ORDER DETAIL SUCCESSFUL");
                  }

                  @Override
                  public void onFailure(Call<ResponseAPI> call, Throwable t) {
                     Log.i("API", "CALL API ADD ORDER DETAIL FAILED");
                  }
               });
               if(i == (MainActivity.carts.size() -1)){
                  isSuccess = true;
               }
            }
            if(isSuccess){
               String text = getResources().getString(R.string.order_done);
               Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
               MainActivity.carts.clear();
               MainActivity mainActivity = (MainActivity) getActivity();
               mainActivity.clearBackStack();
               mainActivity.showNumberProductInCart();
               mainActivity.createNotification();

               getParentFragmentManager()
                       .beginTransaction()
                       .replace(R.id.nav_host_fragment_content_main, new HomeFragment())
                       .commit();
            }
         }

         @Override
         public void onFailure(Call<ResponseAPI> call, Throwable t) {
            Log.i("API", "CALL API ADD ORDER FAILED");

         }
      });
   }

   private boolean checkData(String customerName, String customerAddress, String customerPhone) {
      if(customerAddress.length() > 0 && customerName.length() > 0 && customerPhone.length() > 0){
         return true;
      } else {
         String text = getResources().getString(R.string.error_data);
         Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
         return false;
      }
   }




}
