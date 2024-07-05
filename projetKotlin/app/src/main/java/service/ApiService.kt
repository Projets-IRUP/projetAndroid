package service

import model.Maree
import model.Port
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("port")
    fun getPort(): Call<List<Port>>


    @POST("maree1j")
    fun getMaree1j(@Body requestBody: Map<String, Int>): Call<List<Maree>>

}