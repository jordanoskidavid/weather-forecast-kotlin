import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity

open class BaseActivity : ComponentActivity() {

    private lateinit var localeHelper: LocaleHelper

    override fun attachBaseContext(newBase: Context) {
        localeHelper = LocaleHelper(newBase)
        val context = localeHelper.updateLocale()
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
