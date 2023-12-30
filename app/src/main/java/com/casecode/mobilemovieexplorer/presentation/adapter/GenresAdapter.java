package com.casecode.mobilemovieexplorer.presentation.adapter;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.GenreViewHolder> {

    private List<Genre> genres = new ArrayList<>();

    public GenresAdapter() {
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<Genre> genres) {
        this.genres = genres;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        Genre genre = genres.get(position);
        holder.bind(genre);
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    static class GenreViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewGenre;

        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewGenre = itemView.findViewById(R.id.textViewGenre);
        }

        public void bind(Genre genre) {
            textViewGenre.setText(genre.name());
        }
    }
}
