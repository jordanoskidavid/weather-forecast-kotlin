import android.content.Context
import android.content.res.Configuration
import java.util.Locale
import androidx.core.content.edit

class LocaleHelper(private val context: Context) {

    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun getLanguage(): String = prefs.getString("lang", "en") ?: "en"

    fun setLanguage(lang: String) {
        prefs.edit { putString("lang", lang) }
    }

    fun updateLocale(): Context {
        val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val langCode = prefs.getString("lang", "en") ?: "en"

        val locale = Locale(langCode)
        Locale.setDefault(locale)

        val config = context.resources.configuration
        config.setLocale(locale)

        return context.createConfigurationContext(config)
    }
}
