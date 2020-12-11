package br.com.rodrigolmti.repository.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_entity")
data class RepositoryEntity(
    @PrimaryKey @ColumnInfo(name = "repoId") var repoId: Long = 0,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "private") var private: Boolean = false,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "repoUrl") var repoUrl: String = "",
    @ColumnInfo(name = "createdAt") var createdAt: String? = null,
    @ColumnInfo(name = "lastUpdatedAt") var lastUpdatedAt: String? = null,
    @ColumnInfo(name = "stars") var stars: Int = 0,
    @ColumnInfo(name = "watchersCount") var watchersCount: Int = 0,
    @ColumnInfo(name = "score") var score: Int = 0,
    @ColumnInfo(name = "defaultBranch") var defaultBranch: String? = null,
    @Embedded var owner: RepositoryOwnerEntity = RepositoryOwnerEntity(),
)

data class RepositoryOwnerEntity(
    @ColumnInfo(name = "ownerId") var ownerId: Long = 0,
    @ColumnInfo(name = "login") var login: String = "",
    @ColumnInfo(name = "avatarUrl") var avatarUrl: String? = null,
    @ColumnInfo(name = "ownerUrl") var ownerUrl: String? = null,
    @ColumnInfo(name = "type") var type: String = ""
)