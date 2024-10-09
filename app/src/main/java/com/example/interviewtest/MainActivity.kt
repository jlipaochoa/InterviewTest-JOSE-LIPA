package com.example.interviewtest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.interviewtest.case1.Case1Activity
import com.example.interviewtest.case3.presentation.DetailUser
import com.example.interviewtest.case3.presentation.HomeActivity
import com.example.interviewtest.case3.presentation.HomeViewModel
import com.example.interviewtest.ui.theme.InterviewTestTheme
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            InterviewTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize().padding(vertical = 60.dp, horizontal = 30.dp)) { innerPadding ->
                    Column {
                        Button(onClick = {
                            val intent = Intent(context, Case1Activity::class.java)
                            context.startActivity(intent)
                            finish()
                        }) {
                            Text(text = "Case 1")
                        }
                        Button(onClick = {
                            val intent = Intent(context, Case1Activity::class.java)
                            context.startActivity(intent)
                            finish()
                        }) {
                            Text(text = "Case 2")
                        }
                        Button(onClick = {
                            val intent = Intent(context, HomeActivity::class.java)
                            context.startActivity(intent)
                            finish()
                        }) {
                            Text(text = "Case 3")
                        }
                    }
                }
            }
        }
    }
}

fun startActivity() {

}