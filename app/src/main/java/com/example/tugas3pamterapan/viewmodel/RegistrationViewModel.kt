package com.example.tugas3pamterapan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugas3pamterapan.data.RegistrationData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RegistrationUiState(
    val nim: String = "",
    val nama: String = "",
    val email: String = "",
    val nimError: String? = null,
    val namaError: String? = null,
    val emailError: String? = null,
    val isValid: Boolean = false
)

class RegistrationViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState

    fun onNimChange(value: String) {
        _uiState.update { it.copy(nim = value) }
        validate()
    }

    fun onNamaChange(value: String) {
        _uiState.update { it.copy(nama = value) }
        validate()
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value) }
        validate()
    }

    private fun validate() {
        val s = _uiState.value

        val nimErr = when {
            s.nim.isBlank() -> "NIM wajib diisi"
            s.nim.length < 5 -> "NIM minimal 5 digit"
            !s.nim.all { it.isDigit() } -> "NIM harus angka"
            else -> null
        }

        val namaErr = when {
            s.nama.isBlank() -> "Nama wajib diisi"
            s.nama.length < 3 -> "Nama terlalu pendek"
            else -> null
        }

        val emailErr = when {
            s.email.isBlank() -> "Email wajib diisi"
            !EMAIL_REGEX.matches(s.email) -> "Format email tidak valid"
            else -> null
        }

        _uiState.update {
            it.copy(
                nimError = nimErr,
                namaError = namaErr,
                emailError = emailErr,
                isValid = (nimErr == null && namaErr == null && emailErr == null)
            )
        }
    }

    fun submit(onSuccess: (RegistrationData) -> Unit) {
        viewModelScope.launch {
            validate()
            val s = _uiState.value
            if (s.isValid) {
                onSuccess(
                    RegistrationData(
                        nim = s.nim.trim(),
                        nama = s.nama.trim(),
                        email = s.email.trim()
                    )
                )
            }
        }
    }

    companion object {
        private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    }
}
