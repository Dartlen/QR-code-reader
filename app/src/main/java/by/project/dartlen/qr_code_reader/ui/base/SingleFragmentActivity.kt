package by.project.dartlen.qr_code_reader.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import by.project.dartlen.qr_code_reader.R
import by.project.dartlen.qr_code_reader.presentation.routing.base.IRoutingBinder
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class SingleFragmentActivity: AppCompatActivity(), HasSupportFragmentInjector{

    @Inject
    protected lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    protected lateinit var routingBinder: IRoutingBinder

    abstract fun createFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_single_fragment)
        super.onCreate(savedInstanceState)

        val fragment = supportFragmentManager.findFragmentById(R.id.container)

        if(fragment == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, createFragment())
                    .commit()
        }
    }

    override fun supportFragmentInjector() = fragmentInjector

}