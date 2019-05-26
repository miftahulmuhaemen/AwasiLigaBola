package com.example.ligasubmission

import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.listLeagueActivity.ListLeaguePresenter
import com.example.ligasubmission.listLeagueActivity.ListLeagueView
import com.example.ligasubmission.model.Event
import com.example.ligasubmission.model.EventsResponse
import com.example.ligasubmission.util.TestContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations



class ListLeaguePresenterTest {

    @Mock
    private lateinit var view : ListLeagueView

    @Mock
    private lateinit var gson : Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: ListLeaguePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ListLeaguePresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testListLeaguePresenterNext(){

        val event: MutableList<Event> = mutableListOf()
        val response = EventsResponse(event)
        val idEvent = "4387"

        runBlocking {

            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", EventsResponse::class.java)).thenReturn(response)

            presenter.getNextMatch(idEvent)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showNextLeague(event)
            Mockito.verify(view).hideLoading()

        }

    }

    @Test
    fun testListLeaguePresenterPast(){

        val event: MutableList<Event> = mutableListOf()
        val response = EventsResponse(event)
        val idEvent = "4387"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", EventsResponse::class.java)).thenReturn(response)

            presenter.getPreviousMatch(idEvent)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showPastLeague(event)
            Mockito.verify(view).hideLoading()

        }

    }

}