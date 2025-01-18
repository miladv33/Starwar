package com.example.snapfood.presentation.ui.details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.snapfood.presentation.theme.SnapFoodTheme
import com.example.snapfood.presentation.ui.common.CommonCard
import com.example.snapfood.presentation.ui.common.CommonSpacing

@Composable
fun DetailsScreen(
    characterName: String = "Luke Skywalker",
    onBackClick: () -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        DetailHeader(
            characterName = characterName,
            onBackClick = onBackClick
        )
        DetailContent()
    }
}

@Composable
fun DetailHeader(
    characterName: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier.padding(15.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                text = characterName,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            InfoSection(
                title = "Basic Information",
                items = listOf(
                    InfoItem("Birth Year", "19 BBY"),
                    InfoItem("Height", "172 cm (5 ft 8 in)")
                )
            )
        }

        item {
            InfoSection(
                title = "Species Information",
                items = listOf(
                    InfoItem("Species", "Human"),
                    InfoItem("Language", "Galactic Basic"),
                    InfoItem("Homeworld", "Tatooine (Population: 200,000)")
                )
            )
        }

        item {
            FilmsSection(
                title = "Films",
                films = listOf(
                    Film(
                        "Episode IV: A New Hope",
                        "It is a period of civil war. Rebel spaceships, striking from a hidden base..."
                    ),
                    Film(
                        "Episode V: The Empire Strikes Back",
                        "It is a dark time for the Rebellion. Although the Death Star has been destroyed..."
                    )
                )
            )
        }
    }
}

@Composable
fun InfoSection(
    title: String,
    items: List<InfoItem>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        items.forEach { item ->
            InfoItemCard(item)
        }
    }
}

@Composable
fun InfoItemCard(
    item: InfoItem,
    modifier: Modifier = Modifier
) {
    CommonCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(CommonSpacing.cardPadding)
        ) {
            Text(
                text = item.label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = item.value,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}


@Composable
fun FilmsSection(
    title: String,
    films: List<Film>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        films.forEach { film ->
            FilmCard(film)
        }
    }
}

@Composable
fun FilmCard(
    film: Film,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text = film.title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = film.crawl,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontStyle = FontStyle.Italic
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

data class InfoItem(
    val label: String,
    val value: String
)

data class Film(
    val title: String,
    val crawl: String
)

@Preview(showBackground = true)
@Composable
fun DetailHeaderPreview() {
    SnapFoodTheme {
        DetailHeader(
            characterName = "Luke Skywalker",
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InfoItemCardPreview() {
    SnapFoodTheme {
        InfoItemCard(
            InfoItem("Birth Year", "19 BBY")
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilmCardPreview() {
    SnapFoodTheme {
        FilmCard(
            Film(
                "Episode IV: A New Hope",
                "It is a period of civil war. Rebel spaceships, striking from a hidden base..."
            )
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun DetailsScreenPreview() {
    SnapFoodTheme {
        DetailsScreen()
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun DetailsScreenDarkPreview() {
    SnapFoodTheme(darkTheme = true) {
        DetailsScreen()
    }
}