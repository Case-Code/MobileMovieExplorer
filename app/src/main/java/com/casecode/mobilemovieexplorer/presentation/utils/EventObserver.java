package com.casecode.mobilemovieexplorer.presentation.utils;
import androidx.lifecycle.Observer;

/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 *   already been handled.
 *   [onEventUnhandledContent] is *only* called if the [Event]'s contents have not been handled.
 * Created by Mahmoud Abdalhafeez on 12/26/2023
 */

public class EventObserver<T> implements Observer<Event<T>> {

    private final OnEventUnhandledContent<T> onEventUnhandledContent;

    public EventObserver(OnEventUnhandledContent<T> onEventUnhandledContent) {
        this.onEventUnhandledContent = onEventUnhandledContent;
    }

    @Override
    public void onChanged(Event<T> value) {
        T content = value.getContentIfNotHandled();
        if (content != null) {
            onEventUnhandledContent.onEventUnhandledContent(content);
        }
    }

    public interface OnEventUnhandledContent<T> {
        void onEventUnhandledContent(T content);
    }
}
