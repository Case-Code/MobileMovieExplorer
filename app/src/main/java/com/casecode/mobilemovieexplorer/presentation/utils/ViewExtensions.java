package com.casecode.mobilemovieexplorer.presentation.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import lombok.experimental.ExtensionMethod;

/**
 * Utility class for extending functionality related to Android Views.
 */
@ExtensionMethod({ViewExtensions.class})
public class ViewExtensions {

    /**
     * Displays a Snackbar on the specified View.
     *
     * @param view         The View on which the Snackbar will be displayed.
     * @param snackbarText The text to show in the Snackbar.
     * @param timeLength   Duration of the Snackbar display (e.g., Snackbar.LENGTH_SHORT).
     */
    public static void showSnackbar(View view, String snackbarText, int timeLength) {
        /**
         * Transforms static Java function Snackbar.make() to an extension function on View.
         *
         * @param view          The View on which the Snackbar will be displayed.
         * @param snackbarText  The text to show in the Snackbar.
         * @param timeLength    Duration of the Snackbar display (e.g., Snackbar.LENGTH_SHORT).
         */
        Snackbar.make(view, snackbarText, timeLength).show();
    }
}
