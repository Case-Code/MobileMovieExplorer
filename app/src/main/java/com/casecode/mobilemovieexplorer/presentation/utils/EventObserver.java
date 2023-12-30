package com.casecode.mobilemovieexplorer.presentation.utils;

import androidx.lifecycle.Observer;

/**
 * An Observer for observing events of type Event<T>. It ensures that the onChanged method
 * is called only once for each event, preventing multiple invocations for the same event.
 *
 * @param <T> The type of content associated with the event.
 */
public class EventObserver<T> implements Observer<Event<T>> {

    /**
     * The callback to be invoked when an unhandled event is observed.
     */
    private final OnEventUnhandledContent<T> onEventUnhandledContent;

    /**
     * Constructs an EventObserver with the specified callback for unhandled events.
     *
     * @param onEventUnhandledContent The callback to be invoked when an unhandled event is observed.
     */
    public EventObserver(OnEventUnhandledContent<T> onEventUnhandledContent) {
        this.onEventUnhandledContent = onEventUnhandledContent;
    }

    /**
     * Called when the observed Event<T> is changed. It checks if the event has been handled,
     * and if not, it invokes the callback with the event's content.
     *
     * @param value The observed Event<T>.
     */
    @Override
    public void onChanged(Event<T> value) {
        if(value != null) {
            T content = value.getContentIfNotHandled();
            if (content != null) {
                onEventUnhandledContent.onEventUnhandledContent(content);
            }
        }
    }

    /**
     * Interface for handling the content of unhandled events.
     *
     * @param <T> The type of content associated with the event.
     */
    public interface OnEventUnhandledContent<T> {
        void onEventUnhandledContent(T content);

    }
}
