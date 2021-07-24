package com.sundar.shopping.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sundar.shopping.R;
import com.sundar.shopping.activities.ProductDetailsScreen;
import com.sundar.shopping.classes.Constants;
import com.sundar.shopping.classes.HelperClass;


import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dealOffDayAdapter extends RecyclerView.Adapter<dealOffDayAdapter.ViewHolder> {
    private Activity activity;
    private JSONArray bannerSectionThree;
    private LayoutInflater inflater;

    public dealOffDayAdapter(Activity activity, JSONArray bannerSectionThree) {
        this.activity = activity;
        this.bannerSectionThree = bannerSectionThree;
        this.inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @NotNull
    @Override
    public dealOffDayAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new dealOffDayAdapter.ViewHolder(inflater.inflate(R.layout.recyler_deal_off_day_items, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull dealOffDayAdapter.ViewHolder holder, int position) {

        try {
            final JSONObject current = bannerSectionThree.getJSONObject(position);


            // HelperClass.setBannerImage(current.getString("image_thumb"), holder.image_deal_of_day, activity);
            HelperClass.setBannerImage(current.getString("image_thumb"), holder.image_deal_of_day, activity);

            holder.percentage.setText(current.getString("discount"));
            holder.section_three_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(activity, ProductDetailsScreen.class);
                    try {
                        intent.putExtra(Constants.PRODUCT_ID, current.getString("id").toString());
                        intent.putExtra(Constants.MULTIPLEIMAGES, current.getJSONArray("images").toString());
                        activity.startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return bannerSectionThree.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_deal_of_day;
        TextView percentage;
        CardView section_three_layout;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image_deal_of_day = itemView.findViewById(R.id.image_deal_of_day);
            percentage = itemView.findViewById(R.id.percentage);
            section_three_layout = itemView.findViewById(R.id.section_three_layout);

        }
    }
}
