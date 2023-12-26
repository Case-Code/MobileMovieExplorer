package com.casecode.mobilemovieexplorer.presentation.utils;

/**
 *  Used as a wrapper for data that is exposed via a LiveData that represents an event.
 * Created by Mahmoud Abdalhafeez on 12/26/2023
 */

public class Event<T> {

    private final T content;
    private boolean hasBeenHandled = false;

    public Event(T content) {
        this.content = content;
    }

    /**
     * Returns the content and prevents its use again.
     */
    public T getContentIfNotHandled() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    public T peekContent() {
        return content;
    }
}