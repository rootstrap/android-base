package com.rootstrap.android.network.managers

import com.rootstrap.android.network.managers.session.SessionManager
import com.rootstrap.android.network.managers.session.SessionManagerImpl
import com.rootstrap.android.network.managers.user.UserManager
import com.rootstrap.android.network.managers.user.UserManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ManagerModule {

    @Binds
    @Singleton
    abstract fun bindSessionManager(sessionManagerImpl: SessionManagerImpl): SessionManager

    @Binds
    @Singleton
    abstract fun bindUserManager(userManagerImplImpl: UserManagerImpl): UserManager
}
