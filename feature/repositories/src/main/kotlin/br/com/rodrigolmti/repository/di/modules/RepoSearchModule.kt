package br.com.rodrigolmti.repository.di.modules

import dagger.Module

@Module(includes = [
    RepoSearchDataModule::class,
    RepoSearchDomainModule::class,
    RepoSearchPresentationModule::class
])
interface RepoSearchModule