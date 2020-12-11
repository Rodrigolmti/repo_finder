package br.com.rodrigolmti.repository.data.service

import br.com.rodrigolmti.repository.data.service.response.RepositoryResponse
import br.com.rodrigolmti.repository.data.service.response.SearchRepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val PER_PAGE_RESULTS = 20

interface RepositorySearchApi {

    @GET("search/repositories")
    suspend fun searchRepoByQuery(
        @Query("q") query: String,
        @Query("per_page") perPage: Int = PER_PAGE_RESULTS,
        @Query("page") page: Int,
    ): SearchRepositoryResponse

    @GET("repositories/{id}")
    suspend fun getRepoById(@Path("id") id: Long): RepositoryResponse
}