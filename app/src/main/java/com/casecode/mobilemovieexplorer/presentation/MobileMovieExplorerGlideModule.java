package com.casecode.mobilemovieexplorer.presentation;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.casecode.mobilemovieexplorer.presentation.utils.UnsafeOkHttpClient;

import java.io.InputStream;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Configures Glide for the MobileMovieExplorer app.
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */
@GlideModule
public class MobileMovieExplorerGlideModule  extends AppGlideModule {
    @Override
    public void registerComponents(Context context, Glide glide, Registry registry)
    {
        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        registry.replace(GlideUrl.class, InputStream.class,
                new OkHttpUrlLoader.Factory((Call.Factory) okHttpClient));
    }

    // Disable manifest parsing to avoid adding similar modules twice.
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
