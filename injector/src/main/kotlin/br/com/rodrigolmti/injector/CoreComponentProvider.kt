package br.com.rodrigolmti.injector

import android.app.Activity

fun Activity.coreComponent() = (this.applicationContext as CoreComponentProvider).coreComponent()

interface CoreComponentProvider {
    fun coreComponent(): CoreComponent
}