@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.jeckpackcomposecatalog.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter") //esto se agrego para evitar conflictos en scaffold
@Composable
fun ScaffoldExample() {
    val scaffoldState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()


    Scaffold(//surface es para agregar la elevation o shadow
            topBar = { Surface(shadowElevation = 30.dp) { MyTopAppBar { coroutineScope.launch { scaffoldState.showSnackbar("Has pulsado $it") } } } },
            snackbarHost = { SnackbarHost(scaffoldState) }
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(onClickIcon: (String) -> Unit) { //funcion lambda
    TopAppBar(
            title = { Text(text = "Mi primera toolbar") },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White,
            ),
            navigationIcon = {
                IconButton(onClick = { onClickIcon("Atras") }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            actions = { //botones que van a final
                IconButton(onClick = { onClickIcon("Buscar") }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
                }
                IconButton(onClick = { onClickIcon("cerrar") }) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "close")
                }
            }
    )
}