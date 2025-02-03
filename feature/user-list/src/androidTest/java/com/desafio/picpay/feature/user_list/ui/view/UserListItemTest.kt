package com.desafio.picpay.feature.user_list.ui.view

import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.annotation.DelicateCoilApi
import coil3.test.FakeImage
import coil3.test.FakeImageLoaderEngine
import com.desafio.picpay.feature.user_list.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class UserListItemTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @OptIn(DelicateCoilApi::class)
    @Before
    fun before() {
        val engine = FakeImageLoaderEngine.Builder()
            .intercept(
                { it is String && it == "success_test" },
                FakeImage(color = Color.Red.toArgb())
            )
            .build()
        val imageLoader = ImageLoader.Builder(context)
            .components { add(engine) }
            .build()
        SingletonImageLoader.setUnsafe(imageLoader)
    }

    @Test
    fun should_display_all_user_information() {
        composeTestRule.setContent {
            UserListItem(
                img = "https://image.url/image.jpg",
                username = "User 1",
                name = "User Name"
            )
        }

        composeTestRule.onNodeWithTag("UserImage").assertExists()
        composeTestRule.onNodeWithText("User 1").assertExists()
        composeTestRule.onNodeWithText("User Name").assertExists()
    }

    @Test
    fun should_show_loading_indicator_while_image_loading() {
        composeTestRule.setContent {
            UserListItem(
                img = "loading_test",
                username = "test",
                name = "Test"
            )
        }

        composeTestRule.onNodeWithTag("ImageLoadingIndicator").assertExists()
    }

    @Test
    fun should_display_image_when_loaded() {
        composeTestRule.setContent {
            UserListItem(
                img = "success_test",
                username = "test",
                name = "Test"
            )
        }

        composeTestRule.onNodeWithTag("UserImageContent").assertExists()
    }

    @Test
    fun should_have_proper_content_description() {
        composeTestRule.setContent {
            UserListItem(
                img = "success_test",
                username = "test",
                name = "Test"
            )
        }

        composeTestRule.onNodeWithContentDescription(
            context.getString(R.string.imagem_de_usuario)
        ).assertExists()
    }
}