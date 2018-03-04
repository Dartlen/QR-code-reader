package by.project.dartlen.qr_code_reader.di.component

import android.content.Context
import by.project.dartlen.qr_code_reader.App
import by.project.dartlen.qr_code_reader.di.module.AppModule
import by.project.dartlen.qr_code_reader.di.module.RouterModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AppModule::class),
    (RouterModule::class)])
interface AppComponent{

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(application: App)
}