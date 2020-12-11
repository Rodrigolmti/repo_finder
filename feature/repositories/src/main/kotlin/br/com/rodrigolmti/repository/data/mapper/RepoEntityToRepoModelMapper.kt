package br.com.rodrigolmti.repository.data.mapper

import br.com.rodrigolmti.kotlin_utils.Mapper
import br.com.rodrigolmti.repository.data.database.entity.RepositoryEntity
import br.com.rodrigolmti.repository.domain.model.RepositoryModel
import br.com.rodrigolmti.repository.domain.model.RepositoryOwnerModel
import javax.inject.Inject

class RepoEntityToRepoModelMapper @Inject constructor() : Mapper<RepositoryEntity, RepositoryModel> {

    override fun invoke(from: RepositoryEntity) = RepositoryModel(
        id = from.repoId,
        name = from.name,
        private = from.private,
        owner = RepositoryOwnerModel(
            id = from.owner.ownerId,
            login = from.owner.login,
            avatarUrl = from.owner.avatarUrl,
            url = from.owner.ownerUrl,
            type = from.owner.type,
        ),
        description = from.description,
        url = from.repoUrl,
        createdAt = from.createdAt,
        lastUpdatedAt = from.lastUpdatedAt,
        stars = from.stars,
        watchersCount = from.watchersCount,
        score = from.score,
        defaultBranch = from.defaultBranch,
    )
}