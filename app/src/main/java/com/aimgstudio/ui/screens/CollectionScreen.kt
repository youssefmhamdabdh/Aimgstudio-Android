package com.aimgstudio.ui.screens

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.material3.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import com.aimgstudio.data.models.Collection

@Composable
fun CollectionScreen(navController: NavHostController) {
    var collections by remember { mutableStateOf(listOf<Collection>()) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Collections", style = MaterialTheme.typography.headlineMedium)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(collections) { collection ->
                CollectionItem(collection)
            }
        }
    }
}

@Composable
fun CollectionItem(collection: Collection) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text(text = collection.name, style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
            Text(text = "${collection.screenshotIds.size} items", style = MaterialTheme.typography.bodySmall)
        }
    }
}
