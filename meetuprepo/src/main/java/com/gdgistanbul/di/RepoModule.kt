package com.gdgistanbul.di

import com.gdgistanbul.repo.MeetupRepo
import org.koin.dsl.module

val meetupRepoModule = module {
    single { MeetupRepo(get()) }
}

val repoModuleList =mutableListOf(meetupRepoModule, meetupApiModule)