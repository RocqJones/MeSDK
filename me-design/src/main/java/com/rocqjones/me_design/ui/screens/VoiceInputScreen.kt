package com.rocqjones.me_design.ui.screens

import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun VoiceInputScreen() {
    var textForVoiceInput by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    val voiceLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        // This is where you process the intent and extract the speech text from the intent.
        activityResult.data?.let { data ->
            val results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            textForVoiceInput = results?.get(0) ?: "None"
        }
    }

    fun startVoiceRecognition() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak something...")
        }
        voiceLauncher.launch(intent)
    }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState).padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Button to trigger voice recognition
        Button(
            onClick = { startVoiceRecognition() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Start Voice Input")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display voice input text
        Text(
            text = textForVoiceInput,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
    }
}