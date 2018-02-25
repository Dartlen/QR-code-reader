package by.project.dartlen.qr_code_reader.ui.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import by.project.dartlen.qr_code_reader.R
import by.project.dartlen.qr_code_reader.presentation.presenter.main.MainPresenter
import by.project.dartlen.qr_code_reader.presentation.view.IMainView
import by.project.dartlen.qr_code_reader.ui.base.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import javax.inject.Inject

class MainFragment : BaseFragment(), IMainView {

    companion object {

        fun newInstance() = MainFragment()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = presenter
    private lateinit var unbinder: Unbinder

    @BindView(R.id.imageView)
    protected lateinit var image: ImageView

    @BindView(R.id.input_text)
    protected lateinit var input: EditText

    @BindView(R.id.button)
    protected lateinit var button: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)
                    .also{
                    unbinder = ButterKnife.bind(this@MainFragment, it)
                    }

    override fun onDestroyView(){
        super.onDestroyView()
        unbinder.unbind()
    }

    @OnClick(R.id.button)
    protected fun onButtonToQr(){
        presenter.onToQr(input.text.toString())
    }

    override fun showQr(bmp: Bitmap) {
        image.setImageBitmap(bmp)
    }
}