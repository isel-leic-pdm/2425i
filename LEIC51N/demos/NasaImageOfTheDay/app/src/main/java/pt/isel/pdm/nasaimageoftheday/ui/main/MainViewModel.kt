package pt.isel.pdm.nasaimageoftheday.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.pdm.nasaimageoftheday.model.NasaImage
import pt.isel.pdm.nasaimageoftheday.services.NasaImageOfTheDayService

sealed interface MainScreenState {
    data object Idle : MainScreenState
    data object Loading : MainScreenState
    data class Success(val nasaImage: NasaImage) : MainScreenState
    data class Error(val exception: Throwable) : MainScreenState
}

class MainViewModel(
    private val nasaService: NasaImageOfTheDayService
) : ViewModel() {

    var state by mutableStateOf<MainScreenState>(MainScreenState.Idle)

    fun fetchNasaImage() {
        state = MainScreenState.Loading
        viewModelScope.launch {
            try {
                val nasaImage = nasaService.getImageOfTheDay()
                state = MainScreenState.Success(nasaImage)
            } catch (error: Exception) {
                state =  MainScreenState.Error(error)
            }

        }
    }


}