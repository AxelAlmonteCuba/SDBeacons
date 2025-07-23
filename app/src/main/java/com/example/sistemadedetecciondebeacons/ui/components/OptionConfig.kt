package com.example.sistemadedetecciondebeacons.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sistemadedetecciondebeacons.R

@Composable
fun OptionConfig(id_icon: Int, text: String, onclickAction: ()-> Unit){
    val space = integerResource(id = R.integer.dp_space).dp
    val color_Primal = colorResource(id = R.color.PrimalColor)
    val size_Text = integerResource(id = R.integer.size_texts).sp
    val icon_Next = R.drawable.baseline_navigate_next_24



    Row(
        modifier = Modifier.fillMaxWidth().height(55.dp).clickable { onclickAction() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = id_icon),
            contentDescription = null,
            tint = color_Primal,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(space))

        Text(
            text = text,
            fontSize = size_Text
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            painter = painterResource(id = icon_Next),
            contentDescription = null,
            tint = color_Primal,
            modifier = Modifier.size(22.dp)
        )
    }
}