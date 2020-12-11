package br.com.rodrigolmti.repository.data.mapper

import br.com.rodrigolmti.repository.factories.ModelFactory
import org.junit.Test
import kotlin.test.assertEquals

internal class RepoEntityToRepoModelMapperTest {

    private val mapper = RepoEntityToRepoModelMapper()

    @Test
    fun call_returnObjectWithSameValues() {
        val entity = ModelFactory.repoEntity()

        val actual = mapper(entity)

        assertEquals(entity.repoId, actual.id)
        assertEquals(entity.name, actual.name)
        assertEquals(entity.private, actual.private)
        assertEquals(entity.description, actual.description)
        assertEquals(entity.repoUrl, actual.url)
        assertEquals(entity.createdAt, actual.createdAt)
        assertEquals(entity.lastUpdatedAt, actual.lastUpdatedAt)
        assertEquals(entity.stars, actual.stars)
        assertEquals(entity.watchersCount, actual.watchersCount)
        assertEquals(entity.score, actual.score)
        assertEquals(entity.defaultBranch, actual.defaultBranch)
        assertEquals(entity.owner.avatarUrl, actual.owner.avatarUrl)
        assertEquals(entity.owner.ownerId, actual.owner.id)
        assertEquals(entity.owner.ownerUrl, actual.owner.url)
        assertEquals(entity.owner.type, actual.owner.type)
    }
}