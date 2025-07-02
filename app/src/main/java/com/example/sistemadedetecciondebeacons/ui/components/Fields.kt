package com.example.sistemadedetecciondebeacons.ui.components


import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.sistemadedetecciondebeacons.R
import java.util.Calendar


@Composable
fun TextFieldBasic(value : String, content:String, onChangeValue:(String) -> Unit){
    val colorField = colorResource(id = R.color.FieldTextColor)
    val colorText = colorResource(id = R.color.CampoTextColor)
    val round_do = integerResource(id = R.integer.dp_rounds)
    val colorTextOn = colorResource(id = R.color.PrimalColor)

    TextField(
        value = value,
        textStyle = TextStyle(color = colorTextOn),
        onValueChange = { onChangeValue(it) },
        placeholder = { Text(content, color = colorText)},
        shape = RoundedCornerShape(round_do.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorField,
            unfocusedContainerColor = colorField,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PasswordTextField(value : String, content:String, onChangeValue:(String) -> Unit){
    var passwordHidden by remember { mutableStateOf(true) }
    val colorField = colorResource(id = R.color.FieldTextColor)
    val colorText = colorResource(id = R.color.CampoTextColor)
    val colorTextOn = colorResource(id = R.color.PrimalColor)
    val round_do = integerResource(id = R.integer.dp_rounds)
    val iconVisibility = R.drawable.baseline_visibility_24
    val iconInvisibility = R.drawable.baseline_visibility_off_24
    TextField(
        value = value,
        textStyle = TextStyle(color = colorTextOn),
        onValueChange = { onChangeValue(it) },
        placeholder = { Text(content, color = colorText)},
        shape = RoundedCornerShape(round_do.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorField,
            unfocusedContainerColor = colorField,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        visualTransformation = if(passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = {
                passwordHidden = !passwordHidden
            }) {
                val visibilityIcon = if (passwordHidden) iconInvisibility else  iconVisibility
                Icon(
                    painter = painterResource(id = visibilityIcon),
                    contentDescription = if (passwordHidden) "Mostrar contraseña" else "Ocultar contraseña"
                )

            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TextFieldNumber(value : String, content:String, onChangeValue:(String) -> Unit){
    val colorField = colorResource(id = R.color.FieldTextColor)
    val colorText = colorResource(id = R.color.CampoTextColor)
    val round_do = integerResource(id = R.integer.dp_rounds)
    val colorTextOn = colorResource(id = R.color.PrimalColor)

    TextField(
        value = value,
        textStyle = TextStyle(color = colorTextOn),
        onValueChange = {
            if (it.length <= 9) { // Limita la longitud del texto
                onChangeValue(it)
            }
        },
        placeholder = { Text(content, color = colorText)},
        shape = RoundedCornerShape(round_do.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorField,
            unfocusedContainerColor = colorField,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),

        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDate(
    fechaSeleccionada: String,
    onFechaSeleccionada: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var mostrarDialogo by remember { mutableStateOf(false) }

    if (mostrarDialogo) {
        android.app.DatePickerDialog(
            context,
            { _, yearSelected, monthSelected, daySelected ->
                val fecha = "$daySelected/${monthSelected + 1}/$yearSelected"
                onFechaSeleccionada(fecha)
            },
            year,
            month,
            day
        ).show()
        mostrarDialogo = false
    }

    Box {
        TextFieldBasic(
            value = fechaSeleccionada,
            content = "Selecciona tu fecha de nacimiento",
            onChangeValue = {}
        )

        Spacer(
            modifier = Modifier
                .matchParentSize()
                .clickable { mostrarDialogo = true }
                .background(Color.Transparent) // Hace que el clic pase sin afectar la UI
        )
    }
}

@Composable
fun TextInput(value:String, content: String,title: String, onChangeValue:(String) -> Unit){
    val space_dp = integerResource(id = R.integer.dp_space).dp
    TextForms(title)
    Spacer(modifier = Modifier.height(space_dp))
    TextFieldBasic(value = value, content = content) { onChangeValue(it) }
    Spacer(modifier = Modifier.height(2 * space_dp))
}