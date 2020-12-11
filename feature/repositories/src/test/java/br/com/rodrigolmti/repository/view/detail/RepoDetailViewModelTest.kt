package br.com.rodrigolmti.repository.view.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.rodrigolmti.core_android.test.AssertStates
import br.com.rodrigolmti.core_android.test.CoroutinesTestRule
import br.com.rodrigolmti.repository.domain.model.RepositoryModel
import br.com.rodrigolmti.repository.domain.use_case.GetRepoByIdUseCase
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
internal class RepoDetailViewModelTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val viewState = spyk(RepositoryDetailViewState())
    private val getRepoById: GetRepoByIdUseCase = mockk()

    private val model = ModelFactory.repoModel()

    private val viewModel = RepositoryDetailViewModel(
        viewState,
        getRepoById,
    )

    @Test
    fun call_withSuccess_sendStateLoading_and_Idle() = coroutinesTestRule.testDispatcher.runBlockingTest {
        prepareScenario(success = true)
        val states = AssertStates<RepositoryDetailViewState.State>()
        states.observeForever(viewModel.viewState.state)

        viewModel.dispatchViewAction(RepositoryDetailAction.GetRepoById(1L))

        states.assertStates(
            RepositoryDetailViewState.State.LOADING,
            RepositoryDetailViewState.State.IDLE
        )
    }

    @Test
    fun call_withSuccess_sendLoadRepoAction() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val action = RepositoryDetailViewState.Action.LoadRepoDetail(model)
        prepareScenario(success = true)

        viewModel.dispatchViewAction(RepositoryDetailAction.GetRepoById(1L))

        assertEquals(action, viewModel.viewState.action.value)
    }

    @Test
    fun call_withSuccess_and_NullModel_sendErrorAction() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val action = RepositoryDetailViewState.Action.OnError
        prepareScenario(success = true, model = null)

        viewModel.dispatchViewAction(RepositoryDetailAction.GetRepoById(1L))

        assertEquals(action, viewModel.viewState.action.value)
    }

    @Test
    fun call_withError_sendStateLoading_and_Idle() = coroutinesTestRule.testDispatcher.runBlockingTest {
        prepareScenario(success = false)
        val states = AssertStates<RepositoryDetailViewState.State>()
        states.observeForever(viewModel.viewState.state)

        viewModel.dispatchViewAction(RepositoryDetailAction.GetRepoById(1L))

        states.assertStates(
            RepositoryDetailViewState.State.LOADING,
            RepositoryDetailViewState.State.IDLE
        )
    }

    @Test
    fun call_withError_sendErrorAction() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val action = RepositoryDetailViewState.Action.OnError
        prepareScenario(success = false)

        viewModel.dispatchViewAction(RepositoryDetailAction.GetRepoById(1L))

        assertEquals(action, viewModel.viewState.action.value)
    }

    private fun prepareScenario(
        success: Boolean,
        model: RepositoryModel? = this.model,
    ) {
        if (success) {
            coEvery { getRepoById(id = any()) } returns model
        } else {
            coEvery { getRepoById(id = any()) } throws Exception()
        }
    }
}