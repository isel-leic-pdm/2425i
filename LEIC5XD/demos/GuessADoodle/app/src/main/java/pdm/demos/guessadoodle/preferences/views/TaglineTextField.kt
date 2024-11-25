package pdm.demos.guessadoodle.preferences.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pdm.demos.guessadoodle.R
import pdm.demos.guessadoodle.ui.theme.GuessADoodleTheme

@Composable
fun TaglineTextField(
    tagline: String,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit = {},
    requestFocus: Boolean = false,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(requestFocus) {
        if (requestFocus) { focusRequester.requestFocus() }
    }

    TextField(
        value = tagline,
        singleLine = true,
        label = { Text(stringResource(R.string.preferences_tagline_label)) },
        leadingIcon = { Icon(imageVector = Icons.Outlined.Info, contentDescription = "Tagline") },
        modifier = modifier.testTag(TAGLINE_TEXT_TAG).focusRequester(focusRequester),
        enabled = enabled,
        onValueChange = onValueChange
    )
}

@Preview
@Composable
fun TaglineTextFieldPreview() {
    GuessADoodleTheme {
        TaglineTextField(tagline = "I'm a tagline")
    }
}