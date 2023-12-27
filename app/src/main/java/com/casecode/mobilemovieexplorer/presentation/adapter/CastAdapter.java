package com.casecode.mobilemovieexplorer.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.Cast;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    private List<Cast> castList;
    private Context context;

    public CastAdapter(List<Cast> castList) {
        this.castList = castList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_cast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cast cast = castList.get(position);

        // Load cast images using Glide or Picasso
        Glide.with(context)
                .load(cast.getProfilePath())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.castImageView);

        holder.textViewName.setText(cast.getName());
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView castImageView;
        TextView textViewName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            castImageView = itemView.findViewById(R.id.imageViewProfile);
            textViewName = itemView.findViewById(R.id.textViewName);
        }
    }
}
