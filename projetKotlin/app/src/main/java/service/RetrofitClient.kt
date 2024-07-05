package service

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:5000/"
    @RequiresApi(Build.VERSION_CODES.O)
    private val gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, JsonDeserializer { json, _, _ ->
            LocalDateTime.parse(json.asString, DateTimeFormatter.RFC_1123_DATE_TIME)
        })
        .create()

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}

