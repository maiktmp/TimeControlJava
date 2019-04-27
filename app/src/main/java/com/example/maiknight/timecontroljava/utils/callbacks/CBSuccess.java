package com.example.maiknight.timecontroljava.utils.callbacks;

/**
 * Created by TEA 2 on 3/20/2018.
 */

public interface CBSuccess<T> {
    void onResponse(boolean success, T result);
}
