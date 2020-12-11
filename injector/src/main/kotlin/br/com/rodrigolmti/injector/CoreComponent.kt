package br.com.rodrigolmti.injector

import android.app.Application
import br.com.rodrigolmti.injector.modules.CommonModule
import br.com.rodrigolmti.injector.modules.DispatcherModule
import br.com.rodrigolmti.injector.modules.NetworkingModule
import br.com.rodrigolmti.kotlin_utils.IConnectionChecker
import br.com.rodrigolmti.kotlin_utils.ICoroutineDispatchers
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DispatcherModule::class,
        NetworkingModule::class,
        CommonModule::class,
    ]
)
interface CoreComponent {

    fun providesCoroutineDispatchers(): ICoroutineDispatchers

    fun providesIConnectionChecker(): IConnectionChecker

    fun providesRetrofit(): Retrofit

    fun application(): Application

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): CoreComponent
    }

    companion object {

        fun inject(app: Application): CoreComponent = DaggerCoreComponent
            .builder()
            .application(app)
            .build()
    }
}


