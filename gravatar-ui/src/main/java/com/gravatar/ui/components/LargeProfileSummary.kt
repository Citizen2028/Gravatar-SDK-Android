package com.gravatar.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gravatar.api.models.Account
import com.gravatar.api.models.Email
import com.gravatar.api.models.UserProfile
import com.gravatar.ui.GravatarTheme
import com.gravatar.ui.components.atomic.Avatar
import com.gravatar.ui.components.atomic.DisplayName
import com.gravatar.ui.components.atomic.UserInfo
import com.gravatar.ui.components.atomic.ViewProfileButton

/**
 * [LargeProfileSummary] is a composable that displays a user's profile in a resumed way.
 * Given a [UserProfile], it displays a [LargeProfileSummary] using the other atomic components provided within the SDK.
 *
 * @param profile The user's profile information
 * @param modifier Composable modifier
 */
@Composable
public fun LargeProfileSummary(profile: UserProfile, modifier: Modifier = Modifier) {
    GravatarTheme {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Avatar(
                profile = profile,
                size = 132.dp,
                modifier = Modifier.clip(CircleShape),
            )
            DisplayName(profile, modifier = Modifier.padding(top = 16.dp))
            UserInfo(
                profile,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.outline,
                    textAlign = TextAlign.Center,
                ),
            )
            ViewProfileButton(profile, Modifier.padding(0.dp), inlineContent = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LargeProfileSummaryPreview() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        LargeProfileSummary(
            UserProfile(
                hash = "1234567890",
                displayName = "Dominique Doe",
                preferredUsername = "ddoe",
                jobTitle = "Farmer",
                company = "Farmers United",
                currentLocation = "Crac'h, France",
                pronouns = "They/Them",
                accounts = listOf(
                    Account(name = "Mastodon", url = "https://mastodon.social/@ddoe"),
                    Account(name = "Tumblr", url = "https://ddoe.tumblr.com"),
                    Account(name = "WordPress", url = "https://ddoe.wordpress.com"),
                ),
                aboutMe = "I'm a farmer, I love to code. I ride my bicycle to work. One apple a day keeps the " +
                    "doctor away. This about me description is quite long, this is good for testing.",
                emails = listOf(Email(primary = true, value = "john@doe.com")),
            ),
        )
    }
}
