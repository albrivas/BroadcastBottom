package com.albrivas.uikit.text

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.albrivas.uikit.R
import com.albrivas.uikit.theme.BluePrimaryDark
import com.albrivas.uikit.theme.ubuntuFamily

@Composable
fun TextButton(@StringRes text: Int, colorText: Color) {
    Text(
        text = stringResource(id = text),
        color = colorText,
        fontFamily = ubuntuFamily,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Preview
@Composable
fun ShowTextButton() {
    MaterialTheme {
        TextButton(text = R.string.fab_transformation_sheet_behavior, BluePrimaryDark)
    }
}