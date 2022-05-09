package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.brands;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.databinding.FragmentBrandsBinding;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.databinding.FragmentGalleryBinding;

public class BrandsFragment extends Fragment {

    private FragmentBrandsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BrandsViewModel brandsViewModel =
                new ViewModelProvider(this).get(BrandsViewModel.class);

        binding = FragmentBrandsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textBrands;
        brandsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}