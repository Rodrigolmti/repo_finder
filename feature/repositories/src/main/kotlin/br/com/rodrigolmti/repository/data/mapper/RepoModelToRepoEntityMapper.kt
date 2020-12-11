package br.com.rodrigolmti.repository.data.mapper

import br.com.rodrigolmti.kotlin_utils.Mapper
import br.com.rodrigolmti.repository.data.database.entity.RepositoryEntity
import br.com.rodrigolmti.repository.data.database.entity.RepositoryOwnerEntity
import br.com.rodrigolmti.repository.domain.model.RepositoryModel
import javax.inject.Inject

class RepoModelToRepoEntityMapper @Inject constructor() : Mapper<RepositoryModel, RepositoryEntity> {

    override fun invoke(from: RepositoryModel) = RepositoryEntity(
        repoId = from.id,
        name = from.name,
        private = from.private,
        owner = RepositoryOwnerEntity(
            ownerId = from.owner.id,
            avatarUrl = from.owner.avatarUrl,
            ownerUrl = from.owner.url,
            login = from.owner.login,
            type = from.owner.type,
        ),
        description = from.description,
        repoUrl = from.url,
        createdAt = from.createdAt,
        lastUpdatedAt = from.lastUpdatedAt,
        stars = from.stars,
        watchersCount = from.watchersCount,
        score = from.score,
        defaultBranch = from.defaultBranch,
    )
}