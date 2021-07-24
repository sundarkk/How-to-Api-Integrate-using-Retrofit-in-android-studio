package com.sundar.shopping.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sundar.shopping.R;

import org.jetbrains.annotations.NotNull;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder> {
    private Activity activity;
    private LayoutInflater inflater;

    public RatingAdapter(Activity activity) {
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
    }
    @NonNull
    @NotNull
    @Override
    public RatingAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new RatingAdapter.ViewHolder(inflater.inflate(R.layout.rating_items, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull RatingAdapter.ViewHolder holder, int position) {

//        holder.ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//               // String rateValue = String.valueOf(ratingbar1.getRating());
////                Toast.makeText(activity, "click", Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RatingBar ratingbar;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
           // ratingbar = itemView.findViewById(R.id.ratingbar);
        }
    }
}
