package com.itrsa.monolith.config;

import com.itrsa.monolith.interceptor.ApiHeadersInterceptor;
import com.itrsa.monolith.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class UserRetrofitConfiguration {

    @Value("${servicios.url}")
    private String wsApiUrl;

    @Value("${connectTimeout:40}")
    private long wsApiConnectTimeout;

    @Value("${readTimeout:40}")
    private long wsApiReadTimeout;

    private final ApiHeadersInterceptor apiHeadersInterceptor;

    @Bean
    public UserService getUserInterface() {
        HttpLoggingInterceptor logging
                = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        log.info("{} para el servicio de Api Personas [{}]");
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(wsApiConnectTimeout, TimeUnit.SECONDS)
                .readTimeout(wsApiReadTimeout, TimeUnit.SECONDS)
                .addInterceptor(apiHeadersInterceptor)
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(wsApiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(UserService.class);
    }
}
