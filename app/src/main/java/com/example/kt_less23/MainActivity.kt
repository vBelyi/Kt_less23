package com.example.kt_less23

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        val result: TextView = findViewById(R.id.textV)
        val button: Button = findViewById(R.id.button)
        val viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        button.setOnClickListener {
            viewModel.getData()
        }
        viewModel.uiState.observe(this) {uiState ->
            when (uiState) {
                is ViewModel.UIState.Empty -> Unit
                is ViewModel.UIState.Result -> {
                    result.text = uiState.title
                }
                is ViewModel.UIState.Processing -> result.text = "Processing..."
                is ViewModel.UIState.Error -> {
                    result.text = ""
                    Toast.makeText(this, uiState.description, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}