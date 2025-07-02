package com.example.sistemadedetecciondebeacons.ui.components


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.sistemadedetecciondebeacons.R

@Composable
fun TextForms(text:String){
    val colorText = colorResource(id = R.color.TextForms)
    val sizeText = integerResource(id = R.integer.size_texts)
    Text(
        text = text,
        color = colorText,
        fontSize = sizeText.sp,
        fontWeight = FontWeight.Bold,
        maxLines = 1
    )

}

@Composable
fun TextFormsInfo(text:String){
    val colorText = colorResource(id = R.color.TextFormsInfo)
    val sizeText = integerResource(id = R.integer.text_info)

    Text(
        text = text,
        color = colorText,
        fontSize = sizeText.sp,
        //fontWeight = FontWeight.Light,
        textAlign = TextAlign.Center,
        maxLines = 1
    )
}

@Composable
fun TextInfoCard(text : String){
    val size_text = integerResource(id = R.integer.size_text_card_info)
    Text(
        text = text,
        fontSize = size_text.sp

    )
}

@Composable
fun TextTitleCardResource(text: String){
    val size_text = integerResource(id = R.integer.size_text_card)
    val color_text = colorResource(id = R.color.PrimalColor)
    Text(
        text = text,
        fontSize = size_text.sp,
        color = color_text,
        fontWeight = FontWeight.Medium,
        maxLines = 2
    )
}