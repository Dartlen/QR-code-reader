package by.project.dartlen.qr_code_reader.di.module.fragments

import by.project.dartlen.qr_code_reader.di.scope.FragmentScope
import by.project.dartlen.qr_code_reader.ui.fragment.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule{

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun fragment(): MainFragment

}