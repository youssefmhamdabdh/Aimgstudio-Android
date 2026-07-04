package com.aimgstudio.ui.screens

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.material3.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import com.aimgstudio.data.models.LogEntry

@Composable
fun AIScreen(navController: NavHostController) {
    var logs by remember { mutableStateOf(listOf<LogEntry>()) }
    var isProcessing by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "AI Processing", style = MaterialTheme.typography.headlineMedium)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "AI Queue", style = MaterialTheme.typography.titleMedium)
                Text(text = if (isProcessing) "Processing image..." else "Queue empty")
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(
                    onClick = { isProcessing = !isProcessing },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isProcessing
                ) {
                    Text("Start Batch Process")
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(text = "Activity Log", style = MaterialTheme.typography.titleMedium)
        
        LazyColumn(
            modifier = Modifier.fillMaxSize().weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(logs) { log ->
                LogItem(log)
            }
        }
        
        Button(
            onClick = { logs = emptyList() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear Logs")
        }
    }
}

@Composable
fun LogItem(log: LogEntry) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "[${log.level}] ", 
            style = MaterialTheme.typography.bodySmall, 
            color = when(log.level) {
                "ERROR" -> MaterialTheme.colorScheme.error
                "WARN" -> MaterialTheme.colorScheme.tertiary
                else -> MaterialTheme.colorScheme.primary
            }
        )
        Text(
            text = log.message, 
            style = MaterialTheme.typography.bodySmall, 
            maxLines = 2
        )
    }
}
