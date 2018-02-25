package by.project.dartlen.qr_code_reader

import android.app.Activity
import android.app.Application
import by.project.dartlen.qr_code_reader.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector{
    companion object {
        lateinit var instance: App
    }

    @Inject
    protected lateinit var dispatchingActivityInject: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        instance = this

        DaggerAppComponent
                .builder()
                .context(this)
                .build()
                .inject(this)
    }

    override fun activityInjector() = dispatchingActivityInject

}