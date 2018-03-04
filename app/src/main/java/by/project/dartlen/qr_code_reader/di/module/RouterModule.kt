package by.project.dartlen.qr_code_reader.di.module

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone

@Module
class RouterModule{
    private val cicirone = Cicerone.create()

    @Provides
    fun provideRouting() = cicirone.router

    @Provides
    fun provideNavigationHolder() = cicirone.navigatorHolder
}