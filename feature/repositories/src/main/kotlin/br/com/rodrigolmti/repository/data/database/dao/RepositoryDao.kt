package br.com.rodrigolmti.repository.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.rodrigolmti.repository.data.database.entity.RepositoryEntity

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRepositories(repositories: List<RepositoryEntity>): List<Long>

    @Query("SELECT * FROM repo_entity WHERE name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun getRepositoriesByQuery(query: String): List<RepositoryEntity>?

    @Query("SELECT * FROM repo_entity WHERE repoId = :id")
    fun getRepositoryById(id: Long): RepositoryEntity?
}