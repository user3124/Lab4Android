package com.example.bookbrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.bookbrowser.ui.navigation.AppNavigation
import com.example.bookbrowser.ui.theme.BookBrowserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookBrowserTheme {
                var showDebugDialog by remember { mutableStateOf(false) }
                var serverUrl by remember { mutableStateOf(BuildConfigHelper.API_BASE_URL) }

                Box(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    AppNavigation(navController = navController)

                    if (BuildConfigHelper.IS_DEBUG) {
                        Button(
                            onClick = { showDebugDialog = true },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp)
                        ) {
                            Text("Панель/Тест")
                        }
                    }
                }

                if (showDebugDialog) {
                    AlertDialog(
                        onDismissRequest = { showDebugDialog = false },
                        title = { Text("Экран тестировщика") },
                        text = {
                            OutlinedTextField(
                                value = serverUrl,
                                onValueChange = { serverUrl = it },
                                label = { Text("Параметр: API_BASE_URL") }
                            )
                        },
                        confirmButton = {
                            Button(onClick = { showDebugDialog = false }) {
                                Text("Сохранить")
                            }
                        }
                    )
                }
            }
        }
    }
}
