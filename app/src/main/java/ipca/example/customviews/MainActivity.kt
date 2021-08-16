package ipca.example.customviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewValue = findViewById<TextView>(R.id.textViewValue)
        val lightBrightnessView = findViewById<LightBrightnessView>(R.id.lightBrightnessView)
        lightBrightnessView.setOnValueChange = {
            textViewValue.setText(it.toInt().toString())
        }

    }
}