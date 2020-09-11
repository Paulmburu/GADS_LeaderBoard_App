package github.paulmburu.gads_leaderboard_app.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import github.paulmburu.gads_leaderboard_app.data.dto.LearningLeadersItem
import github.paulmburu.gads_leaderboard_app.data.dto.SkillLeadersItem
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

/*  
 *  Created by Paul Mburu on 7/17/20.
 */

private const val BASE_URL = "https://gadsapi.herokuapp.com/"

private val okClient = OkHttpClient.Builder()
    .connectTimeout(5, TimeUnit.MINUTES)
    .writeTimeout(5, TimeUnit.MINUTES)
    .readTimeout(5,TimeUnit.MINUTES)
    .build()

//Moshi Builder to create a Moshi object with the KotlinJsonAdapterFactory
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface ApiService {
    @GET("api/hours")
    suspend fun getLearningLeaders(): List<LearningLeadersItem>

    @GET("api/skilliq")
    suspend fun getSkillLeaders(): List<SkillLeadersItem>
}

object LeadersApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}