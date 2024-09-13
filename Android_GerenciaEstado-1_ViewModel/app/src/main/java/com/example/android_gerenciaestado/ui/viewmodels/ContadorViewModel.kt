package com.example.android_gerenciaestado.ui.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContadorViewModel: ViewModel() {
    private val _contador = MutableStateFlow<Int>(0)
    val contador = _contador.asStateFlow()

    fun incrementar() {
        _contador.value++
    }

    fun decrementar() {
        _contador.value--
    }
}
