package by.project.dartlen.qr_code_reader.presentation.presenter.camera

import by.project.dartlen.qr_code_reader.di.scope.FragmentScope
import by.project.dartlen.qr_code_reader.presentation.view.ICameraView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@FragmentScope
@InjectViewState
class CameraPresenter @Inject constructor(

): MvpPresenter<ICameraView>(){


}