package br.com.rodrigolmti.repository.view.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.rodrigolmti.core_android.base.BaseViewModel
import br.com.rodrigolmti.repository.domain.model.RepositoryModel
import br.com.rodrigolmti.repository.domain.use_case.SearchRepoByQueryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class Page {

    private var currentPage: Int = 0

    fun nextPage() = currentPage++

    fun resetPages() {
        currentPage = 0
    }
}

class RepositoryListViewState @Inject constructor() {
    val action: MutableLiveData<Action> = MutableLiveData()
    val state: MutableLiveData<State> = MutableLiveData()

    enum class State { IDLE, LOADING }

    sealed class Action {
        data class LoadRepoList(val repositories: List<RepositoryModel>) : Action()
        object OnSearchError : Action()
    }
}

sealed class RepositoryListAction {
    data class SearchRepo(val query: String) : RepositoryListAction()
}

class RepositoryListViewModel @Inject constructor(
    override val viewState: RepositoryListViewState,
    private val searchByQuery: SearchRepoByQueryUseCase
) : BaseViewModel<RepositoryListViewState, RepositoryListAction>() {

    private val repositories: MutableList<RepositoryModel> = mutableListOf()
    private var previousQuery: String = ""
    private val page = Page()

    override fun onCleared() {
        super.onCleared()
        page.resetPages()
    }

    override fun dispatchViewAction(viewAction: RepositoryListAction) {
        when (viewAction) {
            is RepositoryListAction.SearchRepo -> searchRepoByQuery(viewAction.query)
        }
    }

    private fun searchRepoByQuery(query: String) {
        viewState.state.value = RepositoryListViewState.State.LOADING

        if (previousQuery != query) {
            repositories.clear()
        }

        previousQuery = query

        viewModelScope.launch {
            runCatching {
                searchByQuery(
                    query = query,
                    page = page.nextPage()
                )
            }.onSuccess {
                repositories.addAll(it)
                viewState.action.value = RepositoryListViewState.Action.LoadRepoList(repositories)
                viewState.state.value = RepositoryListViewState.State.IDLE
            }.onFailure {
                viewState.action.value = RepositoryListViewState.Action.OnSearchError
                viewState.state.value = RepositoryListViewState.State.IDLE
            }
        }
    }
}