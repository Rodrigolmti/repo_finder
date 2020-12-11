package br.com.rodrigolmti.repository.view.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import br.com.rodrigolmti.core_android.hide
import br.com.rodrigolmti.core_android.show
import br.com.rodrigolmti.core_android.showSnackBar
import br.com.rodrigolmti.core_android.view_binding_delegate.viewBinding
import br.com.rodrigolmti.repository.R
import br.com.rodrigolmti.repository.databinding.RepositoryDetailFragmentBinding
import br.com.rodrigolmti.repository.di.RepoSearchInjector
import coil.load
import coil.transform.CircleCropTransformation
import javax.inject.Inject

class RepositoryDetailFragment : Fragment() {

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val args by navArgs<RepositoryDetailFragmentArgs>()

    private val viewModel by viewModels<RepositoryDetailViewModel> {
        viewModelProviderFactory
    }

    private val binding by viewBinding {
        RepositoryDetailFragmentBinding.inflate(layoutInflater)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            RepoSearchInjector.getComponent(it).inject(this@RepositoryDetailFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dispatchViewAction(RepositoryDetailAction.GetRepoById(args.repoId))
        setupView()
    }

    private fun setupView() {
        (activity as? AppCompatActivity)?.let {
            it.setSupportActionBar(binding.toolBar)
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            it.supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        observeChanges()
    }

    private fun observeChanges() {
        viewModel.viewState.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                RepositoryDetailViewState.State.IDLE -> {
                    binding.content.show()
                    binding.progressBar.hide()
                    binding.toolBar.show()
                }
                RepositoryDetailViewState.State.LOADING -> {
                    binding.content.hide()
                    binding.progressBar.show()
                    binding.toolBar.hide()
                }
            }
        }
        viewModel.viewState.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is RepositoryDetailViewState.Action.LoadRepoDetail -> {
                    setupRepoDetail(action)
                }
                is RepositoryDetailViewState.Action.OnError -> {
                    showSnackBar(
                        message = getString(R.string.repositories_search_error),
                        buttonMessage = getString(R.string.retry_action),
                        onClick = {
                            viewModel.dispatchViewAction(
                                RepositoryDetailAction.GetRepoById(args.repoId)
                            )
                        }
                    )
                }
            }
        }
    }

    private fun setupRepoDetail(action: RepositoryDetailViewState.Action.LoadRepoDetail) {
        binding.textViewWatches.text = action.repo.watchersCount.toString()
        binding.textViewDescription.text = action.repo.description
        binding.textViewStars.text = action.repo.stars.toString()
        binding.textViewOwnerType.text = action.repo.owner.type
        binding.textViewOwner.text = action.repo.owner.login
        binding.toolBar.title = action.repo.name

        val fallbackImage = R.drawable.ic_baseline_broken_image

        binding.appCompatImageViewAvatar.load(action.repo.owner.avatarUrl) {
            transformations(CircleCropTransformation())
            placeholder(fallbackImage)
            fallback(fallbackImage)
            crossfade(true)
            error(fallbackImage)
        }

        context?.let {
            if (action.repo.private) {
                binding.appCompatImagePrivate.setImageDrawable(
                    ContextCompat.getDrawable(it, R.drawable.ic_baseline_lock)
                )
            } else {
                binding.appCompatImagePrivate.setImageDrawable(
                    ContextCompat.getDrawable(it, R.drawable.ic_baseline_lock_open)
                )
            }
        }
    }
}
