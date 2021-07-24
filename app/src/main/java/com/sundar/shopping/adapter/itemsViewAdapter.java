package com.sundar.shopping.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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

public class itemsViewAdapter extends RecyclerView.Adapter<itemsViewAdapter.ViewHolder> {
    private Activity activity;
    private JSONArray recentViewedItemsData;
    private LayoutInflater inflater;

    public itemsViewAdapter(Activity activity, JSONArray recentViewedItemsData) {
        this.activity = activity;
        this.recentViewedItemsData = recentViewedItemsData;
        this.inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @NotNull
    @Override
    public itemsViewAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new itemsViewAdapter.ViewHolder(inflater.inflate(R.layout.recycler_view_items, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull itemsViewAdapter.ViewHolder holder, int position) {

        try {
            final JSONObject current = recentViewedItemsData.getJSONObject(position);
            //   holder.ImageView_image
            JSONArray imageArray = current.getJSONArray("images");
            HelperClass.setImage(imageArray.getString(0), holder.ImageView_image, activity);

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        Intent intent = new Intent(activity, ProductDetailsScreen.class);
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


      /*  if (position==0){
          //  holder.ImageView_image.setVisibility(View.VISIBLE);
            holder.view.setVisibility(View.VISIBLE);
            holder.text_view.setVisibility(View.VISIBLE);
          //  holder.card.setVisibility(View.VISIBLE);


        } else {
            holder.ImageView_image.setVisibility(View.VISIBLE);
            holder.view.setVisibility(View.GONE);
            holder.text_view.setVisibility(View.GONE);
           // holder.card.setVisibility(View.GONE);
        }*/
    }


    @Override
    public int getItemCount() {
        return recentViewedItemsData.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ImageView_image;
        RelativeLayout layout;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ImageView_image = itemView.findViewById(R.id.ImageView_image);
            layout = itemView.findViewById(R.id.layout);


        }
    }
}
