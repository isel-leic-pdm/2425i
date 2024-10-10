package pdm.demos.diceroller.about

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.diceroller.R
import pdm.demos.diceroller.ui.theme.DiceRollerTheme
import kotlin.math.min

/**
 * Used to represent information about a social network in the about screen
 * @param link the link to the social network
 * @param imageId the id of the image to be displayed
 */
data class SocialInfo(val link: Uri, @DrawableRes val imageId: Int)

/**
 * Tags used to identify the components of the AboutScreen in automated tests
 */
const val ABOUT_SCREEN_TEST_TAG = "AboutScreenTestTag"
const val AUTHOR_INFO_TEST_TAG = "AuthorInfoTestTag"
const val SOCIALS_ELEMENT_TEST_TAG = "SocialsElementTestTag"
const val NAVIGATE_BACK_TEST_TAG = "NavigateBack"

/**
 * Root composable for the about screen, the one that displays information about the app.
 * @param onNavigateBack the callback to be invoked when the user requests to go back to the
 * previous screen
 * @param onSendEmailRequested the callback to be invoked when the user requests to send an email
 * @param onOpenUrlRequested the callback to be invoked when the user requests to open an url
 * @param socials the social networks' links to be displayed
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onNavigateBack: () -> Unit = { },
    onSendEmailRequested: () -> Unit = { },
    onOpenUrlRequested: (Uri) -> Unit = { },
    socials: List<SocialInfo>
) {
    DiceRollerTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .testTag(ABOUT_SCREEN_TEST_TAG),
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.app_name)) },
                    navigationIcon = {
                        IconButton(
                            onClick = { onNavigateBack() },
                            modifier = Modifier.testTag(NAVIGATE_BACK_TEST_TAG)
                        ) {
                            Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "")
                        }
                    }
                )
            },
        ) { innerPadding ->
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
            ) {
                Author(onSendEmailRequested = onSendEmailRequested)
                Socials(
                    socials = socials,
                    onOpenUrlRequested = onOpenUrlRequested
                )
            }
        }
    }
}

/**
 * Composable used to display information about the author of the application
 */
@Composable
private fun Author(onSendEmailRequested: () -> Unit = { }) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .testTag(AUTHOR_INFO_TEST_TAG)
            .clickable { onSendEmailRequested() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_author),
            contentDescription = null,
            modifier = Modifier.sizeIn(100.dp, 100.dp, 200.dp, 200.dp)
        )
        Text(text = "Paulo Pereira", style = MaterialTheme.typography.titleLarge)
        Icon(imageVector = Icons.Default.Email, contentDescription = null)
    }
}

@Composable
private fun Socials(
    onOpenUrlRequested: (Uri) -> Unit = { },
    socials: List<SocialInfo>
) {
    val countPerRow = 4
    repeat(socials.size / countPerRow + 1) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            val start = it * countPerRow
            val end = min(a = (it + 1) * countPerRow, b = socials.size)
            socials.subList(fromIndex = start, toIndex = end).forEach {
                Social(id = it.imageId, onClick = { onOpenUrlRequested(it.link) })
            }
        }
    }
}

@Composable
private fun Social(@DrawableRes id: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = Modifier
            .sizeIn(maxWidth = 64.dp)
            .testTag(SOCIALS_ELEMENT_TEST_TAG)
            .clickable { onClick() }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun InfoScreenPreview() {
    val socialsPreview = listOf(
        SocialInfo(
            link = Uri.parse("https://www.linkedin.com/in/palbp/"),
            imageId = R.drawable.ic_linkedin
        ),
        SocialInfo(
            link = Uri.parse("https://www.twitch.tv/paulo_pereira"),
            imageId = R.drawable.ic_twitch
        ),
        SocialInfo(
            link = Uri.parse("https://www.youtube.com/@ProfPauloPereira"),
            imageId = R.drawable.ic_youtube
        ),
        SocialInfo(
            link = Uri.parse("https://discord.com/users/387733375064080396"),
            imageId = R.drawable.ic_discord
        ),
        SocialInfo(
            link = Uri.parse("https://palbp.github.io/"),
            imageId = R.drawable.ic_home
        )
    )
    AboutScreen(socials = socialsPreview)
}