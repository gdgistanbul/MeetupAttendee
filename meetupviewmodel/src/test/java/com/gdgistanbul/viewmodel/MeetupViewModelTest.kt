package com.gdgistanbul.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gdgistanbul.Event
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
import com.gdgistanbul.model.Event as EventModel

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
        val eventList: List<EventModel> = mockk()
        coEvery { meetupRepo.getEvents() } returns eventList
        val observer: Observer<List<EventModel>> = mockk()
        every { observer.onChanged(eventList) } just runs
        val toastObserver: Observer<Event<String>> = mockk()
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
        val eventList: List<EventModel> = mockk()
        coEvery { meetupRepo.getEvents() } throws RuntimeException(message)
        val toastObserver: Observer<Event<String>> = mockk()
        every { toastObserver.onChanged(Event(message)) } just Runs
        val eventsObserver: Observer<List<EventModel>> = mockk()
        val viewModel = MeetupViewModel(meetupRepo)
        viewModel.eventsLiveData.observeForever(eventsObserver)
        viewModel.toastLiveData.observeForever(toastObserver)
        viewModel.refreshEvents().join()
        verify(exactly = 0) { eventsObserver.onChanged(eventList) }
        verify { toastObserver.onChanged(Event(message)) }

    }

    @Test
    fun `login success`() = runBlocking {
        val viewModel = MeetupViewModel(meetupRepo)
        val code = "1234"
        val observer: Observer<Event<Unit>> = mockk()
        val toastObserver: Observer<Event<String>> = mockk()
        every { observer.onChanged(Event(Unit)) } just runs
        every { toastObserver.onChanged(any()) } just runs
        coEvery { meetupRepo.authenticate(code) } just runs
        viewModel.loginLiveData.observeForever(observer)
        viewModel.toastLiveData.observeForever(toastObserver)
        viewModel.authenticate(code).join()
        verify(exactly = 0) { toastObserver.onChanged(any()) }
        verify { observer.onChanged(Event(Unit)) }

    }

    @Test
    fun `login failure`() = runBlocking {
        val message = "loginException"
        val viewModel = MeetupViewModel(meetupRepo)
        val code = "1234"
        val observer: Observer<Event<Unit>> = mockk()
        val toastObserver: Observer<Event<String>> = mockk()
        every { observer.onChanged(Event(Unit)) } just runs
        every { toastObserver.onChanged(any()) } just runs
        coEvery { meetupRepo.authenticate(code) } throws RuntimeException(message)
        viewModel.loginLiveData.observeForever(observer)
        viewModel.toastLiveData.observeForever(toastObserver)
        viewModel.authenticate(code).join()
        verify { toastObserver.onChanged(Event(message)) }
        verify(exactly = 0) { observer.onChanged(Event(Unit)) }

    }


    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}