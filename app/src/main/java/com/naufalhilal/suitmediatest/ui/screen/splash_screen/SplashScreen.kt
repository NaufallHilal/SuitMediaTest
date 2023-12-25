package com.naufalhilal.suitmediatest.ui.screen.splash_screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier=Modifier,
                 navigateToFirst:()->Unit,
                 ) {
    val scale= remember{
        Animatable(0f)
    }
    val startColor = Color(red = 184, green = 255, blue = 253)
    val endColor = Color(red = 0, green = 42, blue = 40)

    val gradientBrush = Brush.linearGradient(
        colors = listOf(startColor, endColor)
    )
    LaunchedEffect(key1 = true ){
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
        delay(2000L)
        navigateToFirst()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Text(text = "SuitMediaApp", style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 24.sp), modifier = modifier.padding(top = 8.dp).scale(scale.value), color = Color.White)
        }
    }
}