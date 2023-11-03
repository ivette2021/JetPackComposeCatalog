@file:OptIn(
    ExperimentalMaterial3Api::class,
)

package com.example.jeckpackcomposecatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jeckpackcomposecatalog.ui.theme.CheckInfo
import com.example.jeckpackcomposecatalog.ui.theme.JeckPackComposeCatalogTheme
import com.example.jeckpackcomposecatalog.ui.theme.MyBottomNavigation
import com.example.jeckpackcomposecatalog.ui.theme.ScaffoldExample
import com.example.jeckpackcomposecatalog.ui.theme.Screen1
import com.example.jeckpackcomposecatalog.ui.theme.Screen2
import com.example.jeckpackcomposecatalog.ui.theme.Screen3

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JeckPackComposeCatalogTheme {
            }
            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
               val navigationController = rememberNavController()//gestiona los estados de navegacion
                NavHost(navController = navigationController, startDestination = "screen1" ) {
                    //debemos designar una id
                    composable("screen1"){ Screen1(navigationController) }
                    composable("screen2"){ Screen2(navigationController) }
                    composable("screen3"){ Screen3(navigationController) }
                }

            }
        }
    }
}
@Composable
fun MyDropDownMenu() {

    var selectedText by remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    val desserts = listOf("helado", "chocolate", "cafe", "natilas", "chilaquiles")

    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true } //cuando haga click se muestre las opciones
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        )
        {
            desserts.forEach { dessert ->
                DropdownMenuItem(
                    text = { Text(text = dessert) },
                    onClick = {
                        expanded = false
                        selectedText = dessert
                    }
                )
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBadgeBox() {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        BadgedBox(badge = {
            Badge(
                containerColor = Color.Blue,//fondo de notificaciones o badge sea vea azul
                contentColor = Color.Yellow //color de la letra
            ) { Text(text = "20") }
        }
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = ""
            )
        }
    }
}

@Composable
fun MyDivider() {
    Divider(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp), color = Color.Red
    )
}

@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),//asi es la forma de colocar ahora la elevacion
        shape = MaterialTheme.shapes.small, //small es menos borde large es mas redondeado
        colors = CardDefaults.cardColors(Color.Blue),
        border = BorderStroke(5.dp, Color.Black) //borde y color
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Hola, que tal", color = Color.White) //aqui le aplico color al texto
            Text(text = "Bienvenido a mi portafolio", color = Color.White)
            Text(text = "Estas list@ ?", color = Color.White)
        }
    }
}

@Composable
fun MyRadioButtonList(
    name: String,
    onItemSelected: (String) -> Unit
) { //este se puede marca solo una respuesta y cambiar la respuesta
    var selected by remember {
        mutableStateOf("si")
    }
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.padding(6.dp)) {
            RadioButton(selected = name == "si", onClick = { onItemSelected("si") })
            Text(text = "si")
        }
        Row(Modifier.padding(6.dp)) {
            RadioButton(selected = name == "no", onClick = { onItemSelected("no") })
            Text(text = "no")
        }
        Row(Modifier.padding(6.dp)) {
            RadioButton(selected = name == "no lo se", onClick = { onItemSelected("no lo se") })
            Text(text = "no lo se")
        }
        Row(Modifier.padding(6.dp)) {
            RadioButton(selected = name == "omitir", onClick = { onItemSelected("omitir") })
            Text(text = "omitir")
        }
    }

}


@Composable
fun MyRadioButton() { //este no tiene estados, asi que no se podra pulsar no ver los cambios de colores
    Row(Modifier.fillMaxWidth()) {
        RadioButton(
            selected = false,
            onClick = { },
            enabled = false,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Yellow,
                disabledSelectedColor = Color.Green
            )
        )
        Text(text = "Ejemplo 1")
    }
}


@Composable
fun MyTriStatusCheckBox() {
    var status by rememberSaveable {
        mutableStateOf(ToggleableState.Off)
    }
    TriStateCheckbox(state = status, onClick = {
        status = when (status) {
            ToggleableState.On -> {
                ToggleableState.Off
            }

            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })
}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {//retornamos el resultado
        var status by rememberSaveable { mutableStateOf(false) } //map los recorre y los devuelve
        CheckInfo( //esto lo sacamos del set content y lo modificamos un poco
            title = it,//it es el titulo por el cual estoy recorriendo
            selected = status,
            //onCheckedChange = { status = it} //estamos seteando un nuevo valor, devuelve un boolean que seria it
            onCheckedChange = { myNewStatus ->
                status = myNewStatus
            })//se cambio it por mynew status}
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JeckPackComposeCatalogTheme {
        //MyText("Android")
        //MyTextFieldOutlined()
        //MyButtonExample()
        //MyImage()
        //MyIcon()
        //MyProgress()
        //MyProgressAdvance()
        //MySwitch()
        //MyCheckBox()
        MyCheckBoxWithText()
    }
}

@Composable
fun MyCheckBoxWithTextCompleted(checkInfo: CheckInfo) {
    Row(Modifier.padding(8.dp)) {//no gestionamos el estado aqui
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })//llamamos a lamda y su boolean
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = checkInfo.title)
    }
}

@Composable
fun MyCheckBoxWithText() {
    var state by rememberSaveable {
        mutableStateOf(false) //en mutable state no podemos colocar parametros
    }
    Row(Modifier.padding(8.dp)) {
        Checkbox(checked = state, onCheckedChange = { state = !state })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Ejemplo 1")
    }
}

