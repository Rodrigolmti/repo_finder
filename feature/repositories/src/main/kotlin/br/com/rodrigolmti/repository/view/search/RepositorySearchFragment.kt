package br.com.rodrigolmti.repository.view.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rodrigolmti.core_android.*
import br.com.rodrigolmti.core_android.view_binding_delegate.viewBinding
import br.com.rodrigolmti.repository.R
import br.com.rodrigolmti.repository.databinding.RepositorySearchFragmentBinding
import br.com.rodrigolmti.repository.di.RepoSearchInjector
import javax.inject.Inject

class RepositorySearchFragment : Fragment() {

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private var adapter: RepositorySearchAdapter? = null

    private val debouncer: DebounceEditText by lazy {
        DebounceEditText {
            searchRepositories(query = it)
        }
    }

    private val viewModel by viewModels<RepositoryListViewModel> {
        viewModelProviderFactory
    }

    private val binding by viewBinding {
        RepositorySearchFragmentBinding.inflate(layoutInflater)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            RepoSearchInjector.getComponent(it).inject(this@RepositorySearchFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }

    private fun setupView() {
        binding.appCompatEditText.addTextChangedListener(debouncer)
        observeChanges()
        setupAdapter()
    }

    private fun observeChanges() {
        viewModel.viewState.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                RepositoryListViewState.State.IDLE -> {
                    binding.recyclerView.show()
                    binding.progressBar.hide()
                    binding.inputView.show()
                }
                RepositoryListViewState.State.LOADING -> {
                    binding.recyclerView.hide()
                    binding.progressBar.show()
                    binding.inputView.hide()
                }
            }
        }
        viewModel.viewState.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is RepositoryListViewState.Action.LoadRepoList -> {
                    adapter?.addAll(action.repositories)
                }
                is RepositoryListViewState.Action.OnSearchError -> {
                    showSnackBar(
                        message = getString(R.string.repositories_search_error),
                        buttonMessage = getString(R.string.retry_action),
                        onClick = {
                            searchRepositories(
                                query = binding.appCompatEditText.editableText.toString()
                            )
                        }
                    )
                }
            }
        }
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        adapter = RepositorySearchAdapter(
            layoutManager = layoutManager,
            onItemClick = { repoModel ->
                val direction = RepositorySearchFragmentDirections
                    .actionToRepoDetailFragment(repoId = repoModel.id)
                findNavController().navigate(direction)
            },
            onScrollEnd = {
                searchRepositories(
                    query = binding.appCompatEditText.editableText.toString()
                )
            }
        )
        binding.recyclerView.apply {
            setLayoutManager(layoutManager)
            adapter = this@RepositorySearchFragment.adapter
            this@RepositorySearchFragment.adapter?.adapterPageHandler?.let { scroll ->
                addOnScrollListener(scroll)
            }
        }
    }

    private fun searchRepositories(query: String) {
        viewModel.dispatchViewAction(RepositoryListAction.SearchRepo(query = query))
        hideSoftInput()
    }
}
