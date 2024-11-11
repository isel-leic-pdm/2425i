package pt.isel.pdm.firebaseexplorer.screens.helpers

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner

@Suppress("UNCHECKED_CAST")
fun <T> viewModelInit(block: () -> T) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return block() as T
        }
    }



fun <T> SavedStateRegistryOwner.initWithSavedState(
    block: (SavedStateHandle) -> T
) = initWithSavedStateRegistryOwner(this, block)

@Suppress("UNCHECKED_CAST")
fun <T> initWithSavedStateRegistryOwner(
    owner: SavedStateRegistryOwner,
    block: (SavedStateHandle) -> T
) =
    object : AbstractSavedStateViewModelFactory(owner, null) {
        override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return block(handle) as T
        }
    }