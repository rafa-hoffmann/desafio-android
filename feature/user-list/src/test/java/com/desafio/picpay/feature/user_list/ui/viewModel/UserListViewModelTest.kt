package com.desafio.picpay.feature.user_list.ui.viewModel

import com.desafio.picpay.core.domain.use_case.GetUserListUseCase
import com.desafio.picpay.core.domain.use_case.SyncUserListUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class UserListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val getUserListUseCase = mockk<GetUserListUseCase>()
    private val syncUserListUseCase = mockk<SyncUserListUseCase>()
    private lateinit var viewModel: UserListViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should trigger sync and observe users`() = runTest {
        coEvery { getUserListUseCase.invoke() } returns flow { emit(fakeUserList) }
        coEvery { syncUserListUseCase.invoke() } returns Unit
        viewModel = UserListViewModel(getUserListUseCase, syncUserListUseCase)

        testDispatcher.scheduler.advanceUntilIdle()

        coVerify(exactly = 1) { syncUserListUseCase() }
        coVerify(exactly = 1) { getUserListUseCase() }
    }

    @Test
    fun `syncUserList should update state correctly on success`() = runTest {
        coEvery { getUserListUseCase.invoke() } returns flow { emit(fakeUserList) }
        coEvery { syncUserListUseCase.invoke() } returns Unit
        viewModel = UserListViewModel(getUserListUseCase, syncUserListUseCase)

        viewModel.syncUserList()
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(false, state.isRequestingData)
        assertEquals(false, state.isError)
    }

    @Test
    fun `syncUserList should handle errors properly`() = runTest {
        coEvery { getUserListUseCase.invoke() } returns flow { emit(fakeUserList) }
        coEvery { syncUserListUseCase.invoke() } throws RuntimeException("Test error")
        viewModel = UserListViewModel(getUserListUseCase, syncUserListUseCase)

        viewModel.syncUserList()
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(true, state.isError)
        assertEquals(false, state.isRequestingData)
    }

    @Test
    fun `observeUserList should update state for loading`() = runTest {
        coEvery { getUserListUseCase.invoke() } returns flow { emit(fakeUserList) }
        coEvery { syncUserListUseCase.invoke() } returns Unit
        viewModel = UserListViewModel(getUserListUseCase, syncUserListUseCase)

        assertEquals(true, viewModel.uiState.value.isLoading)
    }

    @Test
    fun `observeUserList should update state for success`() = runTest {
        coEvery { getUserListUseCase.invoke() } returns flow { emit(fakeUserList) }
        coEvery { syncUserListUseCase.invoke() } returns Unit
        viewModel = UserListViewModel(getUserListUseCase, syncUserListUseCase)

        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(fakeUserList, viewModel.uiState.value.users)
    }

    @Test
    fun `onShowError should clear error state`() = runTest {
        coEvery { getUserListUseCase.invoke() } returns flow { emit(fakeUserList) }
        coEvery { syncUserListUseCase.invoke() } returns Unit
        viewModel = UserListViewModel(getUserListUseCase, syncUserListUseCase)

        viewModel.syncUserList()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.onShowError()
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(false, viewModel.uiState.value.isError)
    }
}