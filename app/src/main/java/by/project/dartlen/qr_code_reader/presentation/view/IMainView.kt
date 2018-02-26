package by.project.dartlen.qr_code_reader.presentation.view

import android.graphics.Bitmap
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IMainView : MvpView{
    fun showQr(bmp: Bitmap)
    fun showToast(message : String)
}