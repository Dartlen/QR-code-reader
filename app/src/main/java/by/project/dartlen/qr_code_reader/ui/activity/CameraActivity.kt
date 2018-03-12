package by.project.dartlen.qr_code_reader.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import by.project.dartlen.qr_code_reader.R
import by.project.dartlen.qr_code_reader.ui.fragment.CameraFragment
import dagger.android.AndroidInjection

class CameraActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, CameraActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_single_fragment)
        super.onCreate(savedInstanceState)

        val fragment = supportFragmentManager.findFragmentById(R.id.container)

        if(fragment == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, CameraFragment.newInstance())
                    .commit()
        }
    }

}
