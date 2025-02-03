package com.desafio.picpay.feature.user_list.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desafio.picpay.core.common.Result
import com.desafio.picpay.core.common.asResult
import com.desafio.picpay.core.data.model.User
import com.desafio.picpay.core.domain.use_case.GetUserListUseCase
import com.desafio.picpay.core.domain.use_case.SyncUserListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getUserListUseCase: GetUserListUseCase,
    private val syncUserListUseCase: SyncUserListUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<UserListUiState> =
        MutableStateFlow(UserListUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init {
        syncUserList()
        observeUserList()
    }

    fun syncUserList() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRequestingData = true) }
            runCatching {
                syncUserListUseCase()
            }.fold(onSuccess = {
                _uiState.update { it.copy(isRequestingData = false) }
            }, onFailure = {
                _uiState.update { it.copy(isError = true, isRequestingData = false) }
            })
        }
    }

    private fun observeUserList() {
        viewModelScope.launch {
            getUserListUseCase().asResult().collect { result ->
                _uiState.update {
                    when (result) {
                        is Result.Error -> it.copy(isError = true, isLoading = false)
                        Result.Loading -> it.copy(isLoading = true, isError = false)
                        is Result.Success -> it.copy(
                            users = result.data, isLoading = false, isError = false
                        )
                    }
                }
            }
        }
    }

    fun onShowError() {
        viewModelScope.launch {
            _uiState.update { it.copy(isError = false) }
        }
    }
}

data class UserListUiState(
    val users: List<User> = emptyList(),
    val isRequestingData: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)