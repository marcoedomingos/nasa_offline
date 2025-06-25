package ao.marco.kotlin.nasaoffline.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ao.marco.kotlin.nasaoffline.features.home.domain.entity.PlanetaryApodEntity
import ao.marco.kotlin.nasaoffline.features.home.domain.repository.HomeRepository
import ao.marco.kotlin.nasaoffline.helpers.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _apodState = MutableStateFlow<DataUiState<PlanetaryApodEntity>>(DataUiState.Loading)
    val apodState: StateFlow<DataUiState<PlanetaryApodEntity>> get() = _apodState

    init {
        fetchPlanetaryApod()
    }

    private fun fetchPlanetaryApod() {
        viewModelScope.launch {
            homeRepository.planetaryApod()
                .onStart {
                    _apodState.value = DataUiState.Loading
                }
                .catch { e ->
                    _apodState.value = DataUiState.Error(e)
                }
                .collect { entity ->
                    _apodState.value = DataUiState.Success(entity)
                }
        }
    }
}
