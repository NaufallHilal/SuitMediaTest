package com.naufalhilal.suitmediatest.ui.screen.second_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naufalhilal.suitmediatest.di.Injection
import com.naufalhilal.suitmediatest.ui.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen (name:String, modifier: Modifier=Modifier,navigateBack:()->Unit,navigateToThird:()->Unit,viewModel: SecondScreenViewModel= androidx.lifecycle.viewmodel.compose.viewModel(
    factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
)){
    viewModel.getUser()
    Column {
        TopAppBar(title = {
            Text(
                text = "Second Screen",
                modifier = modifier
                    .fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }, navigationIcon ={ IconButton(onClick = { navigateBack() }) {
            Icon(imageVector =Icons.Default.ArrowBack , contentDescription = "back")
        }}
            )
        Divider(color = Color.LightGray)
        Text(text = "Welcome", color = Color.Gray, style = TextStyle(fontSize = 12.sp), modifier = modifier.padding(start = 16.dp, top = 8.dp))
        Text(text = name, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),modifier = modifier.padding(horizontal = 16.dp, vertical = 4.dp))
        Box(modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
            .weight(weight = 1f)
            ,
            contentAlignment = Alignment.Center
        ){
            Text(text = viewModel.userName.collectAsState().value.let { if (it!="") it else "Selected User Name" })
        }
        Button(onClick = { navigateToThird() }, enabled = true, modifier = modifier
            .fillMaxWidth()
            .height(88.dp)
            .padding(16.dp)) {
            Text(
                text = "Choose a User",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}