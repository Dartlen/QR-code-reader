package by.project.dartlen.qr_code_reader.presentation.presenter.main

import by.project.dartlen.qr_code_reader.di.scope.FragmentScope
import by.project.dartlen.qr_code_reader.presentation.view.IMainView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import net.glxn.qrgen.android.QRCode
import javax.inject.Inject

@FragmentScope
@InjectViewState
class MainPresenter @Inject constructor(

): MvpPresenter<IMainView>(){

    fun onToQr(text: String){
        val bitmap = QRCode.from(text).withSize(1000, 1000).bitmap()
        viewState.showQr(bitmap)
    }

}