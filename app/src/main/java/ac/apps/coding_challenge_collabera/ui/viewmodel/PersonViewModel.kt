package ac.apps.coding_challenge_collabera.ui.viewmodel

import ac.apps.coding_challenge_collabera.data.ApiService
import ac.apps.coding_challenge_collabera.data.ApiService.personApi
import ac.apps.coding_challenge_collabera.data.PersonApi
import ac.apps.coding_challenge_collabera.data.model.Person
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class PersonViewModel: ViewModel(), CoroutineScope by MainScope() {

    companion object {
        const val TAG = "PersonViewModel"
    }

    private val _personData = MutableLiveData<List<Person>>()
    val personData: LiveData<List<Person>> get() = _personData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchPersonData(results: Int){
        launch (Dispatchers.Main) {
            try {
                val response = ApiService.personApi.getPerson(results)
                if (response.isSuccessful && response.body() != null){
                    _personData.value = response.body()?.results
                    Log.d(TAG, _personData.value.toString())

                } else {
                    Log.d(TAG, "Server Error")
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception){
                e.message?.let { Log.d(TAG, "Exception $it") }
                _error.value = "Exception: ${e.message}"
            }
        }
    }
}