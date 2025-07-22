package com.example.sistemadedetecciondebeacons.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sistemadedetecciondebeacons.R
import java.text.DecimalFormat

@Composable
fun CardLocation(location: String, onClickAction: () -> Unit) {
    val width = integerResource(id = R.integer.width_ubi).dp
    val height = integerResource(id = R.integer.height_ubi).dp
    val color_ubi = colorResource(id = R.color.PrimalColor)
    val logo_location = R.drawable.location_logo
    val text_size = integerResource(id = R.integer.text_ubi).sp
    val rouder_shape = integerResource(R.integer.rounder_shape_home)

    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(rouder_shape.dp))
            .background(Color.White)
            .clickable {
                onClickAction()
            },
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.Bottom) {
            Icon(
                painter = painterResource(id = logo_location),
                contentDescription = null,
                modifier = Modifier.size(35.dp),
                tint = color_ubi
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "Estas en",
                fontSize = text_size
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = location,
                color = color_ubi,
                fontSize = text_size,
                fontWeight = FontWeight.Medium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Composable
fun CardUser(user_name : String, user_place : String, last_connection_hour: String, last_connection_distance: Double) {
    val colorCard = colorResource(id = R.color.IconColor)
    val rouder_shape = integerResource(R.integer.rounder_shape_home).dp
    val user_icon = R.drawable.user
    val color_base = colorResource(id = R.color.PrimalColor)
    val formato = DecimalFormat("0.00000")


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(rouder_shape))
            .background(colorCard)
    ){
        Row (modifier =  Modifier.fillMaxSize().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = user_icon),
                contentDescription = null,
                modifier = Modifier.weight(0.3f).size(88.dp),
                tint = color_base
            )

            Box(
                modifier = Modifier.weight(0.7f)
            ){
                Column (modifier = Modifier.fillMaxSize().padding(start = 10.dp, end = 10.dp)){
                    Row (modifier = Modifier.fillMaxWidth()){
                        TextTitleCardResource(text = user_name)
                        Spacer(modifier = Modifier.weight(1f))

                    }
                    Spacer(modifier = Modifier.weight(0.5f))
                    TextInfoCard(text = user_place)
                    Spacer(modifier = Modifier.weight(0.5f))
                    TextInfoCard(text = "Hora: " + last_connection_hour)
                    Spacer(modifier = Modifier.weight(0.5f))
                    TextInfoCard(text = "Distancia: " + formato.format(last_connection_distance))

                    Spacer(modifier = Modifier.weight(1f))
                    ButtonCards("Editar") { }

                }
            }
        }
    }
}