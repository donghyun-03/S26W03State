package kr.ac.kumoh.ce.s20220009.s26w03state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ac.kumoh.ce.s20220009.s26w03state.ui.theme.S26W03StateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S26W03StateTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            StateButton1()
            StateButton2()
            StateButton3()
            StateTextInput()
            StateVisibility()
        }
    }
}

@Composable
fun StateButton1() {
    val text = remember { mutableStateOf("눌러주세요") }

    Button(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        onClick = {
            text.value = "눌렸습니다"
        }
    ) {
        Text(text.value, fontSize = 30.sp)
    }
}

@Composable
fun StateButton2() {
    val (text, setText) = remember { mutableStateOf("눌러주세요") }

    Button(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        onClick = {
            setText("누르셨군요")
        }
    ) {
        Text(text, fontSize = 30.sp)
    }
}

@Composable
fun StateButton3() {
    var text by remember { mutableStateOf("눌러주세요") }

    Button(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        onClick = {
            text = "감사합니다"
        }
    ) {
        Text(text, fontSize = 30.sp)
    }
}

@Composable
fun StateTextInput() {
    var text by remember { mutableStateOf("") }

    if (text.isNotEmpty())
        Text(text, fontSize = 20.sp)

    TextField(
        value = text,
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        onValueChange = {
            text = it
        }
    )
}

@Composable
fun StateVisibility() {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Button(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            onClick = { expanded = !expanded }
        ) {
            Text("토글 버튼", fontSize = 30.sp)
        }
        AnimatedVisibility(visible = expanded) {
            Text("안녕하세요", fontSize = 30.sp)
        }
    }
}