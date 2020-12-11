package br.com.rodrigolmti.repository.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.rodrigolmti.repository.data.database.dao.RepositoryDao
import br.com.rodrigolmti.repository.data.database.entity.RepositoryEntity

private const val ROOM_VERSION = 1
private const val DATABASE_NAME = "REPO_DB"

@Database(
    entities = [
        RepositoryEntity::class,
    ],
    version = ROOM_VERSION
)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun repoDao(): RepositoryDao

    companion object {
        fun createDatabase(context: Context): RepoDatabase =
            Room.databaseBuilder(
                context,
                RepoDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
    }
}