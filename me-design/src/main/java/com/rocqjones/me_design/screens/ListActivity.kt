package com.rocqjones.me_design.screens

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocqjones.me_design.ui.theme.MeSDKTheme
import com.rocqjones.me_design.R

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeSDKTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    GenerateLazyList()
                }
            }
        }
    }
}

@Composable
fun GenerateLazyList(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    /**
     * reusable fun to avoiding code duplication
     * Creating a performant lazy list
     */
    // for loop threads too long, alternative is LazyColumn and items
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { n ->
            ItemCard(name = n)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ItemCard(name: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
private fun CardContent(name: String) {
    /**
     * Btn state management with remember.
     * - If you expand item number 1, you scroll away to number 20 and come back to 1, you'll notice that 1 is back to the original size.
     * - You could save this data with rememberSaveable if it were a requirement
     */
    val expanded = remember { mutableStateOf(false) }

    /**
     * additional variable that depends on our btn state
     * Animate collapsing.
     * - Let's do something more fun like adding a spring-based animation
     * - The spring spec does not take any time-related parameters. Instead it relies on physical
     * properties (damping and stiffness) to make animations more natural.
     */
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier.weight(1f).padding(12.dp) //.padding(bottom = extraPadding.coerceAtLeast(0.dp)) // due to animation we're making sure that padding is never negative, otherwise it could crash the app.
        ) {
            Text(text = "Hello")
            Text(text = "$name.",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )

            if (expanded.value) {
                Text(
                    text = ("Compose ipsum color sit lazy, padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }

        IconButton(onClick = { expanded.value = !expanded.value }) {
            Icon(
                // imageVector = if (expanded) Filled.ExpandLess else Filled.ExpandMore,
                painter = if (expanded.value) painterResource(id = R.drawable.baseline_expand_less_24) else painterResource(id = R.drawable.baseline_expand_more_24),
                contentDescription = if (expanded.value) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    name = "DefaultPreviewLight"
)
@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    MeSDKTheme {
        GenerateLazyList()
    }
}