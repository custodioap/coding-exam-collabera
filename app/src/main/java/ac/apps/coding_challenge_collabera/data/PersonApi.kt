package ac.apps.coding_challenge_collabera.data

import ac.apps.coding_challenge_collabera.data.datasource.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonApi {
    @GET("/api")
    suspend fun getPerson(
        @Query("results") results: Int,
        @Header("Accept") accept: String = "application/json"
    ): Response<ApiResponse>
}
