package com.albrivas.uikit.text

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.albrivas.uikit.theme.ubuntuFamily
import com.albrivas.uikit.R
import com.albrivas.uikit.theme.BluePrimaryDark

@Composable
fun TitleText(@StringRes text: Int) {
    Text(
        text = stringResource(id = text),
        color = BluePrimaryDark,
        fontFamily = ubuntuFamily,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        fontSize = 24.sp
    )
}

@Preview
@Composable
fun ShowTextTitle() {
    MaterialTheme {
        TitleText(text = R.string.fab_transformation_sheet_behavior)
    }
}