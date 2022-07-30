package com.example.flixter.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.flixter.DetailActivity;
import com.example.flixter.MainActivity;
import com.example.flixter.R;
import com.example.flixter.databinding.ItemMovie1Binding;
import com.example.flixter.databinding.ItemMovie2Binding;
import com.example.flixter.models.Movie;

import org.parceler.Parcels;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static Context context;
    List<Movie>movies;


    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        Log.d("MovieAdapter", "onCreateViewHolder");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == 1){
                ItemMovie1Binding v1 = DataBindingUtil.inflate(inflater, R.layout.item_movie1, parent, false);
                viewHolder = new ViewHolder(v1);
        }
        else{
            ItemMovie2Binding v2 = DataBindingUtil.inflate(inflater, R.layout.item_movie2, parent, false);
            viewHolder = new ViewHolder_1(v2);
        }
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " + position);
        // Get the movie at the pass in the position
        if (holder.getItemViewType() == 1){
            ViewHolder V1 = (ViewHolder) holder;
            configureViewHolder(V1, position);
        }
        else{
            ViewHolder_1 V2 = (ViewHolder_1) holder;
            configureViewHolder1(V2, position);
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(int position){
        if (movies.get(position).getRating() > 7){
            return 0;
        }
        else
            return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemMovie1Binding binding;


        public ViewHolder (@NonNull ItemMovie1Binding bindingView) {
            super(bindingView.getRoot());

            binding = bindingView;

        }

    }
    private void configureViewHolder(ViewHolder V1, int position){
        Movie movie = movies.get(position);

        V1.binding.setMovie(movie);


        // 1. Register click listener on the whole row
        V1.binding.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 2. Navigate to a new activity on tap
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("movie", Parcels.wrap(movie));
                ActivityOptions options = ActivityOptions.
                        makeSceneTransitionAnimation((Activity) context, V1.binding.ivPoster, "profile");
                context.startActivity(i, options.toBundle());

            }
        });

    }

    private void configureViewHolder1(ViewHolder_1 V2, int position){
        Movie movie = movies.get(position);
        V2.binding.setMovie(movie);


        // 1. Register click listener on the whole row
        V2.binding.container1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 2. Navigate to a new activity on tap
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("movie", Parcels.wrap(movie));
                ActivityOptions options = ActivityOptions.
                        makeSceneTransitionAnimation((Activity) context, V2.binding.ivPoster2,"profile");
                context.startActivity(i, options.toBundle());

            }
        });

    }

    public class ViewHolder_1 extends RecyclerView.ViewHolder{

        ItemMovie2Binding binding;

        public ViewHolder_1 (@NonNull ItemMovie2Binding bindingView) {
            super(bindingView.getRoot());

            binding = bindingView;
        }
    }

    public static class BindingAdapterUtils{
        @BindingAdapter({"imageUrl1"})
        public static void loadImage1(ImageView view, String url) {
         Glide.with(context).load(url).transform(new RoundedCorners(100)).placeholder(R.drawable.place_hold).into(view);
        }
        @BindingAdapter({"imageUrl"})
        public static void loadImage(ImageView view, String url) {
            Glide.with(context).load(url).transform(new RoundedCorners(100)).placeholder(R.drawable.place_hold).into(view);
        }
    }
    }





