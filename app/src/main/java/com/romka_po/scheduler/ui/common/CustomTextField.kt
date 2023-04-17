@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.scheduler.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.romka_po.Scheduler.R

@Composable
fun CustomInputField(
    value: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    onFocusChange: (FocusState) -> Unit,
    onValueChange: (String) -> Unit,
    textColor: Color = Color.Black,
    singleLine:Boolean = false,
    readOnly:Boolean = false,
    enabled:Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    val touched = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = value,
        onValueChange = {
            touched.value = true
            onValueChange(it)
        },
        modifier = modifier.onFocusChanged {
            if (touched.value) onFocusChange(it)
        }.clickable  {
            onClick?.let { it() }
                    },
        label = {
            Text(
                text = placeholder,
                style = TextStyle(
                    textAlign = TextAlign.Center
                )
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = textColor,
            focusedBorderColor = Color.Green,
            unfocusedBorderColor = Color.LightGray,
        ),

        shape = RoundedCornerShape(20.dp),
        singleLine = singleLine,
        readOnly = readOnly,
        trailingIcon = leadingIcon,
        enabled = enabled
    )
}

@Preview
@Composable
fun testTextField(){
    val screenState = remember {
        mutableStateOf("")
    }
    CustomInputField(
        value = screenState.value,
        placeholder = "startdatetime",
        onFocusChange = {
                        },
        onValueChange = {
        },
        readOnly = true,
        leadingIcon = { Icon(painterResource(R.drawable.event_24dp), contentDescription = "Hello") }
    )
}