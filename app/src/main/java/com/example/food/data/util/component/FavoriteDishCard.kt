package com.example.food.data.util.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.food.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoriteDishCard() {
    Box(modifier = Modifier.padding(8.dp)) {
        Card(
            modifier = Modifier
                .requiredHeight(250.dp)
                .requiredWidth(200.dp),
            elevation = 5.dp,
            shape = RoundedCornerShape(10.dp),
            backgroundColor = colorResource(id = R.color.saved_button)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(id = R.drawable.food),
                    contentDescription = "image for recipes",
                    modifier =
                    Modifier
                        .height(150.dp)
                        .width(100.dp)
                        .padding(top = 10.dp),
                )
                Text(
                    text = "Pizza",
                    modifier = Modifier.padding(bottom = 5.dp),
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Text(
                    text = "Sushi",
                    modifier = Modifier.padding(bottom = 5.dp),
                    fontSize = 15.sp,
                    color = colorResource(R.color.changed_color)
                )
                Button(
                    onClick = { /*TODO*/ },
                    elevation = ButtonDefaults.elevation(8.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    modifier = Modifier
                        .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Show More",
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun ShowThis() {
    FavoriteDishCard()
}