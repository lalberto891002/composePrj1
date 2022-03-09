package com.example.myapplication

import android.content.Context
import android.graphics.drawable.PaintDrawable
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.createBitmap
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateCard();
                }
            }
        }
    }
}

@Composable
fun CreateCard(){
    val buttonclickedState = remember {
        mutableStateOf(false
        )
    }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
            Card(modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(corner = CornerSize(15.dp))
            ){

                Column(modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                    CreateImageProfile(Modifier.size(135.dp));
                    Divider()
                    CreateInfo()
                    val context = LocalContext.current
                   Button(onClick = {

                       buttonclickedState.value = !buttonclickedState.value;

                   }) {
                        Text(text = "Portafolio",
                            style = MaterialTheme.typography.button
                        )

                   }
                    if(buttonclickedState.value){
                        Content()
                    }else{
                        Box{}
                    }
                }
                

            }
    }
}
@Composable
private fun Content(){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)){
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
        border = BorderStroke(width = 2.dp,color = Color.LightGray)) {
            Portafolio(data = listOf("Project 1","Project2","Poject3",
                "Project 4","Project5","Poject6"))

        }
    }   
}

@Composable
fun Portafolio(data: List<String>) {
    
    LazyColumn{
        items(data) { item ->
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),
                shape = RectangleShape,elevation = 4.dp) {
               Row(modifier = Modifier
                   .padding(8.dp)
                   .background(MaterialTheme.colors.surface)) {
                    CreateImageProfile(modifier = Modifier.size(100.dp))
                   Column(Modifier.padding(7.dp).align(alignment = Alignment.CenterVertically)) {
                       Text(text = item,fontWeight = FontWeight.Bold)
                       Text(text = "A great Project",style = MaterialTheme.typography.body2)
                   }


               }

            }   
        }
    }
}



@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Luis A.",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )
        Text(
            text = "Android Compose Programmer",
            modifier = Modifier.padding(3.dp)
        )

        Text(
            text = "@tluisCompose",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1
        )

    }
}


@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier,stroke: Double = 0.5) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(stroke.dp), shape = CircleShape,
        border = BorderStroke(0.5.dp, color = Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)
    ) {

        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile image",
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        CreateCard();
    }
}