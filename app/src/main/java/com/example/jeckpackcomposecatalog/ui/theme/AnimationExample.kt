package com.example.jeckpackcomposecatalog.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorAnimationSimple() {
    var firstColor by rememberSaveable {
        mutableStateOf(false)
    }
    var showBox by rememberSaveable {
        mutableStateOf(true)
    }
    val realColor by animateColorAsState(targetValue = if (firstColor) Color.Red else Color.Yellow,
        animationSpec = tween(durationMillis = 2000),//2 segubdos para hacer una transicion mas lenta
        finishedListener = { showBox = false } //desaparesca
    )
    if (showBox) {
        Box(modifier = Modifier
            .size(100.dp)
            .background(realColor)
            .clickable { firstColor = !firstColor })
    }
}

@Composable
fun SizeAnimation() {
    var smallSize by rememberSaveable {
        mutableStateOf(true)
    }
    val size by animateDpAsState(
        targetValue = if (smallSize) 50.dp else 100.dp,
        animationSpec = tween(durationMillis = 500)
    )
    Box(modifier = Modifier
        .size(size)
        .background(Color.Cyan)
        .clickable { smallSize = !smallSize })
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VisibilityAnimation() {
    var isVisible by remember { mutableStateOf(true) }

    Column(Modifier.fillMaxSize()) {
        Button(onClick = { isVisible = !isVisible }) {
            Text("Mostrar/ocultar")
        }
        Spacer(modifier = Modifier.size(30.dp))

        AnimatedVisibility(isVisible, enter = slideInVertically(), exit = slideOutVertically()) {
            Box(
                Modifier
                    .size(100.dp)
                    .background(Color.Red))

        }

        Spacer(modifier = Modifier.size(30.dp))
        AnimatedVisibility(isVisible, enter = slideInHorizontally(), exit = slideOutHorizontally()) {
            Box(
                Modifier
                    .size(100.dp)
                    .background(Color.Red))

        }
        Spacer(modifier = Modifier.size(30.dp))
        AnimatedVisibility(isVisible, enter = scaleIn(), exit = scaleOut()) {
            Box(
                Modifier
                    .size(100.dp)
                    .background(Color.Red))

        }
        Spacer(modifier = Modifier.size(30.dp))
        AnimatedVisibility(isVisible, enter = fadeIn(), exit = fadeOut()) {
            Box(
                Modifier
                    .size(100.dp)
                    .background(Color.Red))

        }

    }
}