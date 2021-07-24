package com.sundar.shopping.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sundar.shopping.R;
import com.sundar.shopping.classes.HelperClass;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class priceOffAdapter extends RecyclerView.Adapter<priceOffAdapter.ViewHolder> {
    private Activity activity;
    private LayoutInflater inflater;
    private JSONArray bannerSectionOne;

    public priceOffAdapter(Activity activity, JSONArray bannerSectionOne ) {
        this.activity = activity;
        this.bannerSectionOne = bannerSectionOne;
        this.inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @NotNull
    @Override
    public priceOffAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new priceOffAdapter.ViewHolder(inflater.inflate(R.layout.recyclerview_price_off_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull priceOffAdapter.ViewHolder holder, int position) {

        try {
            final JSONObject current = bannerSectionOne.getJSONObject(position);
           // holder.price_off_image.setIm
            HelperClass.setBannerImage(current.getString("image"), holder.price_off_image, activity);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {

        return bannerSectionOne.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView price_off_image;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            price_off_image = itemView.findViewById(R.id.price_off_image);
        }
    }
}
