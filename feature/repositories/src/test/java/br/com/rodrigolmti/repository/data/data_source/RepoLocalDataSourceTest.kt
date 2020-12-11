package br.com.rodrigolmti.repository.data.data_source

import br.com.rodrigolmti.repository.data.database.dao.RepositoryDao
import br.com.rodrigolmti.repository.data.mapper.RepoEntityToRepoModelMapper
import br.com.rodrigolmti.repository.data.mapper.RepoModelToRepoEntityMapper
import br.com.rodrigolmti.repository.factories.ModelFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
internal class RepoLocalDataSourceTest {

    private val entityMapper: RepoEntityToRepoModelMapper = mockk()
    private val modelMapper: RepoModelToRepoEntityMapper = mockk()
    private val repoDao: RepositoryDao = mockk()

    private val model = ModelFactory.repoModel()
    private val entity = ModelFactory.repoEntity()

    private val dataSource = RepoLocalDataSource(
        entityMapper,
        modelMapper,
        repoDao
    )

    @Test
    fun call_saveRepositories_mapModelsToEntity() = runBlockingTest {
        every { repoDao.saveRepositories(any()) } returns emptyList()
        every { modelMapper.invoke(model) } returns entity
        val models = listOf(model)

        dataSource.saveRepositories(models)

        verify(exactly = 1) { modelMapper.invoke(model) }
    }

    @Test
    fun call_saveRepositories_callDao() = runBlockingTest {
        every { repoDao.saveRepositories(any()) } returns emptyList()
        every { modelMapper.invoke(model) } returns entity
        val models = listOf(model)

        dataSource.saveRepositories(models)

        verify(exactly = 1) { repoDao.saveRepositories(any()) }
    }

    @Test
    fun call_getById_mapEntityToModel() = runBlockingTest {
        every { repoDao.getRepositoryById(any()) } returns entity
        every { entityMapper.invoke(entity) } returns model

        dataSource.getRepositoryById(1L)

        verify(exactly = 1) { entityMapper.invoke(entity) }
    }

    @Test
    fun call_getById_callDao() = runBlockingTest {
        every { repoDao.getRepositoryById(any()) } returns entity
        every { entityMapper.invoke(entity) } returns model

        dataSource.getRepositoryById(1L)

        verify(exactly = 1) { repoDao.getRepositoryById(1L) }
    }

    @Test
    fun call_getRepositories_mapEntityToModel() = runBlockingTest {
        every { repoDao.getRepositoriesByQuery(any()) } returns listOf(entity)
        every { entityMapper.invoke(entity) } returns model

        dataSource.getRepositoriesByQuery(query = "xfiles")

        verify(exactly = 1) { entityMapper.invoke(entity) }
    }

    @Test
    fun call_getRepositories_callDao() = runBlockingTest {
        every { repoDao.getRepositoriesByQuery(any()) } returns listOf(entity)
        every { entityMapper.invoke(entity) } returns model

        dataSource.getRepositoriesByQuery(query = "xfiles")

        verify(exactly = 1) { repoDao.getRepositoriesByQuery(any()) }
    }

    @Test
    fun call_getRepositories_returnEmptyList_when_emptyDb() = runBlockingTest {
        every { entityMapper.invoke(entity) } returns model
        every { repoDao.getRepositoriesByQuery(any()) } returns null

        val actual = dataSource.getRepositoriesByQuery(query = "xfiles")

        assertTrue(actual.isEmpty())
    }
}