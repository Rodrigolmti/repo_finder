package br.com.rodrigolmti.repository.data.service.response

import com.squareup.moshi.Json

data class SearchRepositoryResponse(
    @Json(name = "items")
    val items: List<RepositoryResponse>
)

data class RepositoryResponse(
    @Json(name = "id")
    val id: Long,
    @Json(name = "full_name")
    val name: String,
    @Json(name = "private")
    val private: Boolean = false,
    @Json(name = "owner")
    val owner: RepositoryOwnerResponse,
    @Json(name = "description")
    val description: String?,
    @Json(name = "url")
    val url: String,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "updated_at")
    val lastUpdatedAt: String?,
    @Json(name = "stargazers_count")
    val stars: Int = 0,
    @Json(name = "watchers_count")
    val watchersCount: Int = 0,
    @Json(name = "score")
    val score: Int = 0,
    @Json(name = "default_branch")
    val defaultBranch: String?
)

data class RepositoryOwnerResponse(
    @Json(name = "id")
    val id: Long,
    @Json(name = "login")
    val login: String,
    @Json(name = "avatar_url")
    val avatarUrl: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "type")
    val type: String
)