@Composable
fun MyCheckBox() {
    var state by rememberSaveable { mutableStateOf(false) }
    Checkbox(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red,//color fondo de la casilla seleccionada
            uncheckedColor = Color.Green,//color de la casilla vacia borde
            checkmarkColor = Color.Blue //color palomita
        )
    )
}

@Composable
fun MySwitch() {
    var state by remember { mutableStateOf(false) }//switch esta en false
    Switch(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,//habilitado o desabilitado
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            checkedThumbColor = Color.Green,
            uncheckedTrackColor = Color.Magenta,
            checkedTrackColor = Color.Cyan,
            disabledCheckedTrackColor = Color.Yellow,//color para desabilitado y en true
            disabledCheckedThumbColor = Color.Yellow,
            disabledUncheckedTrackColor = Color.Yellow,//switch desabilitado en false
            disabledUncheckedThumbColor = Color.Yellow
        )
    )
}

@Composable
fun MyProgressAdvance() {
    var progressStatus by rememberSaveable { mutableStateOf(0f) }
    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CircularProgressIndicator(progress = progressStatus) //circular, tambien se puede hacer con el linear progress
        Spacer(modifier = Modifier.height(25.dp)) // Agrega un espacio vertical entre el CircularProgressIndicator y los botones

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {//centramos los botones de manera horizontal
            // Button(onClick = {progressStatus = progressStatus + 0.1f}) {//en base a este boton se muestra la barra de carga
            Row {
                Button(onClick = { progressStatus += 0.1f }) {
                    Text(text = "Incrementar")
                }
            }

            Row {

                Button(onClick = { progressStatus -= 0.1f }) {//en base a este boton se muestra la barra de carga
                    Text(text = "Decrecer")
                }
            }
        }
    }
}


@Composable()
fun MyProgress() {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            CircularProgressIndicator(color = Color.Red, strokeWidth = 10.dp) //circular
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Cyan,
                trackColor = Color.DarkGray
            )//barra
        }
        Button(onClick = {
            showLoading = !showLoading
        }) {//en base a este boton se muestra la barra de carga
            Text(text = "Cargar perfil")

        }
    }
}

@Composable()
fun MyIcon() {
    Icon(imageVector = Icons.Rounded.Star, contentDescription = "icono estrella", tint = Color.Red)
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ejemplo",
        // alpha = 0.5f //opacidad
        //modifier = Modifier.clip(RoundedCornerShape(25f))//redondeo que tienen los iconos de las app
        // modifier = Modifier.clip(CircleShape)//tenemos una imagen circular perfecta
        modifier = Modifier
            .clip(CircleShape)
            .border(5.dp, Color.Red, CircleShape)//
    )
}

@Composable
fun MyButtonExample() {
    var enabled by rememberSaveable {
        mutableStateOf(true)//le agregamos un recordar estado porque se recompone el composable y se pierde lo que definimos como false
    }
    Column( //esta parte del codigo es para definir estilos en el boton fondo color de letra y borde
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = {
                enabled = false
                // Log.i("Hola","Esto es un ejemplo")
            },
            enabled = enabled,//definimos que si es boton se presiona una vez cambia su color y se desabilita
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,//aqui esta la linea corregida
                contentColor = Color.Blue //cambio de color
            ),
            border = BorderStroke(5.dp, Color.Green)
        ) {
            Text(text = "Start")
        }
        OutlinedButton(onClick = { }) {
            Text(text = "Hello")
        }
        OutlinedButton(
            onClick = { enabled = false },
            enabled = enabled,//definimos que si es boton se presiona una vez cambia su color y se desabilita
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,//aqui esta la linea corregida
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black//cambio de color
            ),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "Bye")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "GitHub")
        }
    }
}

@Composable
fun MyTextFieldOutlined() { //para que el edittext tenga un borde redondeado
    var myText by remember { mutableStateOf("username") }//placeholder
    OutlinedTextField(
        value = myText,
        onValueChange = { myText = it },
        modifier = Modifier.padding(24.dp),
        label = {
            Text(
                text = "hello, how are you" //el texto encima
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Magenta,
            unfocusedBorderColor = Color.Red
        )
    )//cambie de color cuando se selecciona y no
}


@Composable
fun MyTextFieldAdvance() {
    var myText by remember {
        mutableStateOf("")
    }
    TextField(
        value = myText,
        onValueChange = {
            myText = if (it.contains("a")) {
                it.replace("a", "")//lo que hace es bloquear la letra a

            } else {
                it
            }
        },//borramos el it y lo cambiamos por una comprobacion
        label = { Text(text = "Introduce tu nombre") })
}


@Composable
fun MyTextField() {
    //var myText by remember { mutableStateOf("") }//solo rellenar
    var myText by remember { mutableStateOf("username") }//placeholder
    TextField(value = myText, onValueChange = { myText = it })
}

@Composable
fun MyText(name: String, modifier: Modifier = Modifier) {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Esto es un ejemplo")
        Text(text = "Esto es un ejemplo", color = Color.Blue)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.ExtraBold)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.Light)
        Text(
            text = "Esto es un ejemplo",
            fontFamily = FontFamily.Cursive
        )//podemos acceder desde afuera al fontfamily sin usar style , depende del uso
        Text(text = "Esto es un ejemplo", style = TextStyle(fontFamily = FontFamily.Cursive))
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        Text(
            text = "Esto es un ejemplo", style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(TextDecoration.LineThrough, TextDecoration.Underline)
                )
            )
        )//mezcla dos estilos
        Text(text = "Esto es un ejemplo", fontSize = 30.sp)
    }
}

