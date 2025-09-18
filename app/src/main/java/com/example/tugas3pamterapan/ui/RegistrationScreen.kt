package com.example.tugas3pamterapan.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tugas3pamterapan.data.RegistrationData
import com.example.tugas3pamterapan.viewmodel.RegistrationUiState
import com.example.tugas3pamterapan.viewmodel.RegistrationViewModel

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel,
    uiState: RegistrationUiState,
    onRegistered: (RegistrationData) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pendaftaran",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = uiState.nim,
            onValueChange = viewModel::onNimChange,
            label = { Text("NIM") },
            singleLine = true,
            isError = uiState.nimError != null,
            supportingText = { uiState.nimError?.let { Text(it) } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = uiState.nama,
            onValueChange = viewModel::onNamaChange,
            label = { Text("Nama Lengkap") },
            singleLine = true,
            isError = uiState.namaError != null,
            supportingText = { uiState.namaError?.let { Text(it) } },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = uiState.email,
            onValueChange = viewModel::onEmailChange,
            label = { Text("Email") },
            singleLine = true,
            isError = uiState.emailError != null,
            supportingText = { uiState.emailError?.let { Text(it) } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.submit(onRegistered) },
            enabled = uiState.isValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("DAFTAR")
        }

        Spacer(modifier = Modifier.height(8.dp))
        SnackbarHost(hostState = snackbarHostState)
    }
}

@Composable
fun SummaryScreen(data: RegistrationData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Pendaftaran Berhasil 🎉", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text("NIM   : ${data.nim}")
        Text("Nama  : ${data.nama}")
        Text("Email : ${data.email}")
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Silakan ambil screenshot layar ini sebagai bukti pendaftaran.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
