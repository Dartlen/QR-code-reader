package by.project.dartlen.qr_code_reader.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import by.project.dartlen.qr_code_reader.R
import by.project.dartlen.qr_code_reader.presentation.presenter.camera.CameraPresenter
import by.project.dartlen.qr_code_reader.presentation.view.ICameraView
import by.project.dartlen.qr_code_reader.ui.base.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.opencv.android.CameraBridgeViewBase
import org.opencv.core.Mat
import com.google.zxing.qrcode.QRCodeReader
import javax.inject.Inject
import org.opencv.core.CvType
import android.content.ContentValues.TAG
import com.google.zxing.common.HybridBinarizer
import android.graphics.Bitmap
import android.util.Log
import com.google.zxing.*
import org.opencv.android.Utils


class CameraFragment : BaseFragment(), ICameraView, CameraBridgeViewBase.CvCameraViewListener2{

    @Inject
    @InjectPresenter
    lateinit var presenter: CameraPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var unbinder: Unbinder
    private lateinit var mRgba: Mat
    private lateinit var mGray: Mat

    private lateinit var qr_code2: Mat

    private var doRgba = false
    private var doGray = false
    private var doThresh = false
    private var doAdaptOnly = false
    private var doOtsuOnly = false
    private var doErode = false
    private var doFindContours = false
    private var doFindSquares = false
    private var doPersTrans = false
    private var doQRreader = false

    private var endWithRgba = false
    private var endWithGray = false
    private var endWithAdaptOnly = false
    private var endWithOtsuOnly = false
    private var endWithErode = false
    private var endWithContours = false
    private var endWithSquares = false
    private var endWithPersTrans = false

    companion object {
        fun newInstance() = CameraFragment()
    }

    @BindView(R.id.activity_main_surface_view)
    protected lateinit var surface_view: CameraBridgeViewBase

    override fun onResume() {
        super.onResume()
        System.loadLibrary("native_code")

        surface_view.enableView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_camera, container, false)
                .also{
                    unbinder = ButterKnife.bind(this@CameraFragment, it)
                }
        surface_view.setCvCameraViewListener(this)
        System.loadLibrary("native_code")
        surface_view.enableView()

        return view
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        surface_view.disableView()
    }

    override fun onDestroy() {
        super.onDestroy()
        surface_view.disableView()
    }

    override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame): Mat {

		mRgba = inputFrame.gray()
    	mGray = inputFrame.gray()

        doRgba = true
        doGray = false
        doThresh = true
        doAdaptOnly = false
        doOtsuOnly = false
        doErode = true
        doFindContours = true
        doFindSquares = true
        doPersTrans = false
        doQRreader = true

        endWithRgba = false
        endWithGray = false
        endWithAdaptOnly = false
        endWithOtsuOnly = false
        endWithErode = false
        endWithContours = false
        endWithSquares = false

	       	callNative(
        			doRgba,
        			doGray,
        			doThresh,
        			doAdaptOnly,
        			doOtsuOnly,
        			doErode,
        			doFindContours,
        			doFindSquares,
        			doPersTrans,
        			doQRreader,

        			endWithRgba,
        		    endWithGray,
        		    endWithAdaptOnly,
        			endWithOtsuOnly,
        		    endWithErode,
        		    endWithContours,
            		endWithSquares,

        			mRgba.getNativeObjAddr(),
           		    mGray.getNativeObjAddr(),

           		    qr_code2.getNativeObjAddr()
        			)
        zxing()

        return mRgba
    }

    external fun callNative(

            doRgba: Boolean,
            doGray: Boolean,
            doThresh: Boolean,
            doAdaptOnly: Boolean,
            doOtsuOnly: Boolean,
            doErode: Boolean,
            doFindContous: Boolean,
            doFindSquares: Boolean,
            doPersTrans: Boolean,
            doQRreader: Boolean,


            endWithRgba: Boolean,
            endWithGray: Boolean,
            endAdaptOnly: Boolean,
            endOtsuOnly: Boolean,
            endWithErode: Boolean,
            endWithContours: Boolean,
            endWithSquares: Boolean,

            matAddrRgba: Long,
            matAddrGr: Long,

            matAddrQr_gray2: Long
    )

    @Throws(ChecksumException::class, FormatException::class)
    fun zxing() {

        val bMap2 = Bitmap.createBitmap(qr_code2.width(), qr_code2.height(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(qr_code2, bMap2)
        val intArray2 = IntArray(bMap2.width * bMap2.height)
        bMap2.getPixels(intArray2, 0, bMap2.width, 0, 0, bMap2.width, bMap2.height)
        val source2 = RGBLuminanceSource(bMap2.width, bMap2.height, intArray2)

        val bitmap2 = BinaryBitmap(HybridBinarizer(source2))

        val reader2 = QRCodeReader()

        try {

            val result2 = reader2.decode(bitmap2)
            Log.d(TAG, "Found something 2: " + result2.text)

            //runOnUiThread(Runnable {
                //m_QRcodeString2 = "" + result2.text
                //qr_message2.setText(m_QRcodeString2)

                //val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                //val clip = ClipData.newPlainText("QR code content", qr_message2.getText())
                //clipboard.setPrimaryClip(clip)

            //    val handler2 = Handler()
            //    handler2.postDelayed(Runnable { }, 2000)
            //})

        } catch (e: NotFoundException) {
            Log.d(TAG, "Code Not Found!")
            e.printStackTrace()
        }


    }
    override fun onCameraViewStarted(width: Int, height: Int) {
        mRgba = Mat(height, width, CvType.CV_8UC4)
        mGray = Mat(height, width, CvType.CV_8UC1)

        qr_code2 = Mat(200, 200, CvType.CV_8UC1)
    }

    override fun onCameraViewStopped() {
        mRgba.release()
        mGray.release()

        qr_code2.release()
    }

    override fun showToast() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}