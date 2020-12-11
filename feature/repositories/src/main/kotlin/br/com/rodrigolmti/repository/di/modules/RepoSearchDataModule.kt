package br.com.rodrigolmti.repository.di.modules

import android.app.Application
import br.com.rodrigolmti.repository.data.data_source.IRepoLocalDataSource
import br.com.rodrigolmti.repository.data.data_source.IRepoRemoteDataSource
import br.com.rodrigolmti.repository.data.data_source.RepoLocalDataSource
import br.com.rodrigolmti.repository.data.data_source.RepoRemoteDataSource
import br.com.rodrigolmti.repository.data.database.RepoDatabase
import br.com.rodrigolmti.repository.data.database.dao.RepositoryDao
import br.com.rodrigolmti.repository.data.service.RepositorySearchApi
import br.com.rodrigolmti.repository.di.RepoSearchScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
interface RepoSearchDataModule {

    @[Binds Reusable]
    fun bindsRepoRemoteDataSource(
        dataSource: RepoRemoteDataSource
    ): IRepoRemoteDataSource

    @[Binds Reusable]
    fun bindsIRepoLocalDataSource(
        dataSource: RepoLocalDataSource
    ): IRepoLocalDataSource

    companion object {

        @[JvmStatic Provides RepoSearchScope]
        fun provideRepoDatabase(application: Application): RepoDatabase =
            RepoDatabase.createDatabase(application.baseContext)

        @[JvmStatic Provides RepoSearchScope]
        fun provideRepoDao(database: RepoDatabase): RepositoryDao = database.repoDao()

        @[JvmStatic Provides Reusable]
        fun providesRepoSearchApi(retrofit: Retrofit): RepositorySearchApi =
            retrofit.create(RepositorySearchApi::class.java)
    }
}