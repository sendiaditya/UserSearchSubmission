package com.example.usersearchsubmission.data.retrofit

import com.example.usersearchsubmission.data.response.DetailUserResponse
import com.example.usersearchsubmission.data.response.GithubResponse
import com.example.usersearchsubmission.data.response.UserItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUsers(
        @Query("q") username: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(@Path("username") username: String
    ): Call<List<UserItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(@Path("username") username: String
    ): Call<List<UserItem>>
}