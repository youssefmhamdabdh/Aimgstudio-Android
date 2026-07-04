package com.aimgstudio.ui.screens

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.material3.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import com.aimgstudio.data.models.Screenshot

@Composable
fun GalleryScreen(navController: NavHostController) {
    var screenshots by remember { mutableStateOf(listOf<Screenshot>()) }
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search screenshots...") },
            leadingIcon = { /* Icon */ },
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(screenshots) { screenshot ->
                ImageCard(screenshot)
            }
        }
    }
}

@Composable
fun ImageCard(screenshot: Screenshot) {
    Card(
        modifier = Modifier.fillMaxWidth().height(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(modifier = Modifier.fillMaxWidth().weight(1f).padding(bottom = 8.dp)) {
                Text("Image Placeholder", modifier = Modifier.align(androidx.compose.ui.Alignment.Center))
            }
            Text(text = screenshot.title, style = MaterialTheme.typography.titleMedium, maxLines = 1)
            Text(text = screenshot.description, style = MaterialTheme.typography.bodySmall, maxLines = 2)
        }
    }
}
