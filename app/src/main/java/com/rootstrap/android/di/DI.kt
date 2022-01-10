package com.rootstrap.android.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.rootstrap.android.BuildConfig
import com.rootstrap.android.ui.activity.main.*
import com.rootstrap.data.api.ApiProvider
import com.rootstrap.data.api.ApiServiceFactory
import com.rootstrap.data.api.interceptors.AuthenticationInterceptor
import com.rootstrap.data.api.interceptors.ConnectivityInterceptor
import com.rootstrap.data.api.interceptors.HeadersInterceptor
import com.rootstrap.data.api.interceptors.ResponseInterceptor
import com.rootstrap.data.managers.session.SessionManager
import com.rootstrap.data.managers.session.SessionManagerImpl
import com.rootstrap.data.repository.UserRepository
import com.rootstrap.data.util.Prefs
import com.rootstrap.usecases.SignIn
import com.rootstrap.usecases.SignOut
import com.rootstrap.usecases.SignUp
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinExperimentalAPI
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

@KoinExperimentalAPI
fun Application.initDI() {
    startKoin {
        androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
        androidContext(this@initDI)
        fragmentFactory()
        modules(appModule, dataModule, scopesModule)
    }
}

val appModule = module {
    single { SignUp(get()) }
    single { SignIn(get()) }
    single { SignOut(get()) }
    single<SessionManager> { SessionManagerImpl(get()) }
    single<SharedPreferences> { androidApplication().getSharedPreferences("userPreferences", Context.MODE_PRIVATE) }

    viewModel { SignUpActivityViewModel(get(), get()) }
    viewModel { SignInActivityViewModel(get(), get()) }
    viewModel { ProfileActivityViewModel(get(), get()) }
}

val dataModule = module {
    single { ApiServiceFactory(get(), get(), get()) }
    single { HeadersInterceptor() }
    single { ConnectivityInterceptor() }
    single { AuthenticationInterceptor(get()) }
    single { ResponseInterceptor(get(), get()) }
    single { ApiProvider(get()) }

    factory { Prefs(get()) }
    factory { UserRepository(get()) }
}

private val scopesModule = module {
    scope<SignUpActivity> {}
    scope<SignInActivity> {}
    scope<ProfileActivity> {}
}
