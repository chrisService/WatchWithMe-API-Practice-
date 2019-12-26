package com.example.watchwithme.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.watchwithme.R;
import com.example.watchwithme.model.Movie;
import com.example.watchwithme.repository.WatchRepository;

import java.util.List;

public class WatchAdapter extends RecyclerView.Adapter<WatchAdapter.WatchViewHolder> {

    private List<Movie> movies;
    private Context context;

    public WatchAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public WatchAdapter.WatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);

        return new WatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchAdapter.WatchViewHolder holder, int position) {

        Movie movie = movies.get(holder.getAdapterPosition());

        holder.raiting.setText(String.valueOf(movie.getVoteAverage()));
        holder.releaseDate.setText(movie.getReleaseDate());
        holder.title.setText(movie.getTitle());

        String uri = movie.getUrl();

        Glide.with(holder.poster.getContext()).load(uri).into(holder.poster);




    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class WatchViewHolder extends RecyclerView.ViewHolder {

        TextView raiting, releaseDate, title;
        ImageView poster;

        public WatchViewHolder(@NonNull View itemView) {
            super(itemView);

            raiting = itemView.findViewById(R.id.raiting);
            releaseDate = itemView.findViewById(R.id.releaseDate);
            title = itemView.findViewById(R.id.movieTitle);

            poster = itemView.findViewById(R.id.poster);

        }
    }
}
