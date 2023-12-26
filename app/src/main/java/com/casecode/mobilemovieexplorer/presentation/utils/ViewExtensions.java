package com.casecode.mobilemovieexplorer.presentation.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import lombok.experimental.ExtensionMethod;

/**
 * Created by Mahmoud Abdalhafeez on 12/26/2023
 */
@ExtensionMethod({ViewExtensions.class})
public class ViewExtensions {

    /**
     * Transforms static Java function Snackbar.make() to an extension function on View.
     */
    public static void showSnackbar(View view, String snackbarText, int timeLength) {
        Snackbar.make(view, snackbarText, timeLength)
                .show();
    }


}
