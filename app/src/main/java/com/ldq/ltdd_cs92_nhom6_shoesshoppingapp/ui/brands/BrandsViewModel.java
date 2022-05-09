package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.brands;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BrandsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BrandsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is brand fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}