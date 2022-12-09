package com.albrivas.uikit.text

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.albrivas.uikit.R
import com.albrivas.uikit.theme.BluePrimaryDark
import com.albrivas.uikit.theme.ubuntuFamily

@Composable
fun LabelLinkText(@StringRes text: Int, onClickText: (Context) -> Unit) {
    val context = LocalContext.current

    ClickableText(
        text = AnnotatedString(context.getString(text)),
        onClick = { onClickText(context) },
        style = TextStyle(
            color = BluePrimaryDark,
            fontFamily = ubuntuFamily,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        ),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Preview
@Composable
fun ShowLabelLinkText() {
    MaterialTheme {
        LabelLinkText(text = R.string.appbar_scrolling_view_behavior) { /* Nothing here */ }
    }
}