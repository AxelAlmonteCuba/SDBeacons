package com.example.sistemadedetecciondebeacons.ui.components

import  androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import com.example.sistemadedetecciondebeacons.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicTopAppBar(title: String, modifier: Modifier, navController: NavController) {
    val colorPrimal = colorResource(id = R.color.PrimalColor)
    val dpSpac = integerResource(id = R.integer.dp_space)

    TopAppBar(

        title = {
            Text(
                text = title,
                color = colorPrimal,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.fillMaxWidth()

            )
        },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                contentDescription = "Back",
                tint = colorPrimal,
                modifier = Modifier.clickable { navController.popBackStack() }.padding(16.dp)
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.White
        )
    )
}


@Composable
fun HomeTopAppBar(id_perfil: Int, name: String, modifier: Modifier, setting:()->Unit){
    val color_text = colorResource(id = R.color.PrimalColor)
    val color_box = colorResource(id = R.color.IconColor)
    val dpSpace = integerResource(id = R.integer.dp_space).dp
    val id_icon_notify = R.drawable.notifications_icon
    val id_icon_config = R.drawable.config_icon
    Row (modifier = modifier
        //.height(41.dp)
        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

        Icon(

            painter = painterResource(id = id_perfil),
            contentDescription = null,
            tint = color_text,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(dpSpace))
        Column {
            Text(
                text = "Bienvenido de nuevo",
                color = color_text,
                fontSize = 15.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = name,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(35.dp)
                .clip(shape = CircleShape)
                .background(color_box),
            contentAlignment = Alignment.Center
        ){
            Icon(
                painter = painterResource(id = id_icon_notify),
                contentDescription = "notificaciones",
                modifier = Modifier.size(30.dp))
        }
        Spacer(modifier = Modifier.width(dpSpace))
        Box(modifier = Modifier
            .size(35.dp)
            .clip(shape = CircleShape)
            .background(color_box),
            contentAlignment = Alignment.Center){
            Icon(
                painter = painterResource(id = id_icon_config),
                contentDescription = "notificaciones",
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        setting()
                    }
            )
        }
    }
}


