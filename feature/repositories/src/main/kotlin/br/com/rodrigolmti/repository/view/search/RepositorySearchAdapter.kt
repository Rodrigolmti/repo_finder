package br.com.rodrigolmti.repository.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigolmti.core_android.formatDate
import br.com.rodrigolmti.repository.databinding.RepositoryItemBinding
import br.com.rodrigolmti.repository.domain.model.RepositoryModel

const val PAGE_SIZE = 20

internal class RepositorySearchAdapter(
    private val onItemClick: (item: RepositoryModel) -> Unit,
    private val onScrollEnd: () -> Unit,
    layoutManager: LinearLayoutManager,
) : RecyclerView.Adapter<RepositorySearchAdapter.ViewHolder>() {

    val adapterPageHandler = SearchAdapterPageHandler(layoutManager)
    private var repositories = mutableListOf<RepositoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(RepositoryItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindData(repositories[position])

    override fun getItemCount() = repositories.size

    inner class ViewHolder(val view: RepositoryItemBinding) : RecyclerView.ViewHolder(view.root) {
        fun bindData(item: RepositoryModel) {
            view.textViewCreatedAt.text = item.createdAt?.formatDate()
            view.textViewDescription.text = item.description
            view.textViewStars.text = item.stars.toString()
            view.textViewName.text = item.name
            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    inner class SearchAdapterPageHandler(
        private val layoutManager: LinearLayoutManager,
    ) : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
                firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE
            ) {
                onScrollEnd()
            }
        }
    }

    fun addAll(list: List<RepositoryModel>) {
        repositories.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }
}