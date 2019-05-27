package com.example.ligasubmission

import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.detailMatch.DetailMatchPresenter
import com.example.ligasubmission.detailMatch.DetailMatchView
import com.example.ligasubmission.detailMatch.DetailMatchActivity
import com.example.ligasubmission.model.*
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

class detailMatchPresenterTest {

    @Mock
    private lateinit var activity: DetailMatchActivity

    @Mock
    private lateinit var view : DetailMatchView

    @Mock
    private lateinit var gson : Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var event : Event

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: DetailMatchPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(activity, view, event, "", apiRepository, gson, TestContextProvider())
    }


    @Test
    fun testDetailMatchPresenter(){

        val home: MutableList<Team> = mutableListOf()
        val homeResponse = TeamResponse(home)

        val away: MutableList<Team> = mutableListOf()
        val awayResponse = TeamResponse(away)

        runBlocking {

            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", TeamResponse::class.java)).thenReturn(homeResponse)
            Mockito.`when`(gson.fromJson("", TeamResponse::class.java)).thenReturn(awayResponse)

            presenter.getTeamPicture()

            Mockito.verify(view).showTeamLogo(homeResponse.teams,awayResponse.teams)
        }

    }


}