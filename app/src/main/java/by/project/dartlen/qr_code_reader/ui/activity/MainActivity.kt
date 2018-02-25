package by.project.dartlen.qr_code_reader.ui.activity

import android.content.Context
import android.content.Intent
import by.project.dartlen.qr_code_reader.ui.base.SingleFragmentActivity
import by.project.dartlen.qr_code_reader.ui.fragment.MainFragment

class MainActivity : SingleFragmentActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun createFragment() = MainFragment.newInstance()
}
