package com.desafio.picpay.feature.user_list.ui.view

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.desafio.picpay.core.data.model.User
import com.desafio.picpay.feature.user_list.R
import org.junit.Rule
import org.junit.Test

internal class UserListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val mockUsers = listOf(
        User(id = 1, name = "User 1", img = "img_1", username = "user1"),
        User(id = 2, name = "User 1", img = "img_2", username = "user2")
    )

    @Test
    fun should_show_title() {
        composeTestRule.setContent {
            Box {
                UserListScreen(users = emptyList(),
                    isLoading = false,
                    isRefreshing = false,
                    onRefresh = {})
            }
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.contatos))
            .assertExists()
    }

    @Test
    fun should_show_loading_indicator_when_loading() {
        composeTestRule.setContent {
            UserListScreen(users = emptyList(),
                isLoading = true,
                isRefreshing = false,
                onRefresh = {})
        }

        composeTestRule.onNodeWithTag("LoadingIndicator").assertExists()
    }

    @Test
    fun should_hide_loading_indicator_when_not_loading() {
        composeTestRule.setContent {
            UserListScreen(users = emptyList(),
                isLoading = false,
                isRefreshing = false,
                onRefresh = {})
        }

        composeTestRule.onNodeWithTag("LoadingIndicator").assertDoesNotExist()
    }

    @Test
    fun should_display_users_list_when_data_available() {
        composeTestRule.setContent {
            UserListScreen(users = mockUsers,
                isLoading = false,
                isRefreshing = false,
                onRefresh = {})
        }

        composeTestRule.onAllNodesWithTag("UserListItem").assertCountEquals(mockUsers.size)
    }

    @Test
    fun should_show_snackbar_host() {
        composeTestRule.setContent {
            UserListScreen(
                users = emptyList(),
                isLoading = false,
                isRefreshing = false,
                onRefresh = {},
                snackbarHostState = SnackbarHostState()
            )
        }

        composeTestRule.onNodeWithTag("SnackbarHost").assertExists()
    }

    @Test
    fun should_show_refresh_indicator_during_refresh() {
        composeTestRule.setContent {
            UserListScreen(users = emptyList(),
                isLoading = false,
                isRefreshing = true,
                onRefresh = {})
        }

        composeTestRule.onNodeWithTag("RefreshIndicator").assertExists()
    }
}