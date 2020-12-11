package br.com.rodrigolmti.repository.data.data_source

import br.com.rodrigolmti.repository.data.mapper.RepoResponseToRepoModelMapper
import br.com.rodrigolmti.repository.data.service.RepositorySearchApi
import br.com.rodrigolmti.repository.factories.ModelFactory
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
internal class RepoRemoteDataSourceTest {

    private val responseMapper: RepoResponseToRepoModelMapper = mockk()
    private val repoSearchApi: RepositorySearchApi = mockk()

    private val model = ModelFactory.repoModel()

    private val dataSource = RepoRemoteDataSource(
        responseMapper,
        repoSearchApi
    )

    @Test
    fun call_searchRepoByQuery_mapResponseToModel() = runBlockingTest {
        coEvery {
            repoSearchApi.searchRepoByQuery(any(), any(), any())
        } returns ModelFactory.repoSearchResponse()
        every { responseMapper.invoke(any()) } returns model

        dataSource.getRepositoriesByQuery(query = "xfiles", page = 1)

        verify(exactly = 1) { responseMapper.invoke(any()) }
    }

    @Test
    fun call_searchRepoByQuery_callService() = runBlockingTest {
        coEvery {
            repoSearchApi.searchRepoByQuery(any(), any(), any())
        } returns ModelFactory.repoSearchResponse()
        every { responseMapper.invoke(any()) } returns model

        dataSource.getRepositoriesByQuery(query = "xfiles", page = 1)

        coVerify(exactly = 1) { repoSearchApi.searchRepoByQuery(any(), any(), any()) }
    }

    @Test
    fun call_getRepoById_mapResponseToModel() = runBlockingTest {
        coEvery { repoSearchApi.getRepoById(any()) } returns ModelFactory.repoResponse()
        every { responseMapper.invoke(any()) } returns model

        dataSource.getRepositoryById(id = 1L)

        verify(exactly = 1) { responseMapper.invoke(any()) }
    }

    @Test
    fun call_getRepoById_callService() = runBlockingTest {
        coEvery { repoSearchApi.getRepoById(any()) } returns ModelFactory.repoResponse()
        every { responseMapper.invoke(any()) } returns model

        dataSource.getRepositoryById(id = 1L)

        coVerify(exactly = 1) { repoSearchApi.getRepoById(any()) }
    }
}