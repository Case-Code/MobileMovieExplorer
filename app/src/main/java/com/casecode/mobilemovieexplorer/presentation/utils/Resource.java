package com.casecode.mobilemovieexplorer.presentation.utils;

import lombok.Getter;

@Getter
public class Resource<T> {
    public static final String TAG = "Resource";
    private final T data;
    @Getter
    public Status status;

    @Getter
    public String message;


    public Resource(Status status,T mData, String mMessage) {
        this.status = status;
        this.data = mData;
        this.message = mMessage;
    }


    public static <T> Resource<T> success(T data) {
        if (data.toString().equals("[]")) {
            return new Resource<>(Status.NULL, null, null);
        } else return new Resource<>(Status.SUCCESS, data, null);

    }

    public static <T> Resource<T> empty(T data) {

        return new Resource<>(Status.NULL, data, null);    }

    public static <T> Resource<T> error(String msg, T data) {

        return new Resource<>(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> loading() {

        return new Resource<>(Status.LOADING, null, null);
    }
}