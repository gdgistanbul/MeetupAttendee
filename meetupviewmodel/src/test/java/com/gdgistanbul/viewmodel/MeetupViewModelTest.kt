package com.gdgistanbul.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gdgistanbul.model.Event
import com.gdgistanbul.repo.MeetupRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MeetupViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @MockK
    lateinit var meetupRepo: MeetupRepo

    @Before
    fun initTests() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockKAnnotations.init(this)
    }

    @Test
    fun `refreshEvents success`() = runBlocking {
        val eventList: List<Event> = mockk()
        coEvery { meetupRepo.getEvents() } returns eventList
        val observer: Observer<List<Event>> = mockk()
        val viewModel = MeetupViewModel(meetupRepo)
        viewModel.eventsLiveData.observeForever(observer)
        viewModel.refreshEvents().join()
        verify { observer.onChanged(eventList) }

    }


    @Test
    fun `refreshEvents failure`() = runBlocking {

        val viewModel: MeetupViewModel = MeetupViewModel(meetupRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}