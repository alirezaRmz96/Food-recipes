package com.example.food.data.util.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.food.R
import com.example.food.data.model.receFromId.RecepFromIdList

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoriteDishCard(
    mList : RecepFromIdList
) {

    Box(modifier = Modifier.padding(8.dp)) {
        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(),
            elevation = 5.dp,
            shape = RoundedCornerShape(10.dp),
            backgroundColor = colorResource(id = R.color.saved_button)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberImagePainter(mList.image!!),
                    contentDescription = "image for recipes",
                    contentScale = ContentScale.Crop,
                    modifier =
                    Modifier
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                        .height(150.dp)
                        .width(150.dp)
                    ,
                )
                Text(
                    text = mList.title!!,
                    modifier = Modifier.padding( 5.dp).padding(start = 2.dp),
                    fontSize = 20.sp,
                    color = Color.Black,
                    maxLines = 1,
//                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
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



//@Preview
//@Composable
//fun ShowThis() {
//    FavoriteDishCard(RecepFromIdList(
//        1,"","",
//        "",12.2,
//
//    ))
//}