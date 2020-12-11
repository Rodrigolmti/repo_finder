package br.com.rodrigolmti.repository.domain.model

data class RepositoryModel(
    val id: Long,
    val name: String,
    val private: Boolean = false,
    val owner: RepositoryOwnerModel,
    val description: String?,
    val url: String,
    val createdAt: String?,
    val lastUpdatedAt: String?,
    val stars: Int = 0,
    val watchersCount: Int = 0,
    val score: Int = 0,
    val defaultBranch: String?
)

data class RepositoryOwnerModel(
    val id: Long,
    val login: String,
    val avatarUrl: String?,
    val url: String?,
    val type: String
)