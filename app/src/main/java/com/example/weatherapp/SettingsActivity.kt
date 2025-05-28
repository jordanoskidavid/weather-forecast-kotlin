package com.example.weatherapp

import BaseActivity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

class SettingsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SettingsScreen(
                currentLangCode = getSharedPreferences("settings", MODE_PRIVATE)
                    .getString("lang", "en") ?: "en",
                onLanguageChange = { langCode ->
                    saveAndApplyLanguage(langCode)
                    // Restart MainActivity to apply locale
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
            )
        }
    }

    private fun saveAndApplyLanguage(langCode: String) {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        prefs.edit().putString("lang", langCode).apply()

        val locale = Locale(langCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}

@Composable
fun SettingsScreen(currentLangCode: String, onLanguageChange: (String) -> Unit) {
    val context = LocalContext.current
    var selectedLang by remember { mutableStateOf(currentLangCode) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = stringResource(id = R.string.settings),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(text = stringResource(id = R.string.choose_language), fontSize = 20.sp, fontWeight = FontWeight.Medium)

        Spacer(modifier = Modifier.height(16.dp))

        LanguageOption(
            label = "English",
            code = "en",
            isSelected = selectedLang == "en",
            onSelect = {
                selectedLang = "en"
                onLanguageChange("en")
            }
        )

        LanguageOption(
            label = "Македонски",
            code = "mk",
            isSelected = selectedLang == "mk",
            onSelect = {
                selectedLang = "mk"
                onLanguageChange("mk")
            }
        )
    }
}

@Composable
fun LanguageOption(label: String, code: String, isSelected: Boolean, onSelect: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() }
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 18.sp)
        if (isSelected) {
            Text(text = "✓", color = Color(0xFF00BFFF), fontSize = 18.sp)
        }
    }
}
