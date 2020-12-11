package br.com.rodrigolmti.repository.domain.use_case

import br.com.rodrigolmti.repository.domain.repository.IRepoRepository
import br.com.rodrigolmti.repository.factories.ModelFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
internal class SearchRepoByQueryTest {

    private val repository: IRepoRepository = mockk()

    private val useCase = SearchRepoByQuery(repository)

    @Test
    fun onCall_callRepository() = runBlockingTest {
        coEvery {
            repository.getRepositoriesByQuery(query = any(), page = any())
        } returns listOf(ModelFactory.repoModel())
        val query = "xfiles"

        useCase(query = query, page = 1)

        coVerify(exactly = 1) { repository.getRepositoriesByQuery(query = query, page = any()) }
    }
}