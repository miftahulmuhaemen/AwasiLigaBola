package com.example.ligasubmission

import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.listDetailMatchActivity.ListDetailMatchPresenter
import com.example.ligasubmission.listDetailMatchActivity.ListDetailMatchView
import com.example.ligasubmission.listDetailMatchActivity.ListDetailMatchActivity
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

class ListDetailMatchPresenterTest {

    @Mock
    private lateinit var activity: ListDetailMatchActivity

    @Mock
    private lateinit var view : ListDetailMatchView

    @Mock
    private lateinit var gson : Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var event : Event

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: ListDetailMatchPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ListDetailMatchPresenter(activity, view, event, "", apiRepository, gson, TestContextProvider())
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