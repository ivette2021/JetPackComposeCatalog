package com.example.jeckpackcomposecatalog.ui.theme

import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jeckpackcomposecatalog.Model.Superhero
import com.example.jeckpackcomposecatalog.R

@Composable
fun SimpleRecyclerView() {
    val myList =
        listOf("Ariel", "Pepa", "Manuel", "James", "Monica", "Jules", "Chandler", "Jessica")
    LazyColumn {
        item { Text(text = "Encabezado", fontWeight = FontWeight.Bold, fontSize = 20.sp) }
        items(100) {
            Text(text = "Item : $it")
        }
        items(myList) {
            Text(text = "Hola me llamo $it", fontSize = 18.sp)
        }
        item { Text(text = "Pie de pagina", fontWeight = FontWeight.Bold, fontSize = 20.sp) }
    }
}
@Composable
fun SuperHeroView() {
    val context = LocalContext.current
    //LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {//para que sea horizontal el recycler
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {//para que sea vertical
        items(getSuperheroes()) { superhero ->
            ItemHero(superhero = superhero) {
                Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT)
                    .show()// mostrar un mensaje cuando se hace click sobre la imagen
            }
        }
    }
}


@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current
    LazyVerticalGrid(columns = GridCells.Fixed(2), content ={
        items(getSuperheroes()){ superhero ->
            ItemHero(superhero = superhero)
            {Toast.makeText(context,it.realName,Toast.LENGTH_SHORT).show()}

        }
    }, contentPadding = PaddingValues(horizontal = 16.dp,vertical = 8.dp)) //margen de afuera del conjunto de imagenes
}


@Composable
fun ItemHero(superhero: Superhero, onItemSelected: (Superhero) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color(0xFF993535)),
        modifier = Modifier
            .width(200.dp)
            .clickable { onItemSelected(superhero) }.padding(top = 8.dp,bottom = 8.dp, end= 16.dp, start = 16.dp)) {
        Column {
            Image(
                painter = painterResource(id = superhero.photo),
                contentDescription = "Superhero avatar",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop //coger el ancho que necesite para crecer
            )
            Text(
                text = superhero.superheroName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )

            Text(
                text = superhero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )

            Text(
                text = superhero.publisher,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)
            )//iferior der
        }
    }
}

fun getSuperheroes(): List<Superhero> {
    return listOf(
        Superhero("Spiderman", "Peter Parker", "Marvel", R.drawable.spiderman),
        Superhero("Wolverine", "James Howlett", "Marvel", R.drawable.logan),
        Superhero("Wonder Woman", "Princess Diana", "DC", R.drawable.wonder_woman),
        Superhero("Green Lantern", "Alan Scott", "DC", R.drawable.green_lantern),
        Superhero("Flash", "Jay Garrick", "DC", R.drawable.flash),
        Superhero("Thor", "Thor Odinson", "Marvel", R.drawable.thor),
        Superhero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
    )
}
