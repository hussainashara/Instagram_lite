package com.example.sastu_insta.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.sastu_insta.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class UniversaIimageLoader {

    private static final int defaultImage  = R.drawable.ic_loading;
    private Context mcentext;

    public UniversaIimageLoader(Context mcentext) {
        this.mcentext = mcentext;
    }
    public ImageLoaderConfiguration getConfig(){

        DisplayImageOptions defaultoptions = new DisplayImageOptions.Builder()
               .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .cacheOnDisk(true).resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();


        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(mcentext)
                .defaultDisplayImageOptions(defaultoptions)
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(100 * 1024 *1024).build();

        return configuration;


    }

    public static void setImage(String imgUrl, ImageView image, final ProgressBar mprogressBar,String append){


        ImageLoader imageLoader = ImageLoader.getInstance();

        imageLoader.displayImage(append + imgUrl, image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if(mprogressBar != null){
                    mprogressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                if(mprogressBar != null){
                    mprogressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                if(mprogressBar != null){
                    mprogressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

                if(mprogressBar != null){
                    mprogressBar.setVisibility(View.GONE);
                }
            }
        });


    }
}
