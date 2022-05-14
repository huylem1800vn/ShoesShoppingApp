package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.databinding.ActivityMainBinding;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.localdata.DataLocalManager;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Cart;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.brands.BrandDetailFragment;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.brands.BrandsFragment;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.gallery.GalleryFragment;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.home.CartFragment;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.home.HomeFragment;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.orders.OrdersFragment;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.setting.SettingFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DrawerLayout drawer;
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_ORDERS = 1;
    private static final int FRAGMENT_BRANDS = 2;
    private static final int FRAGMENT_SETTING = 3;
    private int currentFragment = FRAGMENT_HOME;
    public static ArrayList<Cart> carts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // khởi tạo giỏ hàng
        if (carts == null) {
            carts = new ArrayList<>();
        }
        boolean i = DataLocalManager.getVietNameseLanguage();
        if (DataLocalManager.getVietNameseLanguage()) {
            Utils.setLocale("vi", this);
        } else {
            Utils.setLocale("en", this);
        }

        Toolbar toolbar = binding.appBarMain.toolbar;
        setSupportActionBar(toolbar);

        drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                binding.appBarMain.toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setMenuButton();

        View header = navigationView.inflateHeaderView(R.layout.nav_header_main);
        LinearLayout home = header.findViewById(R.id.home);
        LinearLayout orders = header.findViewById(R.id.orders);
        LinearLayout brands = header.findViewById(R.id.brand);
        LinearLayout setting = header.findViewById(R.id.setting);

        home.setOnClickListener(v -> {
            if (currentFragment != FRAGMENT_HOME) {
                replaceFragment(new HomeFragment());
                currentFragment = FRAGMENT_HOME;
            }
            drawer.closeDrawer(GravityCompat.START);
        });
        orders.setOnClickListener(v -> {
            if (currentFragment != FRAGMENT_ORDERS) {
                replaceFragment(new OrdersFragment());
                currentFragment = FRAGMENT_ORDERS;
            }
            drawer.closeDrawer(GravityCompat.START);
        });
        brands.setOnClickListener(v -> {
            if (currentFragment != FRAGMENT_BRANDS) {
                replaceFragment(new BrandsFragment());
                currentFragment = FRAGMENT_BRANDS;
            }
            drawer.closeDrawer(GravityCompat.START);
        });
        setting.setOnClickListener(v -> {
            if (currentFragment != FRAGMENT_SETTING) {
                replaceFragment(new SettingFragment());
                currentFragment = FRAGMENT_SETTING;
            }
            drawer.closeDrawer(GravityCompat.START);
        });

        replaceFragment(new HomeFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // sự click vào giỏ hàng trên thanh app bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        CartFragment cartFragment = new CartFragment();
        String backStateName = cartFragment.getClass().getName();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, cartFragment)
                .addToBackStack(backStateName)
                .commit();
        setBackButton();
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, fragment);
        transaction.commit();
    }

    public void setBackButton() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        binding.appBarMain.toolbar.setNavigationIcon(R.drawable.ic_back);

        binding.appBarMain.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack();
                setMenuButton();
            }
        });
    }

    public void setMenuButton() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() <= 1) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            binding.appBarMain.toolbar.setNavigationIcon(R.drawable.ic_menu);
            binding.appBarMain.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer.openDrawer(GravityCompat.START);
                }
            });
        }
    }

    public void showNumberProductInCart() {
        TextView productNumberCart = binding.appBarMain.productNumberCart;
        if (carts.size() > 0) {
            // nếu giỏ hàng có sản phẩm thì hiện
            productNumberCart.setText(String.valueOf(carts.size()));
            productNumberCart.setVisibility(View.VISIBLE);
        } else {
            // nếu giỏ hàng không có sản phẩm thì ẩn
            productNumberCart.setVisibility(View.GONE);
        }
    }

    public void clearBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(0);
            fragmentManager.popBackStack(entry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        binding.appBarMain.toolbar.setNavigationIcon(R.drawable.ic_menu);
        binding.appBarMain.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }

    public void createNotification(){
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        Notification notification = new Notification.Builder(this)
//                .setContentTitle("Title push notification")
//                .setContentText("Message push notification")
//                .setSmallIcon(R.drawable.ic_cart_2)
//                .setLargeIcon(bitmap)
//                .build();
//        NotificationManager notificationManager = (NotificationManager) getSystemService(
//                Context.NOTIFICATION_SERVICE);
//        if(notificationManager != null){
//            notificationManager.notify(1, notification);
//        }
    }
}