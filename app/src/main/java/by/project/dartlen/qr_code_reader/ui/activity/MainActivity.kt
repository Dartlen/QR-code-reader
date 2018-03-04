package by.project.dartlen.qr_code_reader.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import by.project.dartlen.qr_code_reader.R
import by.project.dartlen.qr_code_reader.presentation.routing.base.IRoutingBinder
import by.project.dartlen.qr_code_reader.ui.base.SingleFragmentActivity
import by.project.dartlen.qr_code_reader.ui.fragment.CameraFragment
import by.project.dartlen.qr_code_reader.ui.fragment.MainFragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivity: AppCompatActivity(), HasSupportFragmentInjector, BottomNavigationBar.OnTabSelectedListener {

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    fun createFragment() = MainFragment.newInstance()

    @Inject
    protected lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    protected lateinit var routingBinder: IRoutingBinder

    private lateinit var unbinder: Unbinder

    @BindView(R.id.bottom_navigation_bar)
    protected lateinit var bottomNavigation: BottomNavigationBar

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        unbinder = ButterKnife.bind(this)

        super.onCreate(savedInstanceState)

        val fragment = supportFragmentManager.findFragmentById(R.id.container)

        if(fragment == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, createFragment())
                    .commit()
        }

        //bottomNavigation.toggle()
        bottomNavigation.setTabSelectedListener(this)
        bottomNavigation
                .addItem(BottomNavigationItem(R.drawable.ic_generate, "Generate")
                        .setActiveColorResource(R.color.colorAccent))

                .addItem(BottomNavigationItem(R.drawable.ic_decode, "Decode")
                        .setActiveColorResource(R.color.colorAccent))

                .addItem(BottomNavigationItem(R.drawable.ic_about, "About")
                        .setActiveColorResource(R.color.colorAccent))
                .initialise()



    }

    override fun onTabReselected(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTabSelected(position: Int) {
        Log.d("dasd",position.toString())
        supportFragmentManager.beginTransaction().apply {
            when (position) {
                1 -> replace(R.id.container, CameraFragment.newInstance())
                else -> replace(R.id.container, MainFragment.newInstance())
            }.commitAllowingStateLoss()

       /* when(position){
            0->router.navigateTo("generatefragment")
            1->router.navigateTo("decodefragment")
        }*/
        }
    }

    override fun onTabUnselected(position: Int) {

    }

    override fun supportFragmentInjector() = fragmentInjector

    override fun onResume() {
        super.onResume()
        routingBinder.bind()
    }

    override fun onPause() {
        super.onPause()
        routingBinder.unbind()
    }

}
