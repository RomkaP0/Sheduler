@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.romka_po.sheduler

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.romka_po.sheduler.ui.theme.ShedulerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShedulerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
                        FloatingActionButton(onClick = {
                            Toast.makeText(applicationContext, "click", Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Add Event",
                            )
                        }
                    }, content = { padding ->
                        Column(
                            modifier = Modifier
                                .padding(padding)
                        ) {
                            Text("Товар добавлен", fontSize = 28.sp)
                        }
                    }
                    )
                }
            }
        }
    }

}
@Preview()
@Composable
fun DefaultPreview() {
    ShedulerTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
                FloatingActionButton(onClick = {
//                    Toast.makeText(applicationContext, "click", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add Event",
                    )
                }
            }, content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    Text("Товар добавлен", fontSize = 28.sp)
                }
            }
            )
        }
    }
}