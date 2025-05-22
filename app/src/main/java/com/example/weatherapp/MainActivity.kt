package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.WeatherAppTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.res.painterResource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        onNavigateToRegister = {
                            startActivity(Intent(this, RegisterActivity::class.java))
                        },
                        onNavigateToLogin = {
                            startActivity(Intent(this, LoginActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onNavigateToRegister: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF00BFFF)) // Sky blue background
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = "Welcome to",
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                color = Color.White
            )
            Text(
                text = "Weathero",
                fontWeight = FontWeight.Bold,
                fontSize = 45.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.95f), shape = MaterialTheme.shapes.medium)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Access your weather info",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF333333),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Button(
                    onClick = onNavigateToRegister,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    shape = MaterialTheme.shapes.medium,
                    elevation = null,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFC474),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Register",
                        fontSize = 20.sp)
                }

                Button(
                    onClick = onNavigateToLogin,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    elevation = null,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1565C0),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Login",
                        fontSize = 20.sp)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    WeatherAppTheme {
        MainScreen(
            onNavigateToRegister = {},
            onNavigateToLogin = {}
        )
    }
}
