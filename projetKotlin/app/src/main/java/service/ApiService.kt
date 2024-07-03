package service

import model.Port
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("port")
    fun getPort(): Call<List<Port>>
}