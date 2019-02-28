package com.jakewhartonproject.android.helper;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jakewhartonproject.android.R;

public class BindingAdapterUtils {

   private static final String TAG = "BindingAdapterUtils";


    @BindingAdapter(value = {"app:imageUrl"}, requireAll = false)
    public static void setImageUrl(ImageView view, String imageUrl) {
        if (view == null) {
            return;
        }
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .skipMemoryCache(true)
                .dontAnimate()
                .into(view);
    }

}