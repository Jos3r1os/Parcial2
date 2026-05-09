package com.umg.practica1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScrollModifierNode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.umg.practica1.ui.theme.Practica1Theme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica1Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "inicio") {
                    composable("inicio"){
                        Greeting(navController = navController)
                    }

                    composable("ingreso"){
                        Ingreso(navController = navController)
                    }

                    composable("catalogo"){
                        Catalogo(navController = navController)
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(navController: NavController, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("BIENVENIDO")
        Text("DISTRIBUIDORA JR")

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                navController.navigate("ingreso")
            }
        ) {
            Text(text = "INGRESO")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                navController.navigate("catalogo")
            }
        ) {
            Text(text = "CATALOGO PRODUCTOS")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Practica1Theme {
        Greeting(navController = rememberNavController())
    }
}

