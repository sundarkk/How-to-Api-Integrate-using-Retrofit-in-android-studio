package com.sundar.shopping.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sundar.shopping.R;
import com.sundar.shopping.classes.Constants;
import com.sundar.shopping.classes.HelperClass;


import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class categories_Adapter extends RecyclerView.Adapter<categories_Adapter.ViewHolder> {
    private Activity activity;
    private LayoutInflater inflater;
    private JSONArray categoriesData;

    public categories_Adapter(Activity activity, JSONArray categoriesData) {
        this.activity = activity;
        this.categoriesData = categoriesData;
        this.inflater = LayoutInflater.from(activity);
    }


    public categories_Adapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.recyclerview_categories_items, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull categories_Adapter.ViewHolder holder, int position) {

        //holder.categories_image =


        if (position == 0) {
            holder.categories_image.setImageResource(R.drawable.dddd);
            holder.view.setVisibility(View.GONE);

        } else {

            try {

                final JSONObject current = categoriesData.getJSONObject(position);
                HelperClass.setImage(current.getString("image"), holder.categories_image, activity);
                holder.categories_name.setText(current.getString("category_name"));

               /* holder.layout_main.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            Intent intent = new Intent(activity, TopCategoriesDetailsActivity.class);
                            intent.putExtra(Constants.CATEGORYID, current.getString("id"));
                            activity.startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });*/

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


    @Override
    public int getItemCount() {
        return categoriesData.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categories_image;
        TextView categories_name;
        View view;
        RelativeLayout layout_main;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            categories_image = itemView.findViewById(R.id.categories_image);
            categories_name = itemView.findViewById(R.id.categories_name);
            view = itemView.findViewById(R.id.view);
            layout_main = itemView.findViewById(R.id.layout_main);
        }
    }
}
