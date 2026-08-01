package com.example.myapplication.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.data.ContactData.contacts
import com.example.myapplication.model.Contact

@Composable
fun ContactList(navController: NavHostController) {
    var query by remember { mutableStateOf("") }
    
    val filteredContacts = contacts.filter { 
        it.name.contains(query, ignoreCase = true) || it.phone.contains(query)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )
        
        LazyColumn {
            items(filteredContacts) { contact ->
                ContactRow(
                    contact = contact,
                    onClick = {
                        navController.navigate("contactDetail/${contact.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun ContactRow(contact: Contact, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp)
    ) {
        Text(text = contact.name, style = MaterialTheme.typography.titleMedium)
        Text(text = contact.phone, style = MaterialTheme.typography.bodyMedium)
        HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
    }
}
