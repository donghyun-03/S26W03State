package kr.ac.kumoh.ce.s20220009.s26w03state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
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
    var count by remember { mutableStateOf(0) }        // 현재 숫자 (처음 0)
    var resetCount by remember { mutableStateOf(0) }   // 리셋한 횟수
    var showReset by remember { mutableStateOf(false) } // 애니메이션 표시 여부

    // 리셋할 때마다: 잠깐 보여줬다가 다시 숨김
    LaunchedEffect(resetCount) {
        if (resetCount > 0) {
            showReset = true
            delay(1500)          // 1.5초 동안 보여주고
            showReset = false    // 다시 들어감
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            // 숫자 표시
            Text(text = "$count", fontSize = 48.sp)

            Spacer(modifier = Modifier.height(8.dp))

            // +1 / +5 / +10 버튼
            Button(onClick = { count += 1 }, modifier = Modifier.fillMaxWidth()) {
                Text("+1")
            }
            Button(onClick = { count += 5 }, modifier = Modifier.fillMaxWidth()) {
                Text("+5")
            }
            Button(onClick = { count += 10 }, modifier = Modifier.fillMaxWidth()) {
                Text("+10")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 리셋 버튼: 숫자 0으로, 리셋 횟수 +1
            Button(
                onClick = {
                    count = 0
                    resetCount += 1
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("리셋")
            }

            // 애니메이션: 리셋 횟수가 나왔다가 다시 들어감
            AnimatedVisibility(
                visible = showReset,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut(),
            ) {
                Text(text = "리셋 횟수: $resetCount", fontSize = 20.sp)
            }
        }
    }
}