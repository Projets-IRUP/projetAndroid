package service

import model.Maree
import model.Port
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("/port")
    fun getPort(): Call<List<Port>>

    @POST("/maree1j")
    fun getMaree1j(@Body requestBody: Map<String, Int>): Call<List<Maree>>

    @POST("/maree3j")
    fun getMaree3j(@Body requestBody: Map<String, Int>): Call<List<Maree>>

    @POST("/maree30j")
    fun getMaree30j(@Body requestBody: Map<String, Int>): Call<List<Maree>>
}
