package com.example.interviewtest.case2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.interviewtest.case1.Case1Screen
import com.example.interviewtest.ui.theme.InterviewTestTheme

class Case2Activity: ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize().padding(all = 30.dp)) { innerPadding ->
                    Text("Case 2 loaded")
                }
            }
        }
    }
}