package br.com.rodrigolmti.repository.view.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.rodrigolmti.core_android.test.AssertStates
import br.com.rodrigolmti.core_android.test.CoroutinesTestRule
import br.com.rodrigolmti.repository.domain.use_case.SearchRepoByQueryUseCase
import br.com.rodrigolmti.repository.factories.ModelFactory
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
internal class RepoListViewStateTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val viewState = spyk(RepositoryListViewState())
    private val searchByQuery: SearchRepoByQueryUseCase = mockk()

    private val models = listOf(
        ModelFactory.repoModel(),
        ModelFactory.repoModel(),
    )

    private val viewModel = RepositoryListViewModel(
        viewState,
        searchByQuery
    )

    @Test
    fun call_withSuccess_sendStateLoading_and_Idle() = coroutinesTestRule.testDispatcher.runBlockingTest {
        prepareScenario(success = true)
        val states = AssertStates<RepositoryListViewState.State>()
        states.observeForever(viewModel.viewState.state)

        viewModel.dispatchViewAction(RepositoryListAction.SearchRepo(query = "xfiles"))

        states.assertStates(
            RepositoryListViewState.State.LOADING,
            RepositoryListViewState.State.IDLE
        )
    }

    @Test
    fun call_withSuccess_sendLoadRepoListAction() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val action = RepositoryListViewState.Action.LoadRepoList(models)
        prepareScenario(success = true)

        viewModel.dispatchViewAction(RepositoryListAction.SearchRepo(query = "xfiles"))

        assertEquals(action, viewModel.viewState.action.value)
    }

    @Test
    fun call_withError_sendStateLoading_and_Idle() = coroutinesTestRule.testDispatcher.runBlockingTest {
        prepareScenario(success = false)
        val states = AssertStates<RepositoryListViewState.State>()
        states.observeForever(viewModel.viewState.state)

        viewModel.dispatchViewAction(RepositoryListAction.SearchRepo(query = "xfiles"))

        states.assertStates(
            RepositoryListViewState.State.LOADING,
            RepositoryListViewState.State.IDLE
        )
    }

    @Test
    fun call_withError_sendErrorAction() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val action = RepositoryListViewState.Action.OnSearchError
        prepareScenario(success = false)

        viewModel.dispatchViewAction(RepositoryListAction.SearchRepo(query = "xfiles"))

        assertEquals(action, viewModel.viewState.action.value)
    }

    private fun prepareScenario(success: Boolean) {
        if (success) {
            coEvery { searchByQuery(query = any(), page = any()) } returns models
        } else {
            coEvery { searchByQuery(query = any(), page = any()) } throws Exception()
        }
    }
}