package pt.isel.pdm.nasaimageoftheday.ui.components


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pt.isel.pdm.nasaimageoftheday.model.NasaImage
import pt.isel.pdm.nasaimageoftheday.services.NasaImages

@Composable
@Preview()
fun NasaImageViewPreview() {
    NasaImageView(NasaImages.Images[2])
}

@Composable
fun NasaImageView(nasaImage: NasaImage, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth()) {

        Box() {
            AsyncImage(
                model = nasaImage.url,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(300.dp)
            )

            Text(
                text = nasaImage.title,
                color = Color.White,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.5f))
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .padding(12.dp)
            )

        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = nasaImage.date,
                color = MaterialTheme.colorScheme.secondary,
            )
            Spacer(modifier = Modifier.width(12.dp))
            if (nasaImage.author != null)
                Text(
                    text = nasaImage.author,
                    textAlign = TextAlign.End,
                )
        }

    }
}

@Composable
fun NasaImageTextView(nasaImage: NasaImage, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(3.dp)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
    ) {

        Row(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(text = nasaImage.date)

            if (nasaImage.author != null)
                Text(text = nasaImage.author.trim())
        }
    }
}