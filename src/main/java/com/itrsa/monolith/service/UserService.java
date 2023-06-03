package com.itrsa.monolith.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

import com.itrsa.monolith.entity.User;

public interface UserService {

    @GET("/users")
    public Call<List<User>> getUsers(@Query("perPage") int perPage, @Query("page") int page);

    @GET("/users/{username}")
    public Call<User> getUser(@Path("username") String username);

}
