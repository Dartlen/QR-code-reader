package by.project.dartlen.qr_code_reader.ui.base

import android.app.Activity
import android.content.Context
import android.os.Build
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpAppCompatFragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : MvpAppCompatFragment(){

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            AndroidSupportInjection.inject(this)
        }
        super.onAttach(context)
    }

    override fun onAttach(activity: Activity?) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            AndroidSupportInjection.inject(this)
        }
        super.onAttach(activity)
    }
}