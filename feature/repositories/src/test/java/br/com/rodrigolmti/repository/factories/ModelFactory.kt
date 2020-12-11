package br.com.rodrigolmti.repository.factories

import br.com.rodrigolmti.repository.data.database.entity.RepositoryEntity
import br.com.rodrigolmti.repository.data.database.entity.RepositoryOwnerEntity
import br.com.rodrigolmti.repository.data.service.response.RepositoryOwnerResponse
import br.com.rodrigolmti.repository.data.service.response.RepositoryResponse
import br.com.rodrigolmti.repository.data.service.response.SearchRepositoryResponse
import br.com.rodrigolmti.repository.domain.model.RepositoryModel
import br.com.rodrigolmti.repository.domain.model.RepositoryOwnerModel

object ModelFactory {

    fun repoModel() = RepositoryModel(
        id = 1L,
        name = "Fox Mulder",
        private = false,
        owner = RepositoryOwnerModel(
            id = 1L,
            avatarUrl = "www.google.com/xfiles/foxmulder.png",
            login = "mulderfox",
            url = "www.xfiles.com",
            type = "fiction"
        ),
        description = "X files is the best tv show",
        url = "www.xfiles.com",
        createdAt = "10/10/1991",
        lastUpdatedAt = "10/10/2017",
        stars = 100,
        watchersCount = 1000,
        score = 10,
        defaultBranch = "Season 1"
    )

    fun repoEntity() = RepositoryEntity(
        repoId = 1L,
        name = "Fox Mulder",
        private = false,
        owner = RepositoryOwnerEntity(
            ownerId = 1L,
            avatarUrl = "www.google.com/xfiles/foxmulder.png",
            login = "mulderfox",
            ownerUrl = "www.xfiles.com",
            type = "fiction"
        ),
        description = "X files is the best tv show",
        repoUrl = "www.xfiles.com",
        createdAt = "10/10/1991",
        lastUpdatedAt = "10/10/2017",
        stars = 100,
        watchersCount = 1000,
        score = 10,
        defaultBranch = "Season 1"
    )

    fun repoSearchResponse() = SearchRepositoryResponse(
        listOf(
            repoResponse()
        )
    )

    fun repoResponse() = RepositoryResponse(
        id = 1L,
        name = "Fox Mulder",
        private = false,
        owner = RepositoryOwnerResponse(
            id = 1L,
            avatarUrl = "www.google.com/xfiles/foxmulder.png",
            login = "mulderfox",
            url = "www.xfiles.com",
            type = "fiction"
        ),
        description = "X files is the best tv show",
        url = "www.xfiles.com",
        createdAt = "10/10/1991",
        lastUpdatedAt = "10/10/2017",
        stars = 100,
        watchersCount = 1000,
        score = 10,
        defaultBranch = "Season 1"
    )
}