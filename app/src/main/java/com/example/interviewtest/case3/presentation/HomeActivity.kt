package com.example.interviewtest.case3.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewtest.case3.domain.AddUserIdUseCase
import com.example.interviewtest.case3.domain.GetUserCase
import com.example.interviewtest.case3.domain.GetUserIdUseCase
import com.example.interviewtest.case3.domain.UserDTO
import com.example.interviewtest.ui.theme.InterviewTestTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val USER_ID = "USER_ID"

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val sharedPreferences by lazy {
        getSharedPreferences(USER_ID, MODE_PRIVATE)
    }
    private var userId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            userId = sharedPreferences.getInt(USER_ID, 1)

            InterviewTestTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 30.dp)
                ) { innerPadding ->
                    Case3Screen()
                }
            }


        }
    }

}


@Composable
fun DetailUser(userDTO: UserDTO?) {
    Card {
        Text(text = userDTO?.id?.toString() ?: "")
        Text(text = userDTO?.lastName ?: "")
        Text(text = userDTO?.firstName ?: "")
        Text(text = userDTO?.email ?: "")
        Text(text = userDTO?.gender ?: "")
    }
}

@Composable
fun Case3Screen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val userDetail by viewModel.listUser.collectAsState()
    val userId by viewModel.userId.collectAsState()
    Column {

        Text("User Id: $userId")

        Button(onClick = { viewModel.addUserIdOneUnit() }) {
            Text("Incrementar")
        }

        Button(onClick = { viewModel.getDetailUser() }) {
            Text("get user detail")
        }

        DetailUser(userDTO = userDetail)
    }
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getUserCase: GetUserCase,
    val getUserId: GetUserIdUseCase,
    val addUserId: AddUserIdUseCase
) : ViewModel() {

    private val _userDetail = MutableStateFlow<UserDTO?>(null)
    val listUser: StateFlow<UserDTO?> = _userDetail.asStateFlow()

    private val _userId = MutableStateFlow<Int>(1)
    val userId: StateFlow<Int> = _userId.asStateFlow()

    var getJob: Job? = null

    init {
        viewModelScope.launch {
            getUserId().collect {
                _userId.value = it
            }
        }
    }

    fun addUserIdOneUnit() {
        viewModelScope.launch {
            addUserId.invoke(_userId.value)
        }
    }

    fun getDetailUser() {
        getJob?.cancel()
        getJob = viewModelScope.launch {
            try {
                _userDetail.value = getUserCase.invoke()
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error: ${e.message}")
            }
        }
    }
}