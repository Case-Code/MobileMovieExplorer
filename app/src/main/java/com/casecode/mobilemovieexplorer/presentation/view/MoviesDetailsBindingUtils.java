package com.casecode.mobilemovieexplorer.presentation.view;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.casecode.mobilemovieexplorer.domain.model.demodetails.Genre;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.Cast;
import com.casecode.mobilemovieexplorer.presentation.adapter.CastAdapter;
import com.casecode.mobilemovieexplorer.presentation.adapter.GenresAdapter;

import java.util.List;

import timber.log.Timber;

/**
 * Created by Mahmoud Abdalhafeez on 12/28/2023
 */
public class MoviesDetailsBindingUtils {

    @BindingAdapter("setGenresAdapter")
    public static void setGenresAdapter(RecyclerView recyclerView,
                                        GenresAdapter adapter) {
        recyclerView.setAdapter(adapter);

    }
    @BindingAdapter("setDataGenresAdapter")
    public static void setDataGenresAdapter(RecyclerView recyclerView, @Nullable List<Genre> list) {
        var adapter = (GenresAdapter) recyclerView.getAdapter();
        if (list != null && adapter != null) {
            adapter.updateList(list);

        } else {
            Timber.e("adapter = " + adapter + ", List = " + list);
        }

    }
    @BindingAdapter("setCastAdapter")
    public static void setCastAdapter(RecyclerView recyclerView,
                                      CastAdapter adapter) {
        recyclerView.setAdapter(adapter);

    }
    @BindingAdapter("setDataCastAdapter")
    public static void setDataCastAdapter(RecyclerView recyclerView, @Nullable List<Cast> list) {
        var adapter = (CastAdapter) recyclerView.getAdapter();
        if (list != null && adapter != null) {
            adapter.updateList(list);

        } else {
            Timber.e("adapter = " + adapter + ", List = " + list);
        }

    }
}
