@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.scheduler.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.romka_po.scheduler.utils.TimestampConverter

@Composable
fun InsertDateTimeField(
    value: String,
    label: String = "",
    trailingIcon: @Composable (() -> Unit)? = null,
    provide: (Long) -> Unit
) {
    val dialogState = remember {
        mutableStateOf(false)
    }

    val timePickerState = if (value=="") rememberTimePickerState() else rememberTimePickerState(initialHour = value.substring(11, 13).toInt(), initialMinute = value.substring(14).toInt())
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    if (value!="")
        datePickerState.setSelection(TimestampConverter.convertStringToTimestamp(value))


    if (dialogState.value) {
        Dialog(
            onDismissRequest = { dialogState.value = false }
        ) {
            TimeDateDialog(
                dialogState = dialogState,
                label = label,
                onValueChange = provide,
                timePickerState,
                datePickerState
            )
        }
    }
    OutlinedTextField(
        value = value,
        onValueChange = {},
        label = { Text(text = label) },
        trailingIcon = trailingIcon,
        readOnly = true,
        modifier = Modifier.clickable {
            dialogState.value = true
        },
        enabled = false,
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = MaterialTheme.colorScheme.outline,
            disabledLabelColor = MaterialTheme.colorScheme.outline,
            disabledTrailingIconColor = MaterialTheme.colorScheme.outline
        )
    )
}