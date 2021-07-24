package com.sundar.shopping.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sundar.shopping.R;
import com.sundar.shopping.activities.ProductDetailsScreen;
import com.sundar.shopping.classes.Constants;
import com.sundar.shopping.classes.HelperClass;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class arrivalAdapter extends RecyclerView.Adapter<arrivalAdapter.ViewHolder> {
    private Activity activity;
    private JSONArray bannerSetctionTwo;
    private LayoutInflater inflater;

    public arrivalAdapter(Activity activity, JSONArray bannerSetctionTwo) {
        this.activity = activity;
        this.bannerSetctionTwo = bannerSetctionTwo;
        this.inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @NotNull
    @Override
    public arrivalAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new arrivalAdapter.ViewHolder(inflater.inflate(R.layout.recyclerview_arrival_items, parent, false));


    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull arrivalAdapter.ViewHolder holder, int position) {

        try {
            final JSONObject current = bannerSetctionTwo.getJSONObject(position);
            holder.product_name.setText(current.getString("title"));
            holder.text_min.setText("Upto"+ " " + current.getString("discount")+ " " + "off");

            JSONArray imagedta = current.getJSONArray("images");
            HelperClass.setBannerImage(imagedta.getString(0), holder.price_off_image, activity);
         //   HelperClass.setBannerImage(current.getString("image_thumb"), holder.price_off_image, activity);

            holder.arrival_main_layout.setOnClickListener(new View.OnClickListener() {
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
        return bannerSetctionTwo.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView price_off_image;
        TextView text_min, product_name;
        LinearLayout arrival_main_layout;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            price_off_image = itemView.findViewById(R.id.price_off_image);
            text_min = itemView.findViewById(R.id.text_min);
            product_name = itemView.findViewById(R.id.product_name);
            arrival_main_layout = itemView.findViewById(R.id.arrival_main_layout);
        }
    }
}
