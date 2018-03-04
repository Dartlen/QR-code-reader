package by.project.dartlen.qr_code_reader.presentation.routing

import by.project.dartlen.qr_code_reader.presentation.routing.base.BaseRouting
import by.project.dartlen.qr_code_reader.ui.activity.MainActivity
import by.project.dartlen.qr_code_reader.ui.fragment.CameraFragment
import by.project.dartlen.qr_code_reader.ui.viewholder.Screens
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

class MainRouting @Inject constructor(
        activity: MainActivity,
        navigationHolder: NavigatorHolder
): BaseRouting<MainActivity>(activity, navigationHolder){

    override fun recognizeCommand(command: Command) {
        when(command){
            is Forward -> when (command.screenKey){
                Screens.CAMERA -> openFragment(CameraFragment())
            }
        }
    }
}