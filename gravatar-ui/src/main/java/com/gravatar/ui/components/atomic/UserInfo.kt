package com.gravatar.ui.components.atomic

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.gravatar.api.models.UserProfile
import com.gravatar.extensions.formattedUserInfo

/**
 * [UserInfo] is a composable that displays a user's information in a formatted way.
 * The user's information includes their company, job title, pronunciation, pronouns, and current
 * location when available.
 *
 * @param profile The user's profile information
 * @param modifier Composable modifier
 * @param textStyle The style to apply to the default text content
 * @param content Composable to display the formatted user information
 */
@Composable
public fun UserInfo(
    profile: UserProfile,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outline),
    content: @Composable ((String, Modifier) -> Unit) = { userInfo, contentModifier ->
        UserInfoDefaultContent(userInfo, textStyle, contentModifier)
    },
) {
    content(profile.formattedUserInfo(), modifier)
}

@Composable
private fun UserInfoDefaultContent(userInfo: String, textStyle: TextStyle, modifier: Modifier) = Text(
    userInfo,
    modifier = modifier,
    maxLines = 2,
    overflow = TextOverflow.Ellipsis,
    style = textStyle,
)

@Preview
@Composable
private fun UserInfoPreview() {
    UserInfo(
        UserProfile(
            "",
            currentLocation = "Crac'h, France",
            pronouns = "They/Them",
            pronunciation = "Tony with a P",
            jobTitle = "Pony Trainer",
            company = "Pony Land",
        ),
    )
}
