package com.rootstrap.android.di

import android.content.Context
import android.content.SharedPreferences
import com.rootstrap.android.network.managers.session.SessionManager
import com.rootstrap.android.network.managers.session.SessionManagerImpl
import com.rootstrap.android.network.managers.user.UserManager
import com.rootstrap.android.network.managers.user.UserManagerImpl
import com.rootstrap.android.network.managers.user.UserManagerRetrofitBuilder
import com.rootstrap.android.network.services.AuthenticationInterceptor
import com.rootstrap.android.network.services.ResponseInterceptor
import com.rootstrap.android.ui.activity.main.ProfileActivityViewModel
import com.rootstrap.android.ui.activity.main.SignInActivityViewModel
import com.rootstrap.android.ui.activity.main.SignUpActivityViewModel
import com.rootstrap.android.util.Prefs
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule: Module = module {

    single { AuthenticationInterceptor(get()) }
    single { ResponseInterceptor(get(), get()) }
    single { UserManagerRetrofitBuilder(get(), get()) }
    single<UserManager> { UserManagerImpl(get()) }
    single<SharedPreferences> { androidApplication().getSharedPreferences("userPreferences", Context.MODE_PRIVATE) }
    factory { Prefs(get()) }
    single<SessionManager> { SessionManagerImpl(get()) }
    viewModel { SignInActivityViewModel(get(), get()) }
    viewModel { ProfileActivityViewModel(get(), get()) }
    viewModel { SignUpActivityViewModel(get(), get()) }
    // viewModel { BottomBarViewModel(androidApplication()) }
    // single { provideIotDao(androidContext()) }
}
