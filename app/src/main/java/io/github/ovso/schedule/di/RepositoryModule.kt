package io.github.ovso.schedule.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.ovso.schedule.data.ScheduleRepository
import io.github.ovso.schedule.data.ScheduleRepositoryImpl

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsScheduleRepository(impl: ScheduleRepositoryImpl): ScheduleRepository

}