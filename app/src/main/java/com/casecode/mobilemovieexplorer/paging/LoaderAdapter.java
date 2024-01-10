package com.casecode.mobilemovieexplorer.paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.casecode.mobilemovieexplorer.R;

/**
 * Created by Mahmoud Abdalhafeez on 12/3/2023
 */
public class LoaderAdapter extends LoadStateAdapter<LoaderAdapter.LoaderViewHolder> {


    @Override
    public void onBindViewHolder(LoaderViewHolder holder, @NonNull LoadState loadState) {
        holder.bind(loadState);
    }

    @NonNull
    @Override
    public LoaderViewHolder onCreateViewHolder(ViewGroup parent, @NonNull LoadState loadState) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.network_loader, parent, false);
        return new LoaderViewHolder(view);
    }

    public static class LoaderViewHolder extends RecyclerView.ViewHolder {
        private final ProgressBar progressBar;
        private final TextView errorTextView;

        public LoaderViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar);
            errorTextView = itemView.findViewById(R.id.error_textView);
        }

        public void bind(LoadState loadState) {
            progressBar.setVisibility(loadState instanceof LoadState.Loading ? View.VISIBLE : View.GONE);
            errorTextView.setVisibility(loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE);
        }
    }
}
