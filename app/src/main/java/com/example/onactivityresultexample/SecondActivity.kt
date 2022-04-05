package com.example.onactivityresultexample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val resultInput = findViewById<EditText>(R.id.et_input)

        findViewById<Button>(R.id.button_finish_activity).setOnClickListener {
            val input = resultInput.text.toString()
            if (input.isEmpty()) {
                setResult(Activity.RESULT_CANCELED)
            } else {
                setResult(
                    Activity.RESULT_OK,
                    Intent().apply {
                        putExtra(MainActivity.REQUEST_RESULT, input)
                    }
                )
            }
            finish()
        }
    }

    fun injectIntent(value: Int, send: Boolean = false): Intent {
        val intent = Intent(this, MainActivity::class.java)
        intent.apply {
            putExtra(MainActivity.REQUEST_RESULT, value)
        }
        if (send) setResult(RESULT_OK, intent)
        return intent
    }

}