package com.gdgistanbul.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gdgistanbul.model.Event
import com.gdgistanbul.repo.MeetupRepo
import io.mockk.*
import io.mockk.impl.annotations.MockK
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
        every { observer.onChanged(eventList) } just runs
        val toastObserver: Observer<String> = mockk()
        val viewModel = MeetupViewModel(meetupRepo)
        viewModel.eventsLiveData.observeForever(observer)
        viewModel.toastLiveData.observeForever(toastObserver)
        viewModel.refreshEvents().join()
        verify { observer.onChanged(eventList) }
        verify(exactly = 0) { toastObserver.onChanged(any()) }

    }


    @Test
    fun `refreshEvents failure`() = runBlocking {
        val message = "refreshException"
        val eventList: List<Event> = mockk()
        coEvery { meetupRepo.getEvents() } throws RuntimeException(message)
        val toastObserver: Observer<String> = mockk()
        every { toastObserver.onChanged(message) } just Runs
        val eventsObserver: Observer<List<Event>> = mockk()
        val viewModel = MeetupViewModel(meetupRepo)
        viewModel.eventsLiveData.observeForever(eventsObserver)
        viewModel.toastLiveData.observeForever(toastObserver)
        viewModel.refreshEvents().join()
        verify(exactly = 0) { eventsObserver.onChanged(eventList) }
        verify { toastObserver.onChanged(message) }

    }

    @Test
    fun `login success`() = runBlocking {
        val viewModel = MeetupViewModel(meetupRepo)
        val code = "1234"
        val observer: Observer<Unit> = mockk()
        val toastObserver: Observer<String> = mockk()
        every { observer.onChanged(Unit) } just runs
        every { toastObserver.onChanged(any()) } just runs
        coEvery { meetupRepo.authenticate(code) } just runs
        viewModel.loginLiveData.observeForever(observer)
        viewModel.toastLiveData.observeForever(toastObserver)
        viewModel.authenticate(code).join()
        verify(exactly = 0) { toastObserver.onChanged(any()) }
        verify { observer.onChanged(Unit) }

    }

    @Test
    fun `login failure`() = runBlocking {
        val message = "loginException"
        val viewModel = MeetupViewModel(meetupRepo)
        val code = "1234"
        val observer: Observer<Unit> = mockk()
        val toastObserver: Observer<String> = mockk()
        every { observer.onChanged(Unit) } just runs
        every { toastObserver.onChanged(any()) } just runs
        coEvery { meetupRepo.authenticate(code) } throws RuntimeException(message)
        viewModel.loginLiveData.observeForever(observer)
        viewModel.toastLiveData.observeForever(toastObserver)
        viewModel.authenticate(code).join()
        verify { toastObserver.onChanged(message) }
        verify(exactly = 0) { observer.onChanged(Unit) }

    }


    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}