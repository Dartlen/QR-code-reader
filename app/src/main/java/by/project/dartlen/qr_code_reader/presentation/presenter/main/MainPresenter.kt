package by.project.dartlen.qr_code_reader.presentation.presenter.main

import android.os.Environment
import by.project.dartlen.qr_code_reader.di.scope.FragmentScope
import by.project.dartlen.qr_code_reader.presentation.view.IMainView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import net.glxn.qrgen.android.QRCode
import java.io.File
import javax.inject.Inject
import android.graphics.Bitmap
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import ru.terrakok.cicerone.Router
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*




@FragmentScope
@InjectViewState
class MainPresenter @Inject constructor(
    private val router: Router
): MvpPresenter<IMainView>(){

    private lateinit var bitmap: Bitmap
    private lateinit var timeStamp: String
    private lateinit var root: String

    fun onToQr(text: String){
        //bitmap = QRCode.from(text).withSize(1000, 1000).bitmap()
        if(!text.equals("")) {
            bitmap = encodeAsBitmap(text, BarcodeFormat.QR_CODE, 1000, 1000)
            viewState.showQr(bitmap)
        }
        else
            viewState.showToast("Необходимо ввести кодируемый текст")
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

    fun onCamera(){
        //router.navigateTo(Screens.CAMERA)
    }

    @Throws(WriterException::class)
    private fun encodeAsBitmap(contents: String, format: BarcodeFormat, img_width: Int, img_height: Int): Bitmap {
        var hints: MutableMap<EncodeHintType, Any>? = null
        val encoding = guessAppropriateEncoding(contents)
        if (encoding != null) {
            hints = EnumMap<EncodeHintType, Any>(EncodeHintType::class.java)
            hints!![EncodeHintType.CHARACTER_SET] = encoding
        }
        val writer = MultiFormatWriter()
        val result: BitMatrix
        ///try {
            result = writer.encode(contents, format, img_width, img_height, hints)
       /* } catch (iae: IllegalArgumentException) {
            // Unsupported format
            return
        }*/

        val width = result.getWidth()
        val height = result.getHeight()
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (result.get(x, y)) BLACK else WHITE
            }
        }

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }

    fun guessAppropriateEncoding(contents: CharSequence): String? {
        // Very crude at the moment
        for (i in 0 until contents.length) {
            if (contents[i].toInt() > 0xFF) {
                return "UTF-8"
            }
        }
        return null
    }

}