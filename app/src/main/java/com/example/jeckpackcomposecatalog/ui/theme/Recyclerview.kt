package com.example.jeckpackcomposecatalog.ui.theme

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun SimpleRecyclerView() {
    val myList = listOf("Ariel", "Pepa", "Manuel", "James", "Monica", "Jules","Chandler", "Jessica")
    LazyColumn{
        item { Text(text = "Encabezado", fontWeight = FontWeight.Bold, fontSize = 20.sp)}
        items(100) {
            Text(text = "Item : $it")
        }
        items(myList) {
            Text(text = "Hola me llamo $it", fontSize = 18.sp)

        }
        item{ Text(text = "Pie de pagina", fontWeight = FontWeight.Bold, fontSize = 20.sp)}
    }
}


