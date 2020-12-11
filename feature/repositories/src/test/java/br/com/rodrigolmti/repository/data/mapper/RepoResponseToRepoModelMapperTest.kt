package br.com.rodrigolmti.repository.data.mapper

import br.com.rodrigolmti.repository.factories.ModelFactory
import org.junit.Test
import kotlin.test.assertEquals

internal class RepoResponseToRepoModelMapperTest {

    private val mapper = RepoResponseToRepoModelMapper()

    @Test
    fun call_returnObjectWithSameValues() {
        val response = ModelFactory.repoResponse()

        val actual = mapper(response)

        assertEquals(response.id, actual.id)
        assertEquals(response.name, actual.name)
        assertEquals(response.private, actual.private)
        assertEquals(response.description, actual.description)
        assertEquals(response.url, actual.url)
        assertEquals(response.createdAt, actual.createdAt)
        assertEquals(response.lastUpdatedAt, actual.lastUpdatedAt)
        assertEquals(response.stars, actual.stars)
        assertEquals(response.watchersCount, actual.watchersCount)
        assertEquals(response.score, actual.score)
        assertEquals(response.defaultBranch, actual.defaultBranch)
        assertEquals(response.owner.avatarUrl, actual.owner.avatarUrl)
        assertEquals(response.owner.id, actual.owner.id)
        assertEquals(response.owner.url, actual.owner.url)
        assertEquals(response.owner.type, actual.owner.type)
    }
}