package com.droidbyme.toastlib;

/**
 * Created by Droidbyme on 25-08-2016.
 */
public enum  ToastEnum {

    SHORT(1), LONG(2);

    ToastEnum(int id) {
    }

    private int id;

    public int getId() {
        return id;
    }
}