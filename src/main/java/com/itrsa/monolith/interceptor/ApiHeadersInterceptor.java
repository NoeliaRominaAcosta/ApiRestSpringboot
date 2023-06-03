package com.itrsa.monolith.interceptor;

import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiHeadersInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        var originalRequest = chain.request();
        var requestBuilder = originalRequest.newBuilder();
        var newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }
}
