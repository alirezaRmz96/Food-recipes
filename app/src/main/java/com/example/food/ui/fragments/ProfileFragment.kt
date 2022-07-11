package com.example.food.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.CalendarContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.ImagePainter
import com.example.food.R
import com.example.food.databinding.FragmentProfileBinding
import com.google.android.material.composethemeadapter.MdcTheme


class ProfileFragment : Fragment() {
    private lateinit var mBinding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentProfileBinding.bind(view)
        val profile = mBinding.profile

        profile.setContent {
            MdcTheme {
                ProfileScaffold()
            }
        }
    }
}

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ProfileScaffold() {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp)
                .verticalScroll(rememberScrollState())
            ,
        ) {
            Text(
                text = "My Profile",
                fontSize = 25.sp,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
            ) {
                Image(
                    painterResource(id = R.drawable.avatar),
                    contentDescription = "profile image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .weight(2.0f)
                        .padding(start = 20.dp)
//                        .height(80.dp)
//                        .width(80.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        textAlign = TextAlign.Start,
                        text = "Ali Reza",
                        fontSize = 25.sp,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(start = 30.dp)
                    )
                    Text(
                        text = "software eng",
                        fontSize = 20.sp,
                        maxLines = 1,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(start = 30.dp)
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(end = 20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "showing"
                    )
                }
            }
            Column(
                Modifier.padding(
                    top = 20.dp,
                    start = 25.dp
                )
            ) {
                Text(
                    text = "Dashbord",
                    color = Gray
                )
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(id = R.drawable.pay),
                        contentDescription = "payment",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(color = Color.Green)
                            .padding(7.dp)
                    )
                    Text(
                        text = "Payments",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .weight(2.0f),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "arrow",
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(id = R.drawable.achievements),
                        contentDescription = "achievements",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(color = Color.Yellow)
                            .padding(7.dp)
                    )
                    Text(
                        text = "achievements",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .weight(2.0f),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "arrow",
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(id = R.drawable.privacy),
                        contentDescription = "privacy",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(colorResource(id = R.color.privacy))
                            .padding(7.dp)
                    )
                    Text(
                        text = "privacy",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .weight(2.0f),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "arrow",
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(id = R.drawable.setting),
                        contentDescription = "settings",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(colorResource(id = R.color.settings))
                            .padding(7.dp)

                    )
                    Text(
                        text = "settings",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .weight(2.0f),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "arrow",
                        )
                    }
                }
            }
            Column(modifier = Modifier.padding(25.dp)) {
                Text(
                    text = "My Account",
                    color = Gray
                )
                Text(
                    text = "Switch TO Another Account",
                    color = Color.Blue,
                    modifier = Modifier.padding(top = 30.dp),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )
                Text(
                    text = "Log Out",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 30.dp),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )
            }
        }
    }
}