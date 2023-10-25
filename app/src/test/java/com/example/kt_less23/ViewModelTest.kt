package com.example.kt_less23


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mockito.*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.kt_less23.api_service.Data
import com.example.kt_less23.api_service.Model
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import retrofit2.Response

class ViewModelTest {
    private lateinit var viewModel: ViewModel
    private val repository = mock(Repository::class.java)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ViewModel(repository)
    }

    @Test
    fun `test successful data retrieval`() = testDispatcher.runBlockingTest {
        val response = Response.success(Model(Data("bitcoin", "34570.97", "₿")))
        `when`(repository.getCrypto("bitcoin")).thenReturn(response)
        viewModel.getData()
        verify(repository, times(1)).getCrypto("bitcoin")
        val uiState = viewModel.uiState.value
        assert(uiState is ViewModel.UIState.Result)
        val result = (uiState as ViewModel.UIState.Result).title
        assert(result == "bitcoin ₿ 34570.97")
    }

    @Test
    fun `test unsuccessful data retrieval`() = testDispatcher.runBlockingTest {
        val errorResponse = Response.error<Model>(404, ResponseBody.create(null, "Not Found"))
        `when`(repository.getCrypto("bitcoin")).thenReturn(errorResponse)
        viewModel.getData()
        verify(repository, times(1)).getCrypto("bitcoin")
        val uiState = viewModel.uiState.value
        assert(uiState is ViewModel.UIState.Error)
        val errorDescription = (uiState as ViewModel.UIState.Error).description
        assert(errorDescription == "Error code 404")
    }
}