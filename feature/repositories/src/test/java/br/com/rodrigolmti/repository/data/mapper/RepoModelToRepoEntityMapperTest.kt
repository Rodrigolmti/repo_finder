package br.com.rodrigolmti.repository.data.mapper

import br.com.rodrigolmti.repository.factories.ModelFactory
import org.junit.Test
import kotlin.test.assertEquals

internal class RepoModelToRepoEntityMapperTest {

    val mapper = RepoModelToRepoEntityMapper()

    @Test
    fun call_returnObjectWithSameValues() {
        val model = ModelFactory.repoModel()

        val actual = mapper(model)

        assertEquals(model.id, actual.repoId)
        assertEquals(model.name, actual.name)
        assertEquals(model.private, actual.private)
        assertEquals(model.description, actual.description)
        assertEquals(model.url, actual.repoUrl)
        assertEquals(model.createdAt, actual.createdAt)
        assertEquals(model.lastUpdatedAt, actual.lastUpdatedAt)
        assertEquals(model.stars, actual.stars)
        assertEquals(model.watchersCount, actual.watchersCount)
        assertEquals(model.score, actual.score)
        assertEquals(model.defaultBranch, actual.defaultBranch)
        assertEquals(model.owner.avatarUrl, actual.owner.avatarUrl)
        assertEquals(model.owner.id, actual.owner.ownerId)
        assertEquals(model.owner.url, actual.owner.ownerUrl)
        assertEquals(model.owner.type, actual.owner.type)
    }
}