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

public class offerAdapter extends RecyclerView.Adapter<offerAdapter.ViewHolder> {
    private Activity activity;
    private JSONArray bannerSectionFour;
    private LayoutInflater inflater;

    public offerAdapter(Activity activity, JSONArray bannerSectionFour) {
        this.activity = activity;
        this.bannerSectionFour = bannerSectionFour;
        this.inflater = LayoutInflater.from(activity);
    }
    @NonNull
    @NotNull
    @Override
    public offerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new offerAdapter.ViewHolder(inflater.inflate(R.layout.recycler_offer_items, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull offerAdapter.ViewHolder holder, int position) {


        try {
            final JSONObject current = bannerSectionFour.getJSONObject(position);
            HelperClass.setBannerImage(current.getString("image"), holder.offer_image, activity);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public int getItemCount() {
        return bannerSectionFour.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView offer_image;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            offer_image = itemView.findViewById(R.id.offer_image);
        }
    }
}
