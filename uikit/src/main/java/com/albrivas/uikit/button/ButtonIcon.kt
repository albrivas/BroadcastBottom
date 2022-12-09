package com.albrivas.uikit.button

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.albrivas.uikit.R
import com.albrivas.uikit.text.TextButton
import com.albrivas.uikit.theme.BluePrimary
import com.albrivas.uikit.theme.BluePrimaryDark

@Composable
fun ButtonChooseLogin(
    type: LoginTypeModel,
    @StringRes textButton: Int,
    onClickAction: (Context) -> Unit
) {
    val context = LocalContext.current
    Button(
        onClick = { onClickAction(context) },
        shape = CircleShape,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(),
        colors = androidx.compose.material.ButtonDefaults.buttonColors(
            backgroundColor = getColorBackgroundButton(type)
        ),
        border = BorderStroke(0.dp, getColorBorderButton(type))
    ) {
        IconButtonChooseLogin(type = type)
        if (type != LoginTypeModel.USER_PASS)
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        TextButtonChooseLogin(type = type, textButton = textButton)
    }
}

@Composable
fun IconButtonChooseLogin(type: LoginTypeModel) {
    when (type) {
        LoginTypeModel.GOOGLE -> Icon(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = "Button ${type.name}",
            modifier = Modifier.size(ButtonDefaults.IconSize),
            tint = Color.Unspecified
        )
        LoginTypeModel.FACEBOOK -> Icon(
            painter = painterResource(id = R.drawable.ic_facebook),
            contentDescription = "Button ${type.name}",
            modifier = Modifier.size(ButtonDefaults.IconSize),
            tint = Color.Unspecified
        )
        else -> { /* Nothing here */ }
    }
}

@Composable
fun TextButtonChooseLogin(type: LoginTypeModel, @StringRes textButton: Int) {
    when (type) {
        LoginTypeModel.GOOGLE, LoginTypeModel.FACEBOOK -> TextButton(
            text = textButton,
            colorText = BluePrimaryDark
        )
        LoginTypeModel.USER_PASS -> TextButton(
            text = textButton,
            colorText = Color.White
        )
    }
}

private fun getColorBackgroundButton(type: LoginTypeModel): Color {
    return if (type == LoginTypeModel.USER_PASS)
        BluePrimary
    else
        Color.White
}

private fun getColorBorderButton(type: LoginTypeModel): Color {
    return if (type == LoginTypeModel.USER_PASS)
        BluePrimary
    else
        Color.DarkGray
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    widthDp = 300,
    heightDp = 70,
    device = Devices.PIXEL_4
)
@Composable
fun ShowButtonPreview() {
    MaterialTheme {
        ButtonChooseLogin(
            type = LoginTypeModel.USER_PASS,
            textButton = R.string.bottom_sheet_behavior
        ) { /* Nothing here */ }
    }
}