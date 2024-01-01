package com.casecode.mobilemovieexplorer.presentation.utils;

import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;

import lombok.Getter;

/**
 * Generic class for representing a data resource, with status and optional message.
 *
 * @param <T> The type of the data.
 */
@Getter
public class Resource<T> {

    /**
     * Tag used for logging purposes.
     */
    public static final String TAG = "Resource";

    /**
     * The actual data of the resource.
     */
    private final T data;

    /**
     * The status of the resource (SUCCESS, ERROR, LOADING, NULL).
     */
    @Getter
    public Status status;

    /**
     * Optional message associated with the resource.
     */
    @Getter
    public String message;

    /**
     * Constructor for creating a Resource instance.
     *
     * @param status   The status of the resource.
     * @param mData    The data of the resource.
     * @param mMessage Optional message associated with the resource.
     */
    public Resource(Status status, T mData, String mMessage) {
        this.status = status;
        this.data = mData;
        this.message = mMessage;
    }

    /**
     * Creates a success Resource with the provided data.
     *
     * @param data The data for the success Resource.
     * @param <T>  The type of the data.
     * @return A success Resource with the provided data.
     */
    public static <T> Resource<T> success(T data) {
        if (data.toString().equals("[]")) {
            return new Resource<>(Status.NULL, null, null);
        } else return new Resource<>(Status.SUCCESS, data, null);

    }

    /**
     * Creates an empty Resource with the provided data.
     *
     * @param data The data for the empty Resource.
     * @param <T>  The type of the data.
     * @return An empty Resource with the provided data.
     */
    public static <T> Resource<T> empty(T data) {
        return new Resource<>(Status.NULL, data, null);
    }

    /**
     * Creates an error Resource with the provided message and data.
     *
     * @param msg  The error message.
     * @param data The data associated with the error.
     * @param <T>  The type of the data.
     * @return An error Resource with the provided message and data.
     */
    public static <T> Resource<T> error(String msg, T data) {

        return new Resource<>(Status.ERROR, data, msg);
    }

    /**
     * Creates a loading Resource with no associated data.
     *
     * @param <T> The type of the data (typically Void for loading).
     * @return A loading Resource with no associated data.
     */
    public static <T> Resource<T> loading() {

        return new Resource<>(Status.LOADING, null, null);
    }

    public static<T> Resource<T> error(Throwable throwable) {
        return new Resource<>(Status.ERROR,null, throwable.getMessage());


    }
}