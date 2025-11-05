package com.thulanicreatives.stackoverflow.di

import com.thulanicreatives.stackoverflow.data.repository.StackoverflowRepositoryImpl
import com.thulanicreatives.stackoverflow.domain.repository.StackoverflowRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStackoverflowRepository(
        stackoverflowRepositoryImpl: StackoverflowRepositoryImpl
    ) : StackoverflowRepository

}