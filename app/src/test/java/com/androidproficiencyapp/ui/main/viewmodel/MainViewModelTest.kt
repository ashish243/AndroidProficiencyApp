package com.androidproficiencyapp.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.androidproficiencyapp.data.api.ApiHelper
import com.androidproficiencyapp.data.model.Rows
import com.androidproficiencyapp.utils.Resource
import com.androidproficiencyapp.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: ApiHelper

    @Mock
    private lateinit var apiRowObserver: Observer<Resource<List<Rows>>>

    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<Rows>())
                .`when`(apiHelper)
                .getList()
            val viewModel = MainViewModel(apiHelper)
            viewModel.getList().observeForever(apiRowObserver)
            verify(apiHelper).getList()
            verify(apiRowObserver).onChanged(Resource.success(emptyList()))
            viewModel.getList().removeObserver(apiRowObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doThrow(RuntimeException(errorMessage))
                .`when`(apiHelper)
                .getList()
            val viewModel = MainViewModel(apiHelper)
            viewModel.getList().observeForever(apiRowObserver)
            verify(apiHelper).getList()
            verify(apiRowObserver).onChanged(
                Resource.error(
                    null,
                    RuntimeException(errorMessage).toString()

                )
            )
            viewModel.getList().removeObserver(apiRowObserver)
        }
    }
    @After
    fun tearDown() {
        // do something if required
    }
}