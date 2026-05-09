package com.umg.practica1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.umg.practica1.ui.theme.Practica1Theme

@Composable
fun Catalogo (navController: NavController){
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val productosDao = db.productosDao()

    val scope = rememberCoroutineScope()

    val productosList: List<Productos> by productosDao.getAllProductos().collectAsState(initial = emptyList<Productos>())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("CATALOGO")

        productosList.forEach { producto ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Nombre del producto: ${producto.producto}")
                    Text("Codigo del producto: ${producto.id}")
                    Text("Descripcion: ${producto.descripcion}")
                    Text("Cantidad: ${producto.cantidad}")
                    Text("Precio: ${producto.precio}")
                    Text("Categoria: ${producto.categoria}")
                    Text("Stock Inicial: ${producto.stockInicial}")
                    Text("Stock Minimo: ${producto.stockMinimo}")
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CatalogoPreview() {
    Practica1Theme {
        Catalogo(navController = rememberNavController())
    }
}