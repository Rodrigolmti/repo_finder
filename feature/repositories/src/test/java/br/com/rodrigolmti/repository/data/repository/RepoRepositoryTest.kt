package br.com.rodrigolmti.repository.data.repository

import br.com.rodrigolmti.core_android.test.CoroutinesTestRule
import br.com.rodrigolmti.kotlin_utils.IConnectionChecker
import br.com.rodrigolmti.repository.data.data_source.IRepoLocalDataSource
import br.com.rodrigolmti.repository.data.data_source.IRepoRemoteDataSource
import br.com.rodrigolmti.repository.factories.ModelFactory
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class RepoRepositoryTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val remoteDataSource: IRepoRemoteDataSource = mockk()
    private val localDataSource: IRepoLocalDataSource = mockk()
    private val connectionChecker: IConnectionChecker = mockk()

    private val repository = RepoRepository(
        remoteDataSource,
        localDataSource,
        connectionChecker,
        coroutinesTestRule.testDispatcherProvider,
    )

    @Test
    fun call_searchRepoByQuery_withNoConnection_callDb() = runBlockingTest {
        coEvery {
            localDataSource.getRepositoriesByQuery(any())
        } returns listOf(ModelFactory.repoModel())
        every { connectionChecker.isDeviceConnected() } returns false

        repository.getRepositoriesByQuery(query = "xfiles", page = 1)

        coVerify(exactly = 1) { localDataSource.getRepositoriesByQuery(any()) }
    }

    @Test
    fun call_searchRepoByQuery_withConnection_callRemote() = runBlockingTest {
        coEvery {
            remoteDataSource.getRepositoriesByQuery(any(), page = any())
        } returns listOf()
        every { connectionChecker.isDeviceConnected() } returns true

        repository.getRepositoriesByQuery(query = "xfiles", page = 1)

        coVerify(exactly = 1) { remoteDataSource.getRepositoriesByQuery(any(), page = any()) }
        coVerify(exactly = 0) { localDataSource.saveRepositories(any()) }
    }

    @Test
    fun call_searchRepoByQuery_withConnection_saveResultOnDb() = runBlockingTest {
        coEvery {
            remoteDataSource.getRepositoriesByQuery(any(), page = any())
        } returns listOf(ModelFactory.repoModel())
        coEvery { localDataSource.saveRepositories(any()) } just runs
        every { connectionChecker.isDeviceConnected() } returns true

        repository.getRepositoriesByQuery(query = "xfiles", page = 1)

        coVerify(exactly = 1) { localDataSource.saveRepositories(any()) }
    }

    @Test
    fun call_getRepoById_withNoConnection_callDb() = runBlockingTest {
        coEvery {
            localDataSource.getRepositoryById(any())
        } returns ModelFactory.repoModel()
        every { connectionChecker.isDeviceConnected() } returns false

        repository.getRepositoryById(id = 1L)

        coVerify(exactly = 1) { localDataSource.getRepositoryById(any()) }
    }

    @Test
    fun call_getRepoById_withConnection_callDb() = runBlockingTest {
        coEvery {
            remoteDataSource.getRepositoryById(any())
        } returns ModelFactory.repoModel()
        every { connectionChecker.isDeviceConnected() } returns true

        repository.getRepositoryById(id = 1L)

        coVerify(exactly = 1) { remoteDataSource.getRepositoryById(any()) }
    }
}