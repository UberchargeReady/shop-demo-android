package com.dyejeekis.shopdemo.data.remote;

public interface ApiCallback<T> {
    void onComplete(Result<T> result);
}
