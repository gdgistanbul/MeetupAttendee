package com.gdgistanbul.di

import com.gdgistanbul.viewmodel.EventListViewModel
import com.gdgistanbul.viewmodel.MeetupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val meetupViewModelModule = module {
    viewModel { MeetupViewModel(get()) }
    viewModel { EventListViewModel(get()) }
}