package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ui.home;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.adapter.ShoesAdapter;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Shoes;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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