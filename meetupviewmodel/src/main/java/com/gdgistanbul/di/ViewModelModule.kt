package com.gdgistanbul.di

import com.gdgistanbul.viewmodel.MeetupViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val meetupViewModelModule = module {
    viewModel {
        MeetupViewModel(get())
    }
}

val moduleList = repoModuleList + meetupViewModelModule