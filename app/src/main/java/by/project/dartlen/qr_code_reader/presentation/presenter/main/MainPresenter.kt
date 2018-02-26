package by.project.dartlen.qr_code_reader.presentation.presenter.main

import android.graphics.BitmapFactory
import android.os.Environment
import by.project.dartlen.qr_code_reader.di.scope.FragmentScope
import by.project.dartlen.qr_code_reader.presentation.view.IMainView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import net.glxn.qrgen.android.QRCode
import java.io.File
import java.nio.file.FileSystem
import javax.inject.Inject
import android.graphics.Bitmap
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


@FragmentScope
@InjectViewState
class MainPresenter @Inject constructor(

): MvpPresenter<IMainView>(){

    private lateinit var bitmap: Bitmap
    private lateinit var timeStamp: String
    private lateinit var root: String

    fun onToQr(text: String){
        bitmap = QRCode.from(text).withSize(1000, 1000).bitmap()
        viewState.showQr(bitmap)
    }

    fun onSave(){
        if (isExternalStorageWritable()) {
            saveImage(bitmap)
            viewState.showToast("Image saved $root/qr-$timeStamp.jpg!")
        }else{
            //prompt the user or do something
        }
    }

    fun isExternalStorageWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        return if (Environment.MEDIA_MOUNTED == state) {
            true
        } else false
    }

    private fun saveImage(finalBitmap: Bitmap) {

        root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
        val myDir = File(root + "/qrcoderieader")
        myDir.mkdirs()

        timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        val file = File(myDir, "qr-$timeStamp.jpg")
        if (file.exists()) file.delete()
        try {
            val out = FileOutputStream(file)
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}