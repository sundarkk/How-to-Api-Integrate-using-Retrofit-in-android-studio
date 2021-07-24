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

import org.json.JSONArray;

public class ProductDetailsImageAdapter extends RecyclerView.Adapter<ProductDetailsImageAdapter.ViewHolder> {

    private Activity activity;
    private LayoutInflater inflater;
    private JSONArray data;

    public ProductDetailsImageAdapter(Activity activity, JSONArray data) {
        this.activity = activity;
        this.data = data;
        this.inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public ProductDetailsImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductDetailsImageAdapter.ViewHolder(inflater.inflate(R.layout.poduct_details_image_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailsImageAdapter.ViewHolder holder, int position) {
        try {
            String current = data.getString(position);
            HelperClass.setBannerImage(current, holder.product_image, activity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView product_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);
        }
    }
}
