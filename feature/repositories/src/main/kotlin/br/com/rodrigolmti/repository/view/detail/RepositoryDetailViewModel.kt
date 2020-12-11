package br.com.rodrigolmti.repository.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.rodrigolmti.core_android.SingleLiveEvent
import br.com.rodrigolmti.core_android.base.BaseViewModel
import br.com.rodrigolmti.repository.domain.model.RepositoryModel
import br.com.rodrigolmti.repository.domain.use_case.GetRepoByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoryDetailViewState @Inject constructor() {
    val action: SingleLiveEvent<Action> = SingleLiveEvent()
    val state: MutableLiveData<State> = MutableLiveData()

    enum class State { IDLE, LOADING }

    sealed class Action {
        data class LoadRepoDetail(val repo: RepositoryModel) : Action()
        object OnError : Action()
    }
}

sealed class RepositoryDetailAction {
    data class GetRepoById(val id: Long) : RepositoryDetailAction()
}

class RepositoryDetailViewModel @Inject constructor(
    override val viewState: RepositoryDetailViewState,
    private val getRepoById: GetRepoByIdUseCase,
) : BaseViewModel<RepositoryDetailViewState, RepositoryDetailAction>() {

    override fun dispatchViewAction(viewAction: RepositoryDetailAction) {
        when (viewAction) {
            is RepositoryDetailAction.GetRepoById -> {
                searchRepoByQuery(viewAction.id)
            }
        }
    }

    private fun searchRepoByQuery(id: Long) {
        viewState.state.value = RepositoryDetailViewState.State.LOADING
        viewModelScope.launch {
            runCatching {
                getRepoById(id = id)
            }.onSuccess { repo ->
                repo?.let {
                    viewState.action.value = RepositoryDetailViewState.Action.LoadRepoDetail(it)
                } ?: run {
                    viewState.action.value = RepositoryDetailViewState.Action.OnError
                }
                viewState.state.value = RepositoryDetailViewState.State.IDLE
            }.onFailure {
                viewState.action.value = RepositoryDetailViewState.Action.OnError
                viewState.state.value = RepositoryDetailViewState.State.IDLE
            }
        }
    }
}