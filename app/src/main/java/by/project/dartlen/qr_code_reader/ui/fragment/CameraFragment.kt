package by.project.dartlen.qr_code_reader.ui.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.ButterKnife
import butterknife.Unbinder
import by.project.dartlen.qr_code_reader.R
import by.project.dartlen.qr_code_reader.presentation.presenter.camera.CameraPresenter
import by.project.dartlen.qr_code_reader.presentation.view.ICameraView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.gms.vision.barcode.Barcode
import info.androidhive.barcode.BarcodeReader
import javax.inject.Inject
import android.widget.Toast
import butterknife.BindView
import android.content.Intent
import java.net.URL


class CameraFragment : Fragment(), ICameraView, BarcodeReader.BarcodeReaderListener{

    @Inject
    @InjectPresenter
    lateinit var presenter: CameraPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    @BindView(R.id.url)
    lateinit var url: TextView

    private lateinit var unbinder: Unbinder

    private lateinit var barcodeReader: BarcodeReader

    companion object {
        fun newInstance() = CameraFragment()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_camera, container, false)
                .also{
                    unbinder = ButterKnife.bind(this@CameraFragment, it)
                }
        ButterKnife.bind(this, view)
        barcodeReader = childFragmentManager.findFragmentById(R.id.barcode_fragment) as BarcodeReader
        barcodeReader.setListener(this)

        url.setOnClickListener {
            try {
                URL(url.toString()).openConnection()?.connect()
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(url.text.toString()))
                startActivity(i)
            }catch (e:Exception){

                val clipboard =  activity!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.primaryClip =  ClipData.newPlainText("text", url.text.toString())

                Toast.makeText(context, clipboard.getPrimaryClip().getItemAt(0).getText(), Toast.LENGTH_LONG).show()
            }

        }

        return view
    }

    override fun onScanned(barcode: Barcode) {
        url.text= barcode.rawValue
        url.visibility=View.VISIBLE
        Log.d("dsad", barcode.toString())

    }

    override fun onScannedMultiple(barcodes: MutableList<Barcode>) {
        var codes=""
        for (barcode in barcodes) {
            codes += (barcode.displayValue + ", ")
        }
        url.visibility = View.VISIBLE
        url.setText(codes)

    }

    override fun onBitmapScanned(sparseArray: SparseArray<Barcode>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onScanError(errorMessage: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCameraPermissionDenied() {

    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun showToast() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}