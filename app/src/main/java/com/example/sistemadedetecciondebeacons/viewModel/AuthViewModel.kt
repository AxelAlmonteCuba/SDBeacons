package com.example.sistemadedetecciondebeacons.viewModel


import  android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemadedetecciondebeacons.data.User
import com.example.sistemadedetecciondebeacons.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _usuario = MutableStateFlow<User?>(null)
    val usuario: StateFlow<User?> get() = _usuario


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _userName = MutableStateFlow<String?>(null)
    val userName: StateFlow<String?> get() = _userName

    fun fetchAndSaveUserName(context: Context, onDone: () -> Unit) {
        getUserData { data, error ->
            if (data != null) {
                val nombre = data["nombre"] as? String
                if (!nombre.isNullOrBlank()) {
                    _userName.value = nombre

                    // Guardar en SharedPreferences como refuerzo
                    val prefs = context.getSharedPreferences("BluxiPrefs", Context.MODE_PRIVATE)
                    prefs.edit().putString("user_name", nombre).apply()

                    onDone()
                }
            }
        }
    }

    fun iniciarSesion(correo: String, password: String) {
        viewModelScope.launch {
            val result = repository.iniciarSesion(correo, password)
            result.onSuccess { firebaseUser ->
                val userId = firebaseUser?.uid
                if (userId != null) {
                    db.collection("usuarios").document(userId).get()
                        .addOnSuccessListener { document ->
                            val nombre = document.getString("nombre") ?: ""
                            val email = firebaseUser?.email ?: ""
                            val rol = document.getString("rol") ?: ""
                            _usuario.value = User(uid = userId, nombre = nombre, email = email, rol = rol)
                        }
                        .addOnFailureListener {
                            _error.value = "No se pudieron cargar los datos del usuario"
                        }
                }
            }
            result.onFailure { _error.value = it.message }
        }
    }

    fun cerrarSesion() {
        repository.cerrarSesion()
        _usuario.value = null
    }

    fun setError(s: String) {

    }

    fun getUserData(onResult: (Map<String, Any>?, String?) -> Unit) {
        val userId = auth.currentUser?.uid
        if (userId == null) {
            onResult(null, "Usuario no autenticado")
            return
        }

        viewModelScope.launch {
            db.collection("usuarios").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        onResult(document.data, null)
                    } else {
                        onResult(null, "No se encontraron datos del usuario")
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("AuthViewModel", "Error al obtener datos del usuario", e)
                    onResult(null, "Error al obtener datos del usuario")
                }
        }
    }
}
