package com.example.sistemadedetecciondebeacons.viewModel


import  android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun registrarUsuario(
        nombre: String,
        email: String,
        password: String,
        telefono: String,
        fechaNacimiento: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        if (nombre.isBlank() || email.isBlank() || password.isBlank() || telefono.isBlank() || fechaNacimiento.isBlank()) {
            onResult(false, "Todos los campos son obligatorios")
            return
        }

        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid

                        if (userId != null) {
                            val user = hashMapOf(
                                "nombre" to nombre,
                                "email" to email,
                                "telefono" to telefono,
                                "fechaNacimiento" to fechaNacimiento,
                                "userId" to userId,
                                "rol" to "user"
                            )

                            db.collection("usuarios").document(userId)
                                .set(user)
                                .addOnSuccessListener {
                                    Log.d("Registro", "Usuario registrado en Firestore")
                                    onResult(true, null)
                                }
                                .addOnFailureListener { e ->
                                    Log.e("Registro", "Error al guardar usuario en Firestore", e)
                                    onResult(false, "Error al registrar usuario en la base de datos")
                                }
                        } else {
                            onResult(false, "Error al obtener el UID del usuario")
                        }
                    } else {
                        onResult(false, task.exception?.localizedMessage ?: "Error desconocido")
                    }
                }
        }
    }
}

