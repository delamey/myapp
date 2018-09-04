package com.example.delamey.myapplication5.constant;

import com.example.delamey.myapplication5.bean.Result;
import com.example.delamey.myapplication5.bean.User;
import com.example.delamey.myapplication5.bean.bitmapbean;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface api {

        @GET("users/{user}/repos")
        Call<List<Result>> listRepos(@Path("user") String user);
        @GET("{json}")
        io.reactivex.Observable<List<Result<User>>> getjson(@Path("json") String user);

        @GET("DQ2SSNG900AN0001NOS.jpg")
        Observable <String> getbitmap();
}
