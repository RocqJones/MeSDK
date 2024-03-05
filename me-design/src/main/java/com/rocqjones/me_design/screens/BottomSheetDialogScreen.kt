package com.rocqjones.me_design.screens

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rocqjones.me_design.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDialogScreen(navController: NavHostController) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show bottom sheet") },
                icon = { Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.icon)) },
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) { it
        // Screen content
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                var selectedRowOption by remember { mutableStateOf(0) } // Track selected option (default 0)
                var selectedColumnOption by remember { mutableStateOf(0) } // Track selected option (default 0)

                // Sheet content
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Select row option:")

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedRowOption == 0,
                            onClick = { selectedRowOption = 0 }
                        )
                        Text("Option 1")
                        Spacer(modifier = Modifier.width(16.dp)) // Add spacing between options

                        RadioButton(
                            selected = selectedRowOption == 1,
                            onClick = { selectedRowOption = 1 }
                        )
                        Text("Option 2")
                        Spacer(modifier = Modifier.width(16.dp)) // Add spacing between options

                        RadioButton(
                            selected = selectedRowOption == 2,
                            onClick = { selectedRowOption = 2 }
                        )
                        Text("Option 3")
                    }

                    Text("Select column option:")
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = selectedColumnOption == 0,
                                onClick = { selectedColumnOption = 0 }
                            )
                            Text("Option 1")
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = selectedColumnOption == 1,
                                onClick = { selectedColumnOption = 1 }
                            )
                            Text("Option 2")
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = selectedColumnOption == 2,
                                onClick = { selectedColumnOption = 2 }
                            )
                            Text("Option 3")
                        }
                    }

                    Button(
                        onClick = {
                            scope.launch {
                                sheetState.hide()
                            }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        }) {
                        Text("Hide bottom sheet")
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}