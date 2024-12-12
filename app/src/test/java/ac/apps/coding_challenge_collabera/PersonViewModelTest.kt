package ac.apps.coding_challenge_collabera

import ac.apps.coding_challenge_collabera.data.ApiService
import ac.apps.coding_challenge_collabera.data.datasource.ApiResponse
import ac.apps.coding_challenge_collabera.data.model.Coordinates
import ac.apps.coding_challenge_collabera.data.model.Location
import ac.apps.coding_challenge_collabera.data.model.Name
import ac.apps.coding_challenge_collabera.data.model.Person
import ac.apps.coding_challenge_collabera.data.model.Picture
import ac.apps.coding_challenge_collabera.data.model.Street
import ac.apps.coding_challenge_collabera.data.model.Timezone
import ac.apps.coding_challenge_collabera.ui.viewmodel.PersonViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
class PersonViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule() //for live data

    @Mock
    lateinit var apiService: ApiService

    private lateinit var viewModel: PersonViewModel


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = PersonViewModel(apiService)
    }

    @Test
    fun `test successful API response`() = runTest {
        // mock successful API response
        val mockPersonData = listOf(TestData.mockPerson)
        // Create an ApiResponse wrapper with the mock data
        val mockApiResponse = ApiResponse(mockPersonData)
        val mockResponse = Response.success(mockApiResponse)

        //mock the api service to return successful response
        Mockito.`when`(apiService.personApi.getPerson(10)).thenReturn(mockResponse)

        // call the fetchPersonData function
        viewModel.fetchPersonData(10)

        val actualPersonData = viewModel.personData.getOrAwaitValue()
        assertEquals(mockPersonData, actualPersonData)
    }

    object TestData {
        val mockPerson = Person("Male",
            Name("Mr", "John", "Doe"),
            Location(
                Street(123, "Main St"),
                "UK",
                "England",
                "UK",
                "SW1A 1AA",
                Coordinates("1.0", "2.0"),
                Timezone("0", "description"),
            ),
            "john.c.breckinridge@altostrat.com",
            "12345",
            "12345",
            Picture("test.jpg", "test.jpg", "test.jpg"),
            "UK"
            )
    }

}