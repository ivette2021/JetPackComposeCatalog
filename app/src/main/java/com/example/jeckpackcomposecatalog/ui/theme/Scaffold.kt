@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.jeckpackcomposecatalog.ui.theme

import android.annotation.SuppressLint
import android.text.Layout.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter") //esto se agrego para evitar conflictos en scaffold
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = { MyTopAppBar { coroutineScope.launch { snackBarHostState.showSnackbar("Has pulsado $it") } } },
        bottomBar = { MyBottomNavigation() },
        floatingActionButton = { MyFAB() },
        floatingActionButtonPosition = FabPosition.Center,
    ) {

    }
}

@Composable
fun MyFAB() {
    FloatingActionButton(onClick = { }, containerColor = Color.Blue, contentColor = Color.White) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
    }
}

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

@Composable
fun MyBottomNavigation() {
    var index by remember { mutableStateOf(0) }


    NavigationBar(
        containerColor = Color.Red,

        ) {
        NavigationBarItem(
            selected = index == 0,
            onClick = { index = 0 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "home"
                )
            }, label = {
                //Text(text = "Inicio", color = Color.White) //dejas por defecto un color ,configuras solo el icono no el color texto
                Text(text = "Inicio")
            },
            //colors = NavigationBarItemDefaults.colors(Color.Blue)
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Blue,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.Blue,
                unselectedTextColor = Color.White
            )
        )
        NavigationBarItem(
            selected = index == 1,
            onClick = { index = 1 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "fav"
                )
            }, label = {
                Text(text = "FAV")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Blue,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.Blue,
                unselectedTextColor = Color.White
            )
        )
        NavigationBarItem(
            selected = index == 2,
            onClick = { index = 2 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "user"
                )
            }, label = { Text(text = "User") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Blue,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.Blue,
                unselectedTextColor = Color.White
            )
        )
    }
}