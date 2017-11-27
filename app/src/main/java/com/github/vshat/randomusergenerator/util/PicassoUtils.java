package com.github.vshat.randomusergenerator.util;

import android.content.Context;
import android.widget.ImageView;

import com.github.vshat.randomusergenerator.R;
import com.squareup.picasso.Picasso;

public class PicassoUtils {

    public static void loadImage(Context context, String url, ImageView imageView) {
        Picasso
                .with(context)
                .load(url)
                .error(R.drawable.ic_image_broken)
                .fit()
                .centerCrop()
                .transform(new CircleTransform())
                .into(imageView);
    }
}
