package com.example.myapplication.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.data.ContactData

@Composable
fun ContactDetailScreen(
    contactId: Int,
    navController: NavHostController
) {
    val contact = ContactData.getById(contactId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (contact == null) {
            Text("Contact not found")
        } else {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Phone: ${contact.phone}",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}

@Composable
@androidx.compose.ui.tooling.preview.Preview(showSystemUi = true)
fun ContactDetailPreview() {
    com.example.myapplication.ui.theme.MyApplicationTheme {
        ContactDetailScreen(
            contactId = 1,
            navController = androidx.navigation.compose.rememberNavController()
        )
    }
}
