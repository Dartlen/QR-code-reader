package by.project.dartlen.qr_code_reader.di.module.main

import android.app.Activity
import by.project.dartlen.qr_code_reader.ui.activity.MainActivity
import by.project.dartlen.qr_code_reader.di.scope.ActivityScope
import by.project.dartlen.qr_code_reader.di.scope.FragmentScope
import by.project.dartlen.qr_code_reader.ui.fragment.CameraFragment
import by.project.dartlen.qr_code_reader.ui.fragment.MainFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule{
    @Binds
    @ActivityScope
    abstract fun activity(activity: MainActivity): Activity

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun fragment(): CameraFragment
}