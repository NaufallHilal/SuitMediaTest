package com.naufalhilal.suitmediatest.ui.screen.third_screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.naufalhilal.suitmediatest.data.pref.UserModel
import com.naufalhilal.suitmediatest.di.Injection
import com.naufalhilal.suitmediatest.ui.ViewModelFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdScreen (modifier: Modifier=Modifier,viewModel: ThirdScreenViewModel= androidx.lifecycle.viewmodel.compose.viewModel(
    factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
), navigateBack :()-> Unit){
    val users = viewModel.pagingItem.collectAsLazyPagingItems()
    var showToast by remember { mutableStateOf(false) }

    if (showToast){
        Toast.makeText(LocalContext.current,"User berhasil di simpan",Toast.LENGTH_SHORT).show()
        showToast=false
    }
Column {
    TopAppBar(title = {
        Text(
            text = "Third Screen",
            modifier = modifier
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }, navigationIcon ={ IconButton(onClick = { navigateBack() }) {
        Icon(imageVector = Icons.Default.ArrowBack , contentDescription = "back")
    }
    }
    )
    Divider(color = Color.LightGray)
    LazyColumn{
        items(
        count = users.itemCount,
        key = users.itemKey(),
        contentType = users.itemContentType(
            )
    ) { index ->
        val item = users[index]
        Card(
            shape = MaterialTheme.shapes.small,
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .clickable {
                    viewModel.viewModelScope.launch {
                        if (viewModel.saveUser(
                                UserModel(
                                    item?.firstName.toString(),
                                    item?.lastName.toString(),
                                    item?.email.toString(),
                                    item?.avatar.toString()
                                )
                            )
                        ) {
                            showToast =true
                        }
                    }
                }
        ) {
            Row(
                modifier = modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = item?.avatar,
                    contentDescription = "foto user",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${item?.firstName} ${item?.lastName}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item?.email.toString(),
                        style = MaterialTheme.typography.bodyLarge.copy(fontStyle = FontStyle.Italic)
                    )
                }
            }
        }
 Divider(color = Color.LightGray, modifier = modifier.padding(horizontal = 16.dp))
        }
        users.apply {
            when{
                loadState.refresh is LoadState.Loading->{
                    item { CircularProgressIndicator() }
                }
                loadState.append is LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = users.loadState.refresh as LoadState.Error
                    item {
                        Text("Error: ${e.error.localizedMessage}")
                    }
                }
                loadState.append is LoadState.Error->{
                    val e = users.loadState.append as LoadState.Error
                    item {
                        Text("Error: ${e.error.localizedMessage}")
                    }
                }
            }
        }
    }
}

}