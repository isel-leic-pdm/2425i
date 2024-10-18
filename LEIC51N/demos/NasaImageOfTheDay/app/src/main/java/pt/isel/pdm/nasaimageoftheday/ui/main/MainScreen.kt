package pt.isel.pdm.nasaimageoftheday.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pt.isel.pdm.nasaimageoftheday.ui.components.AppTopBar
import pt.isel.pdm.nasaimageoftheday.ui.components.GenericErrorView
import pt.isel.pdm.nasaimageoftheday.ui.components.LoadingView
import pt.isel.pdm.nasaimageoftheday.ui.components.NavigationActions

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    aboutRequest : ()->Unit
    ) {

    var state = viewModel.state
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                navActions = NavigationActions(
                    onAboutAction = aboutRequest
                )
            )
        }

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
        {
            when (state) {
                is MainScreenState.Success ->
                    MainStateView(
                        state.nasaImage,
                        onRefresh = {
                            viewModel.fetchNasaImage()
                        }
                    )

                is MainScreenState.Error ->
                    GenericErrorView(state.exception, {
                        viewModel.fetchNasaImage()
                    })

                is MainScreenState.Loading ->
                    LoadingView()

                else ->
                    MainIdleView(loadImage = {
                        viewModel.fetchNasaImage()
                    })
            }


        }
    }
}