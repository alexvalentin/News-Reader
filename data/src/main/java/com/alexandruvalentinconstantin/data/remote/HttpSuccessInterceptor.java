package com.alexandruvalentinconstantin.data.remote;

import com.alexandruvalentinconstantin.data.remote.exception.ApiException;
import com.alexandruvalentinconstantin.data.remote.exception.ConnectivityException;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class HttpSuccessInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) {
        Request request = chain.request();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (IOException exception) {
            throw new ConnectivityException(exception.getMessage());
        }
        if (!response.isSuccessful()) {
            throw new ApiException(response.code());
        }
        return response;
    }
}