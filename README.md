# Repo-Finder - challenge

With this application you can search for repositories in the GitHub API, and if you do not have connection,
your previous searches will be saved locally, so you can search them again.

## Architecture

* Kotlin
* MVVM (LiveData)
* Clean Architecture
* Room
* Dagger
* Moshi
* Retrofit
* Coil

## Structure

### Features
* repository -> View -> VM -> UseCase -> Repository -> DataSources

Contains unit testing on each layer of the application, starting from VM

### Infra
* Injetor -> Dagger core injection with base instances
* Kotlin-utils -> Kotlin shared code
* Core-android -> Android shared code
* buildSrc -> Module that contains base configuration for gradle deps and Android configs
* build-plugins -> Composite build system (using build plugins) to config deps

All the screen are using base styles from material to make easy the theming change (dark and light)

## Improvements
* Create some sort of cache policy (to update the saved repositories)
* Use hilt when stable and production ready
* Use compose when stable and production ready


