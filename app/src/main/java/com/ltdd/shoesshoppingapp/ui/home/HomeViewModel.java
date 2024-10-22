package com.ltdd.shoesshoppingapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    private ArrayList<String> photolist = new ArrayList<>();
    private MutableLiveData<ArrayList<String>> mPhotos;

    public HomeViewModel() {
        LivePhotos();
        AddListPhoto();
    }

    private void AddListPhoto() {
        photolist.add("https://i.pinimg.com/564x/09/9b/18/099b1881915da67f7c00af4f093d7013.jpg");
        photolist.add("https://i.pinimg.com/564x/5d/d5/5c/5dd55c383ade34f2a2970f283ffd3661.jpg");
        photolist.add("https://i.pinimg.com/564x/4b/8b/71/4b8b719bfe9bae95d3d89264a8cc742b.jpg");
    }

    private void LivePhotos() {
        mPhotos = new MutableLiveData<>();
        mPhotos.setValue(photolist);
    }

    public LiveData<ArrayList<String>> getPhotolists() {
        return mPhotos;
    }
}