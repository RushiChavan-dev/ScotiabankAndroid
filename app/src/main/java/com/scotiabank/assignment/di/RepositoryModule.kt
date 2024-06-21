package com.scotiabank.assignment.di

import android.content.Context
import com.scotiabank.assignment.network.UserService
import com.scotiabank.assignment.network.model.RepoDtoMapper
import com.scotiabank.assignment.network.model.UserDtoMapper
import com.scotiabank.assignment.repository.UserRepository
import com.scotiabank.assignment.repository.UserRepositoryImpl
import com.scotiabank.assignment.util.ResourceStringProvider
import com.scotiabank.assignment.util.StringProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Retrofit instance is injecting into the repository and
 * the {RepositoryModule} is injected into {RepoListViewModel}
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(
        userService: UserService, userDtoMapper: UserDtoMapper, repoDtoMapper: RepoDtoMapper
    ): UserRepository {
        return UserRepositoryImpl(
            userService = userService, userMapper = userDtoMapper, repoMapper = repoDtoMapper
        )
    }

    @Singleton
    @Provides
    fun provideStringProvider(@ApplicationContext context: Context): StringProvider {
        return ResourceStringProvider(context)
    }
}