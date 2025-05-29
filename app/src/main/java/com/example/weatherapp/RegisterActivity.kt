package com.example.weatherapp

import BaseActivity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color




class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RegisterScreen(
                onRegisterSuccess = {
                    startActivity(Intent(this, WeatherActivity::class.java))
                    finish()
                },
                onNavigateToLogin = {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            )
        }
    }
}

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.create_account),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(stringResource(id = R.string.username)) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Black, // Focused border color
                    unfocusedIndicatorColor = Color.Black, // Unfocused border color
                    focusedTextColor = Color.Black, // Text color when focused
                    unfocusedTextColor = Color.Black, // Text color when unfocused
                    focusedLabelColor = Color(0xFF00BFFF), // Label color when focused
                    unfocusedLabelColor = Color.Black, // Label color when unfocused
                    cursorColor = Color(0xFF00BFFF), // Cursor color
                    focusedContainerColor = Color.Transparent, // Container background when focused
                    unfocusedContainerColor = Color.Transparent, // Container background when unfocused
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(id = R.string.email)) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Black, // Focused border color
                    unfocusedIndicatorColor = Color.Black, // Unfocused border color
                    focusedTextColor = Color.Black, // Text color when focused
                    unfocusedTextColor = Color.Black, // Text color when unfocused
                    focusedLabelColor = Color(0xFF00BFFF), // Label color when focused
                    unfocusedLabelColor = Color.Black, // Label color when unfocused
                    cursorColor = Color(0xFF00BFFF), // Cursor color
                    focusedContainerColor = Color.Transparent, // Container background when focused
                    unfocusedContainerColor = Color.Transparent, // Container background when unfocused
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(id = R.string.password)) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Black, // Focused border color
                    unfocusedIndicatorColor = Color.Black, // Unfocused border color
                    focusedTextColor = Color.Black, // Text color when focused
                    unfocusedTextColor = Color.Black, // Text color when unfocused
                    focusedLabelColor = Color(0xFF00BFFF), // Label color when focused
                    unfocusedLabelColor = Color.Black, // Label color when unfocused
                    cursorColor = Color(0xFF00BFFF), // Cursor color
                    focusedContainerColor = Color.Transparent, // Container background when focused
                    unfocusedContainerColor = Color.Transparent, // Container background when unfocused
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Surface(
                onClick = { onRegisterSuccess() },
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF00BFFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                    .padding(0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.register),
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.already_have_account),
                modifier = Modifier
                    .clickable { onNavigateToLogin() }
                    .padding(top = 8.dp),
                color = Color(0xFF00BFFF),
                fontSize = 18.sp
            )
        }

        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.or_continue_with),
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }


            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                onClick = { /* TODO: Google login */ },
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFEA4335), // Google red
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 13.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.google),
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }


            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                onClick = { /* TODO: Google login */ },
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF1565C0),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 13.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.facebook),
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }

}
