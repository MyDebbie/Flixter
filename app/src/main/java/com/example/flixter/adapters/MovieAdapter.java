package com.example.flixter.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.R;
import com.example.flixter.models.Movie;


import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
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
                View v1 = inflater.inflate(R.layout.item_movie1, parent, false);
                viewHolder = new ViewHolder(v1);
        }
        else{
            View v2 = inflater.inflate(R.layout.item_movie2, parent, false);
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
        if (movies.get(position).getRating() > 5){
            return 0;
        }
        else
            return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

    }
    private void configureViewHolder(ViewHolder V1, int position){
        Movie movie = movies.get(position);

        V1.tvTitle.setText(movie.getTitle());
        V1.tvOverview.setText(movie.getOverview());
        String imageUrl;
        // if the phone in landscape
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            // then imageUrl = back drop image
            imageUrl = movie.getBackdropPath();
        }
        else{
            // else imageUrl = poster image
            imageUrl = movie.getPosterPath();
        }
        Glide.with(context).load(imageUrl).placeholder(R.drawable.place_hold).into(V1.ivPoster);

    }

    private void configureViewHolder1(ViewHolder_1 V2, int position){
        Movie movie = movies.get(position);
        Glide.with(context).load(movie.getBackdropPath()).placeholder(R.drawable.place_hold).into(V2.backdropPath);
    }

    public class ViewHolder_1 extends RecyclerView.ViewHolder{
        ImageView backdropPath;

        public ViewHolder_1 (@NonNull View itemView) {
            super(itemView);
            backdropPath = itemView.findViewById(R.id.ivPoster2);
        }
    }
    }





