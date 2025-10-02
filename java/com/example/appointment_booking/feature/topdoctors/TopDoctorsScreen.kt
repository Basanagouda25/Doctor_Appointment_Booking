package com.example.appointment_booking.feature.topdoctors

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appointment_booking.R
import com.example.appointment_booking.core.model.DoctorModel
import com.example.appointment_booking.feature.topdoctors.components.DoctorRowCard

@Composable
fun TopDoctorsScreen(doctors:List<DoctorModel>,
                     onBack:()-> Unit,
                     onOpendetail:(DoctorModel)-> Unit,
                     isLoading: Boolean = false
){
    val listState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(state = listState,
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .height(56.dp)
                ){
                    IconButton(onClick = onBack,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    ) {
                        Icon(
                            painterResource(R.drawable.back),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                    Text(
                        text = "Recommended List",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            items(
                doctors,
                key = { (it.Name) + "_" + (it.Mobile ?: "") }
            ) { doc ->
                DoctorRowCard(
                    item = doc,
                    onMakeClick = { onOpendetail(doc) }
                )
            }
        }
        when{
            isLoading->{
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            doctors.isEmpty()->{
                Text(
                    text = "it's Empty",
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

    }
}

@Preview
@Composable
fun TopDoctorsScreenPreview(){
    val sampleDoctors = listOf(
        DoctorModel(
            Name = "Dr. John Doe",
            Special = "Cardiologist",
            Rating = 4.8,
            Picture = "https://picsum.photos/200",
            Mobile = "9876543210"
        ),
        DoctorModel(
            Name = "Dr. Emily Carter",
            Special = "Dermatologist",
            Rating = 4.6,
            Picture = "https://picsum.photos/201",
            Mobile = "9876543211"
        ),
        DoctorModel(
            Name = "Dr. Rahul Mehta",
            Special = "Orthopedic Surgeon",
            Rating = 4.9,
            Picture = "https://picsum.photos/202",
            Mobile = "9876543212"
        )
    )
    TopDoctorsScreen(doctors = sampleDoctors, onBack = {}, onOpendetail = {})
}