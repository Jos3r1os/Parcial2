package com.umg.practica1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.umg.practica1.ui.theme.Practica1Theme
import kotlinx.coroutines.launch

@Composable
fun Ingreso(navController: NavController) {

    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val productosDao = db.productosDao()

    val scope = rememberCoroutineScope()

    val productosList: List<Productos> by productosDao.getAllProductos().collectAsState(initial = emptyList<Productos>())

    var producto by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var stockInicial by remember { mutableStateOf("") }
    var stockMinimo by remember { mutableStateOf("") }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("INGRESA UN NUEVO PRODUCTO")

        OutlinedTextField(
            value = producto,
            onValueChange = { producto = it },
            label = { Text("Producto") }

        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripcion") }

        )

        OutlinedTextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label  = { Text("Cantidad") }
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") }
        )

        Text("CATEGORIA")

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                categoria = "PC"
            }
        ) {
            Text(text = "PC")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                categoria = "ACCESORIO"
            }
        ) {
            Text(text = "ACCESORIO")
        }

        OutlinedTextField(
            value = stockInicial,
            onValueChange = { stockInicial = it },
            label = { Text("Stock Inicial") }
        )

        OutlinedTextField(
            value = stockMinimo,
            onValueChange = { stockMinimo = it },
            label = { Text("Stock Minimo") }
        )

        Button(
            onClick = {
                val cantidadValue = cantidad.toInt()
                val precioValue = precio.toDouble()
                val stockInicialValue = stockInicial.toInt()
                val stockMinimoValue = stockMinimo.toInt()

                if(producto.isNotEmpty() && descripcion.isNotEmpty() && cantidadValue > 0 && precioValue > 0.0 && categoria.isNotEmpty() && stockInicialValue > 0 && stockMinimoValue > 0){
                    val  newProductos = Productos(producto = producto, descripcion = descripcion, cantidad = cantidadValue, precio = precioValue, categoria = categoria, stockInicial = stockInicialValue, stockMinimo = stockMinimoValue)
                    scope.launch {
                        productosDao.insertProducto(newProductos)
                        producto = ""
                        descripcion = ""
                        cantidad = ""
                        precio = ""
                        categoria = ""
                        stockInicial = ""
                        stockMinimo = ""
                    }
                }
            }
        ) { Text("GUARDAR") }

    }

}

@Preview(showBackground = true)
@Composable
fun IngresoPreview() {
    Practica1Theme {
        Ingreso(navController = rememberNavController())
    }
}