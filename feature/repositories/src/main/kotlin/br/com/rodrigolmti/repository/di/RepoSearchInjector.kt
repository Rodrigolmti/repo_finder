package br.com.rodrigolmti.repository.di

import android.app.Activity
import br.com.rodrigolmti.injector.coreComponent
import java.lang.ref.WeakReference

internal object RepoSearchInjector {

    private var componentWeakRef: WeakReference<RepoSearchComponent>? = null

    fun getComponent(activity: Activity): RepoSearchComponent {
        return componentWeakRef?.get() ?: run {
            val component = DaggerRepoSearchComponent.builder()
                .coreComponent(activity.coreComponent())
                .build()
            componentWeakRef = WeakReference(component)
            return component
        }
    }
}