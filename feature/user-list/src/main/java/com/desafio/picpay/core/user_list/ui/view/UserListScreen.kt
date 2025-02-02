package com.desafio.picpay.core.user_list.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.desafio.picpay.core.data.model.User
import com.desafio.picpay.core.designsystem.theme.PicPayTheme
import com.desafio.picpay.core.user_list.ui.viewModel.UserListViewModel
import com.desafio.picpay.feature.user_list.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserListRoute(viewModel: UserListViewModel = koinViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val errorMessage = stringResource(R.string.falha_ao_atualizar_lista_de_usuarios)

    LaunchedEffect(state) {
        if (state.isError) {
            snackbarHostState.showSnackbar(errorMessage)
            viewModel.onShowError()
        }
    }

    UserListScreen(
        users = state.users,
        isLoading = state.isLoading,
        snackbarHostState = snackbarHostState,
        onRefresh = viewModel::syncUserList,
        isRefreshing = state.isRequestingData
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserListScreen(
    users: List<User>,
    isLoading: Boolean,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    Scaffold(snackbarHost = {
        SnackbarHost(snackbarHostState)
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(
                modifier = Modifier.padding(start = 24.dp, top = 48.dp),
                text = stringResource(R.string.contatos),
                style = MaterialTheme.typography.titleLarge
            )

            PullToRefreshBox(isRefreshing = isRefreshing, onRefresh = onRefresh) {
                LazyColumn {
                    if (isLoading) {
                        item {
                            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                        }
                    }

                    items(users, key = { it.id }) {
                        UserListItem(
                            modifier = Modifier.animateItem(),
                            img = it.img,
                            username = it.username,
                            name = it.name
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun UserListScreenPreview() {
    PicPayTheme {
        UserListScreen(
            users = listOf(
                User(
                    img = "", name = "Rosanne Grant", id = 8579, username = "Ines McIntosh"
                ), User(
                    img = "", name = "Harley Wilkins", id = 7862, username = "Claire Ramirez"
                ), User(img = "", name = "Ariel Puckett", id = 9640, username = "Ian Sheppard")
            ),
            isLoading = true,
            onRefresh = {},
            isRefreshing = false
        )
    }
}