package com.rootstrap.android.network.managers

import com.rootstrap.android.network.managers.session.SessionManager
import com.rootstrap.android.network.managers.session.SessionManagerImpl
import com.rootstrap.android.network.managers.user.UserManager
import com.rootstrap.android.network.managers.user.UserManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class ManagerModule {

    @Binds
    abstract fun provideSessionManager(sessionManagerImpl: SessionManagerImpl): SessionManager

    @Binds
    abstract fun provideUserManager(userManagerImplImpl: UserManagerImpl): UserManager
}
