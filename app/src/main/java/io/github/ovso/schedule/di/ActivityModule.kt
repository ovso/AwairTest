package io.github.ovso.schedule.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.github.ovso.schedule.ui.main.adapter.ScheduleAdapter

@InstallIn(ActivityComponent::class)
@Module
object ActivityModule {

    @Provides
    fun providesScheduleAdapter(): ScheduleAdapter = ScheduleAdapter()

}