package com.example.sistemadedetecciondebeacons.ui.components


import  androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sistemadedetecciondebeacons.R

@Composable
fun ButtonDesign(content: String, logicButton:  () -> Unit){
    val colorBottom = colorResource(id = R.color.PrimalColor)
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = colorBottom
        ),

        onClick = {
            logicButton()
        }
    ) {
        Text(content)
    }
}

@Composable
fun ButtonDesignLight(content: String, logicButton:  () -> Unit){
    val colorBottom = colorResource(id = R.color.IconColor)
    val colorText = colorResource(id = R.color.PrimalColor)
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = colorBottom
        ),

        onClick = {
            logicButton()
        }
    ) {
        Text(text = content, color = colorText)
    }
}

@Composable
fun ButtonSocialMedia(idIcon: Int, contentDescription: String, onClickButton: () -> Unit) {
    val colorBottom = colorResource(id = R.color.IconColor)

    Button(
        onClick = { onClickButton() },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = colorBottom),
        modifier = Modifier.size(50.dp), // Tamaño cuadrado para que el CircleShape se aplique bien
        contentPadding = PaddingValues(0.dp) // Elimina el relleno interno
    ) {
        Image(
            painter = painterResource(id = idIcon),
            contentDescription = contentDescription,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun ButtonCards(content: String, logicButton: () -> Unit) {
    val colorButton = colorResource(id = R.color.PrimalColor)

    Button(
        modifier = Modifier.height(30.dp),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 2.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorButton),
        onClick = { logicButton() }
    ) {
        Text(
            text = content,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}

@Composable
fun ButtonOptionsCard(idIcon : Int, logicButton: ()-> Unit){
    val colorIcon = colorResource(id = R.color.PrimalColor)
    Button(
        onClick = {logicButton()},
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        modifier = Modifier.size(30.dp),
        contentPadding = PaddingValues(1.dp)
    ) {
        Icon(
            painter = painterResource(id = idIcon),
            contentDescription = null,
            tint = colorIcon,
            modifier = Modifier.size(20.dp)
        )
    }
}









