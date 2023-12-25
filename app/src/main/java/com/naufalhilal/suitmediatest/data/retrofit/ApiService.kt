package com.naufalhilal.suitmediatest.data.retrofit

import com.naufalhilal.suitmediatest.data.response.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ):UsersResponse
}