package com.example.sastu_insta.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sastu_insta.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

public class GridImageAdapter extends ArrayAdapter<String> {
    private Context mcontext;
    private LayoutInflater layoutInflater;
    private int layotResource;
    private String mAppend;
    private ArrayList<String> imgUrls;

    public GridImageAdapter(Context mcontext,int layotResource, String mAppend, ArrayList<String> imgUrls) {
        super(mcontext,layotResource,imgUrls);
        this.mcontext = mcontext;
        layoutInflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layotResource = layotResource;
        this.mAppend = mAppend;
        this.imgUrls = imgUrls;
    }

    private static class ViewHolder{
       squareImageview image;
        ProgressBar mprogressBar;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null){
            convertView = layoutInflater.inflate(layotResource,parent,false);
            holder = new ViewHolder();
            holder.mprogressBar = convertView.findViewById(R.id.gridprogressbar);
            holder.image = convertView.findViewById(R.id.gridimageview);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        String imgUrl = getItem(position);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(mAppend + imgUrl, holder.image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if(holder.mprogressBar != null){
                    holder.mprogressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                if(holder.mprogressBar != null){
                    holder.mprogressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                if(holder.mprogressBar != null){
                    holder.mprogressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

                if(holder.mprogressBar != null){
                    holder.mprogressBar.setVisibility(View.GONE);
                }
            }
        });
        return convertView;
    }
}
