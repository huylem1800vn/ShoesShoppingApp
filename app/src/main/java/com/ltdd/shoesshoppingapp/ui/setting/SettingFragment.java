package com.ltdd.shoesshoppingapp.ui.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.ltdd.shoesshoppingapp.R;
import com.ltdd.shoesshoppingapp.databinding.FragmentSettingBinding;
import com.ltdd.shoesshoppingapp.localdata.DataLocalManager;

public class SettingFragment extends Fragment {

    private FragmentSettingBinding binding;
    private AutoCompleteTextView autoCompleteTextView;
    private LottieAnimationView darkMode;
    private Boolean isSwitchOn = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // dropdown language
        autoCompleteTextView = binding.filledExposed;
        // add array từ file values/strings.xml vào
        String[] language = getResources().getStringArray(R.array.language_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                root.getContext(), R.layout.drop_down_item, language);
        autoCompleteTextView.setAdapter(adapter);
        // set giá trị mặc định cho ngôn ngữ
        if(DataLocalManager.getVietNameseLanguage()){
            autoCompleteTextView.setText(autoCompleteTextView.getAdapter().getItem(0).toString(), false);
        } else {
            autoCompleteTextView.setText(autoCompleteTextView.getAdapter().getItem(1).toString(), false);
        }

        // sự kiện click chọn item của dropdown language
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        DataLocalManager.setVietnameseLanguage(true);
                        break;
                    case 1:
                        DataLocalManager.setVietnameseLanguage(false);
                        break;
                }
                recreate();
            }
        });

        // chế độ ban đêm
        darkMode = binding.switchDarkMode;
        if(DataLocalManager.getDarMode()){
            isSwitchOn = true;
        }
        if(isSwitchOn){
            // dark mode
            darkMode.setProgress(0.0f);
        } else {
            // normal
            darkMode.setProgress(0.5f);
        }

        darkMode.setOnClickListener(v -> {
            if(!isSwitchOn){
                // chuyển qua dark mode
                darkMode.setProgress(0.0f);
                isSwitchOn = true;
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                DataLocalManager.setDarkMode(true);
            } else {
                // chuyển qua normal
                darkMode.setProgress(0.5f);
                isSwitchOn = false;
                DataLocalManager.setDarkMode(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void recreate(){
        getActivity().finish();
        startActivity(getActivity().getIntent());
    }
}