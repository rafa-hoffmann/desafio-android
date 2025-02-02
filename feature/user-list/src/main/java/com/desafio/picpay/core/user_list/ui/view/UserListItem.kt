package com.desafio.picpay.core.user_list.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import com.desafio.picpay.core.designsystem.theme.PicPayTheme
import com.desafio.picpay.feature.user_list.R

@Composable
internal fun UserListItem(
    modifier: Modifier = Modifier,
    img: String,
    username: String,
    name: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                .size(54.dp)
                .clip(CircleShape),
            model = img,
            contentDescription = stringResource(R.string.imagem_de_usuario)
        ) {
            val state by painter.state.collectAsState()
            if (state is AsyncImagePainter.State.Success) {
                SubcomposeAsyncImageContent(
                    modifier = Modifier
                )
            } else {
                CircularProgressIndicator()
            }
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = username, style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.tertiary)
            )
        }
    }
}

@Preview
@Composable
private fun UserListItemPreview() {
    PicPayTheme {
        UserListItem(
            img = "",
            username = "username",
            name = "name"
        )
    }
}