package com.naufalhilal.suitmediatest.ui.screen.first_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(modifier: Modifier=Modifier, navigateToSecond:(String)->Unit) {
    val startColor = Color(red = 184, green = 255, blue = 253)
    val endColor = Color(red = 0, green = 42, blue = 40)

    val gradientBrush = Brush.linearGradient(
        colors = listOf(startColor, endColor)
    )
    var inputName by remember{ mutableStateOf(TextFieldValue("")) }
    var inputPalindrome by remember{ mutableStateOf(TextFieldValue("")) }
    var isPalindrome by remember { mutableStateOf("") }

    if (isPalindrome=="isPalindrome"){
        Toast.makeText(LocalContext.current,isPalindrome,Toast.LENGTH_SHORT).show()
        isPalindrome=""
    }else if(isPalindrome=="not palindrome"){
        Toast.makeText(LocalContext.current,isPalindrome,Toast.LENGTH_SHORT).show()
        isPalindrome=""
    }
    Box(modifier = modifier
        .fillMaxSize()
        .background(brush = gradientBrush)
        .padding(32.dp)
        ,
        contentAlignment = Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(value = inputName, onValueChange ={newValue->inputName=newValue},singleLine = true, label = {Text(
                text ="Name", color = Color.Gray,
            )}, modifier= modifier.padding(8.dp) )
            TextField(value = inputPalindrome, onValueChange ={newValue->inputPalindrome=newValue},singleLine = true, label = {Text(
                text ="Palindrome", color = Color.Gray,
            )}, modifier= modifier.padding(8.dp) )
            Button(enabled = inputPalindrome.text.isNotBlank(),onClick = { if (checkPalindrome(inputPalindrome.text)){isPalindrome="isPalindrome"}else{isPalindrome="not palindrome"} }, modifier = modifier
                .padding(top = 24.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth()) {
                Text(text = "Check")
            }
            Button(enabled = inputName.text.isNotBlank(),onClick = { navigateToSecond(inputName.text) }, modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()) {
                Text(text = "Next")
            }
        }
    }
}

private fun checkPalindrome(sentence:String):Boolean{
    val cleanSentence = sentence.replace("[^A-Za-z0-9]".toRegex(),"").lowercase(Locale.ROOT)
    return cleanSentence==cleanSentence.reversed()
}