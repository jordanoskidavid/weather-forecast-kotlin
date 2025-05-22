package com.example.weatherapp

import BaseActivity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.LanguagePicker
import com.example.weatherapp.ui.theme.WeatherAppTheme
import java.util.Locale
import androidx.core.content.edit

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Load saved language preference, default to English ("en")
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val savedLang = prefs.getString("lang", "en") ?: "en"
        updateLocale(savedLang)

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
                        },
                        onLanguageChange = { languageName ->
                            val langCode = when (languageName) {
                                "English" -> "en"
                                "Македонски" -> "mk"
                                else -> "en"
                            }
                            setLanguage(langCode)
                            updateLocale(langCode)
                            recreate() // restart activity to apply new locale
                        }
                    )
                }
            }
        }
    }

    private fun setLanguage(langCode: String) {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        prefs.edit { putString("lang", langCode) }
    }

    private fun updateLocale(langCode: String) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onNavigateToRegister: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onLanguageChange: (String) -> Unit
) {
    // Read saved language from SharedPreferences on first composition
    val prefs = androidx.compose.ui.platform.LocalContext.current.getSharedPreferences("settings", 0)
    val savedLangCode = prefs.getString("lang", "en") ?: "en"
    var currentLanguage by remember {
        mutableStateOf(
            when (savedLangCode) {
                "mk" -> "Македонски"
                else -> "English"
            }
        )
    }

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
                text = stringResource(id = R.string.welcome_to),
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                color = Color.White
            )
            Text(
                text = stringResource(id = R.string.app_name),
                fontWeight = FontWeight.Bold,
                fontSize = 45.sp,
                color = Color.White
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                LanguagePicker(
                    currentLanguage = currentLanguage,
                    onLanguageSelected = { selected ->
                        currentLanguage = selected
                        onLanguageChange(selected)
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.95f), shape = MaterialTheme.shapes.medium)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.access_your_weather_info),
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
                        text = stringResource(id = R.string.register),
                        fontSize = 20.sp
                    )
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
                        text = stringResource(id = R.string.login),
                        fontSize = 20.sp
                    )
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
            onNavigateToLogin = {},
            onLanguageChange = {}
        )
    }
}
