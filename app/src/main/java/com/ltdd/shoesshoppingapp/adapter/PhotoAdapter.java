package com.ltdd.shoesshoppingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.ltdd.shoesshoppingapp.R;
import com.ltdd.shoesshoppingapp.model.Photo;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {

    private final Context context;
    private final List<Photo> photoList;

    public PhotoAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        if(photoList != null){
            return photoList.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo, container, false);
        ImageView imageView = view.findViewById(R.id.image_photo);

        Photo photo = photoList.get(position);
        if(photo != null){
//            Picasso.get().load(photo.getLink())
//                    .placeholder(R.drawable.ic_logo)
//                    .resize(200,200)
//                    .error(R.drawable.ic_search)
//                    .into(imageView);
            Glide.with(context).load(photo.getLink())
                    .centerCrop()
                    .error(R.drawable.ic_logo)
                    .into(imageView);
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
