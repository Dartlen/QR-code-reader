package by.project.dartlen.qr_code_reader.ui.fragment

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import by.project.dartlen.qr_code_reader.R
import by.project.dartlen.qr_code_reader.presentation.presenter.camera.CameraPresenter
import by.project.dartlen.qr_code_reader.presentation.view.ICameraView
import by.project.dartlen.qr_code_reader.ui.base.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.gms.vision.barcode.Barcode
import info.androidhive.barcode.BarcodeReader
import javax.inject.Inject
import android.widget.Toast

class CameraFragment : BaseFragment(), ICameraView, BarcodeReader.BarcodeReaderListener{

    @Inject
    @InjectPresenter
    lateinit var presenter: CameraPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

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
        barcodeReader = childFragmentManager.findFragmentById(R.id.barcode_fragment) as BarcodeReader
        barcodeReader.setListener(this)


        return view
    }

    override fun onScanned(barcode: Barcode?) {

    }

    override fun onScannedMultiple(barcodes: MutableList<Barcode>) {
        var codes=""
        for (barcode in barcodes) {
            codes += (barcode.displayValue + ", ")
        }

        val finalCodes = codes
        activity!!.runOnUiThread { Toast.makeText(activity, "Barcodes: " + finalCodes, Toast.LENGTH_SHORT).show() }
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