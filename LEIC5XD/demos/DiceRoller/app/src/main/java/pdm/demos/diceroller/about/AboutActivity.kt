package pdm.demos.diceroller.about

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import pdm.demos.diceroller.main.TAG
import pdm.demos.diceroller.R

/**
 * The activity responsible for displaying the about screen. Notice the absence of a ViewModel.
 * In this case ee don't need one because the screen is static and doesn't have any dynamic data.
 */
class AboutActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AboutScreen(
                onNavigateBack = { finish() },
                onSendEmailRequested = { openSendEmail() },
                onOpenUrlRequested = { openURL(it) },
                socials = socialLinks
            )
        }
    }

    private fun openSendEmail() {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(AUTHOR_EMAIL))
                putExtra(Intent.EXTRA_SUBJECT, EMAIL_SUBJECT)
            }

            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Log.e(TAG, "Failed to send email", e)
            Toast
                .makeText(
                    this,
                    R.string.activity_info_no_suitable_app,
                    Toast.LENGTH_LONG
                )
                .show()
        }
    }

    private fun openURL(url: Uri) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, url)
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Log.e(TAG, "Failed to open URL", e)
            Toast
                .makeText(
                    this,
                    R.string.activity_info_no_suitable_app,
                    Toast.LENGTH_LONG
                )
                .show()
        }
    }
}

val socialLinks = listOf(
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

private const val AUTHOR_EMAIL = "paulo.pereira@isel.pt"
private const val EMAIL_SUBJECT = "About the Dice Roller App"