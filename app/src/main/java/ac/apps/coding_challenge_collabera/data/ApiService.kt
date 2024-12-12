package ac.apps.coding_challenge_collabera.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private const val BASE_URL = "https://randomuser.me/api/"

//    val gson = GsonBuilder().setLenient().create()

    // apply interceptor for debugging
    // checks the json if correct format
//    val logging = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }
//
//    val client = OkHttpClient.Builder()
//        .addInterceptor(logging)
//        .build()


    private fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val personApi: PersonApi by lazy {  provideRetrofit().create(PersonApi::class.java) }

}