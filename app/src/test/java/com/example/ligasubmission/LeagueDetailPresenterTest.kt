package com.example.ligasubmission
import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.leagueDetailActivity.LeagueDetailPresenter
import com.example.ligasubmission.leagueDetailActivity.LeagueDetailView
import com.example.ligasubmission.model.League
import com.example.ligasubmission.model.LeaguesResponse
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

class LeagueDetailPresenterTest {

    @Mock
    private lateinit var view : LeagueDetailView

    @Mock
    private lateinit var gson : Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: LeagueDetailPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LeagueDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }


    @Test
    fun testGetLeagueDetail(){

        val leagues: MutableList<League> = mutableListOf()
        val response = LeaguesResponse(leagues)
        val league = "4328"

        runBlocking {

            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("",LeaguesResponse::class.java)).thenReturn(response)

            presenter.getLeagueDetail(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showLeagueDetail(leagues)
            Mockito.verify(view).hideLoading()

        }

    }

}