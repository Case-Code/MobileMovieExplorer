package com.casecode.mobilemovieexplorer.presentation.utils;

/**
 * A class representing an event with content of type T. This class is used to deliver
 * one-time events, preventing their use after being consumed.
 *
 * @param <T> The type of content associated with the event.
 */
public class Event<T> {

    /**
     * The content of the event.
     */
    private final T content;

    /**
     * A flag indicating whether the event has already been handled.
     */
    private boolean hasBeenHandled = false;

    /**
     * Constructs an Event with the given content.
     *
     * @param content The content associated with the event.
     */
    public Event(T content) {
        this.content = content;
    }

    /**
     * Returns the content if it has not been handled yet, marking it as handled in the process.
     *
     * @return The content of the event if it hasn't been handled; otherwise, null.
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
     * Returns the content of the event, even if it has been handled.
     *
     * @return The content of the event.
     */
    public T peekContent() {
        return content;
    }
}