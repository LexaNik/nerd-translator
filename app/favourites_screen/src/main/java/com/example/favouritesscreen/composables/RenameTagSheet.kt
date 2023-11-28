package com.example.favouritesscreen.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.favouritesscreen.R
import com.example.favouritesscreen.FavouritesScreenContract
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.TextFieldValue
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

private val labelMargins = PaddingValues(top = 10.dp, bottom = 6.dp)
private val layoutMargins = PaddingValues(bottom = 18.dp)
private val textMargins = PaddingValues(15.dp, 10.dp, 15.dp, 10.dp)
private val borderColor = Color(0xFFF2575D)
private val roundingSize = 8.dp
private val buttonColorPrimaryNormal = Color(0xFFF2575D)
private val buttonTextPaddings = PaddingValues(30.dp, 15.dp, 30.dp, 15.dp)

@Composable
private fun titleTextStyle(textColor: Color = MaterialTheme.colorScheme.primary) =
    MaterialTheme.typography.titleLarge.copy(
        textAlign = TextAlign.Center,
        color = textColor,
    )

@Composable
private fun textStyle(defaultTextColor: Color = MaterialTheme.colorScheme.primary) =
    MaterialTheme.typography.bodyLarge.copy(
        color = defaultTextColor
    )

@Composable
private fun doneButtonTextStyle() = MaterialTheme.typography.titleSmall.copy(
    color = MaterialTheme.colorScheme.background,
)

@Composable
private fun doneButtonColors() = ButtonDefaults.buttonColors(
    containerColor = buttonColorPrimaryNormal
)

@Composable
fun RenameTagHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.rename_tag_image),
            contentDescription = stringResource(R.string.rename_tag_image),
        )

        Text(
            modifier = Modifier.padding(labelMargins),
            text = stringResource(R.string.rename_tag_title),
            style = titleTextStyle()
        )
    }
}

@Composable
fun RenameTagInput() {
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    ConstraintLayout(
        modifier = Modifier
            .padding(layoutMargins)
    ) {
        Box(
            modifier = Modifier
                .width(286.dp)
                .height(48.dp)
                .border(1.dp, borderColor, RoundedCornerShape(roundingSize))
                .background(
                    MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(roundingSize)
                ),
        ) {
            BasicTextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                textStyle = textStyle(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(textMargins),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.tertiary),
            )
        }
    }
}

@Composable
fun DoneButton(onCreateButtonClick: () -> Unit) {
    Button(
        onClick = { onCreateButtonClick() },
        contentPadding = buttonTextPaddings,
        shape = RoundedCornerShape(size = 32.dp),
        modifier = Modifier
            .width(110.dp)
            .height(48.dp),
        colors = doneButtonColors()
    ) {
        Text(
            text = stringResource(R.string.done_button_name),
            modifier = Modifier
                .width(50.dp)
                .height(18.dp),
            style = doneButtonTextStyle(),
        )
    }
}

@Composable
fun RenameTagSheet(onEventSent: (event: FavouritesScreenContract.Event) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        RenameTagHeader()
        RenameTagInput()
        DoneButton { onEventSent(FavouritesScreenContract.Event.DoneButtonActionClick) }
    }
}
