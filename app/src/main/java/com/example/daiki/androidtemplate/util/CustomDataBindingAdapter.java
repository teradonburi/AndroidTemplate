package com.example.daiki.androidtemplate.util;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.example.daiki.androidtemplate.R;
import com.squareup.picasso.Picasso;

/**
 * Created by daiki on 2017/05/05.
 */

public class CustomDataBindingAdapter {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if(!TextUtils.isEmpty(imageUrl)){
            Picasso.with(view.getContext())
                    .load(imageUrl)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(view);
        }
    }
}
