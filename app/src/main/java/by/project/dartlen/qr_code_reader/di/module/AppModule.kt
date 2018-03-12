package by.project.dartlen.qr_code_reader.di.module

import by.project.dartlen.qr_code_reader.ui.activity.MainActivity
import by.project.dartlen.qr_code_reader.di.module.main.MainActivityModule
import by.project.dartlen.qr_code_reader.di.scope.ActivityScope
import by.project.dartlen.qr_code_reader.ui.activity.CameraActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes=[(AndroidSupportInjectionModule::class)])
abstract class AppModule{
    @ActivityScope
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun mainActivityInjector(): MainActivity

}