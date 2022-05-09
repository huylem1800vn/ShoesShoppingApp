package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.databinding.ActivityMainBinding;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.brands.BrandsFragment;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.gallery.GalleryFragment;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.home.HomeFragment;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.setting.SettingFragment;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_ORDERS = 1;
    private static final int FRAGMENT_BRANDS = 2;
    private static final int FRAGMENT_SETTING = 3;
    private int currentFragment = FRAGMENT_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                binding.appBarMain.toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View header = navigationView.inflateHeaderView(R.layout.nav_header_main);
        LinearLayout home = header.findViewById(R.id.home);
        LinearLayout orders = header.findViewById(R.id.orders);
        LinearLayout brands = header.findViewById(R.id.brand);
        LinearLayout setting = header.findViewById(R.id.setting);

        home.setOnClickListener(v -> {
            if(currentFragment != FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                currentFragment = FRAGMENT_HOME;
            }
            drawer.closeDrawer(GravityCompat.START);
        });
        orders.setOnClickListener(v -> {
            if(currentFragment != FRAGMENT_ORDERS){
                replaceFragment(new GalleryFragment());
                currentFragment = FRAGMENT_ORDERS;
            }
            drawer.closeDrawer(GravityCompat.START);
        });
        brands.setOnClickListener(v -> {
            if(currentFragment != FRAGMENT_BRANDS){
                replaceFragment(new BrandsFragment());
                currentFragment = FRAGMENT_BRANDS;
            }
            drawer.closeDrawer(GravityCompat.START);
        });
        setting.setOnClickListener(v -> {
            if(currentFragment != FRAGMENT_SETTING){
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
        int id = item.getItemId();
        Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
        return true;
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, fragment);
        transaction.commit();
    }
}