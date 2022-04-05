package com.example.onactivityresultexample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val activityLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        onActivityResult(result)
    }

    private lateinit var resultView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultView = findViewById(R.id.tv_result)
        findViewById<Button>(R.id.button_launch_activity).setOnClickListener {
            onClickSwitchActivity()
        }
    }

    private fun onClickSwitchActivity() {
        activityLauncher.launch(Intent(this, SecondActivity::class.java))
    }

    private fun onActivityResult(result: ActivityResult) {
        when(result.resultCode) {
            Activity.RESULT_CANCELED -> resultView.text = "Canceled by User"
            Activity.RESULT_OK -> {
                resultView.text = result.data?.let { data ->
                    data.getStringExtra(REQUEST_RESULT) ?: "No Entry"
                } ?: run { "No Data" }
            }
        }
    }

    companion object {
        const val REQUEST_RESULT: String = "REQUEST_RESULT"
    }
}