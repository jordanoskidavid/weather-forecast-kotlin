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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginScreen(
                onLoginSuccess = {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                },
                onNavigateToRegister = {
                    startActivity(Intent(this, RegisterActivity::class.java))
                    finish()
                }
            )
        }
    }
}

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)
        .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.Top) {
        Text(
            text = stringResource(id = R.string.login_to_account),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
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

        Spacer(modifier = Modifier.height(16.dp))

        Surface(
            onClick = { onLoginSuccess() },
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
                    text = stringResource(id = R.string.login),
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(id = R.string.dont_have_account),
            modifier = Modifier
                .clickable { onNavigateToRegister() }
                .padding(top = 8.dp),
            color = Color(0xFF00BFFF),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { /* TODO: Anonymous login */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.anonymous), color = Color.Gray, fontSize = 18.sp)
        }
    }
}
