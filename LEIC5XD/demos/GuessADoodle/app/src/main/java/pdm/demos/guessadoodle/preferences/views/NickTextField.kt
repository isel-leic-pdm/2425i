package pdm.demos.guessadoodle.preferences.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pdm.demos.guessadoodle.R
import pdm.demos.guessadoodle.domain.MAX_NICK_SIZE
import pdm.demos.guessadoodle.domain.MIN_NICK_SIZE
import pdm.demos.guessadoodle.ui.theme.GuessADoodleTheme

@Composable
fun NickTextField(
    nick: String,
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    TextField(
        value = nick,
        singleLine = true,
        supportingText = {
            Text(stringResource(R.string.preferences_nick_hint, MIN_NICK_SIZE, MAX_NICK_SIZE))
        },
        label = { Text(stringResource(R.string.preferences_nick_label)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Face, contentDescription = "Nick") },
        modifier = modifier.testTag(NICK_TEXT_TAG),
        onValueChange = onValueChange
    )
}

@Preview
@Composable
fun NickTextFieldPreview() {
    GuessADoodleTheme {
        NickTextField(nick = "John Doe")
    }
}