package com.example.moviemvvm.api

import com.example.moviemvvm.vlo.MainMovieModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieInterface {
    //https://api.themoviedb.org/3/movie/top_rated?api_key=05838070772e7aac6cab92fabcfa19bf&language=en-US&page=1

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id:Int):Single<MainMovieModel>
}