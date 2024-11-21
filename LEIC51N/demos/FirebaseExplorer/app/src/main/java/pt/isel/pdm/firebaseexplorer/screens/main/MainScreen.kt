package pt.isel.pdm.firebaseexplorer.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import pt.isel.pdm.firebaseexplorer.R
import pt.isel.pdm.firebaseexplorer.model.SimpleModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navigateToDetail: (SimpleModel) -> Unit,
    navigateToFlow: () -> Unit,
    navigateToRoom: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getAll()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.create()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
                },
                actions = {
                    IconButton(onClick = navigateToFlow) {
                        Icon(Icons.Default.Warning, contentDescription = "")
                    }

                    IconButton(onClick = navigateToRoom) {
                        Icon(Icons.Default.Build, contentDescription = "")
                    }
                }
            )
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {


            if (viewModel.message.isNotEmpty())
                MessageView(viewModel.message)

            ModelListView(
                data = viewModel.modelList,
                onUpdate = { viewModel.update(it) },
                onDelete = { viewModel.delete(it) },
                onRefresh = { viewModel.getAll() },
                onClicked = navigateToDetail
            )


        }


    }

    if (viewModel.error != null) {
        ErrorView(
            viewModel.error!!,
            onDismiss = { viewModel.dismissError() })
    }

    if (viewModel.loading) {
        LoadingView();
    }
}

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clickable(
                indication = null, // disable ripple effect
                interactionSource = remember { MutableInteractionSource() },
                onClick = { }
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Loading")
    }
}


@Composable
fun ErrorView(
    error: Exception,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clickable(
                indication = null, // disable animation
                interactionSource = remember { MutableInteractionSource() },
                onClick = { }
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Error")
        Text(error.toString())
        Button(onClick = onDismiss) {
            Text("Dismiss")
        }
    }
}