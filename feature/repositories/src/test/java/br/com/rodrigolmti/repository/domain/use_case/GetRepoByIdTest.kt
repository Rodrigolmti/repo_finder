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
internal class GetRepoByIdTest {

    private val repository: IRepoRepository = mockk()

    private val useCase = GetRepoById(repository)

    @Test
    fun onCall_callRepository() = runBlockingTest {
        coEvery { repository.getRepositoryById(id = any()) } returns ModelFactory.repoModel()
        val id = 1L

        useCase(id = id)

        coVerify(exactly = 1) { repository.getRepositoryById(id = id) }
    }
}