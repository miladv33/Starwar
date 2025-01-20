package com.example.snapfood.presentation.ui.details

import android.util.Log
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
import com.example.snapfood.domain.model.StarWarsCharacter
import com.example.snapfood.presentation.theme.SnapFoodTheme
import com.example.snapfood.presentation.ui.common.CommonCard
import com.example.snapfood.presentation.ui.common.CommonSpacing

@Composable
fun DetailsScreen(
    characterName: String = "Luke Skywalker",
    state: DetailScreenState,
    onEvent: (DetailScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        DetailHeader(
            characterName = state.character?.name ?: "",
            onBackClick = onBackClick
        )

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            state.character?.let { character ->
                DetailContent(
                    character = character
                )
            }
        }
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
    character: StarWarsCharacter,
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
                items = character.getBasicInfo()
            )
        }

        item {
            InfoSection(
                title = "Species Information",
                items = character.getSpeciesInfo()
            )
        }

        item {
            FilmsSection(
                title = "Films",
                films = character.films
            )
        }
    }
}

@Composable
fun InfoSection(
    title: String,
    items: List<Pair<String, String>>,
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
    info: Pair<String, String>,
    modifier: Modifier = Modifier
) {
    CommonCard(modifier = modifier) {
        Column(
            modifier = Modifier.padding(CommonSpacing.cardPadding)
        ) {
            Text(
                text = info.first,  // label
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = info.second,  // value
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun FilmsSection(
    title: String,
    films: List<String>,
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
    title: String,
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
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}


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
            Pair("Birth Year", "19 BBY")
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilmCardPreview() {
    SnapFoodTheme {
        FilmCard(
            title = "Episode IV: A New Hope"
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun DetailsScreenPreview() {
    SnapFoodTheme {
        DetailsScreen(
            state = DetailScreenState(
                character = StarWarsCharacter(
                    id = "1",
                    name = "Luke Skywalker",
                    birthYear = "19 BBY",
                    height = "172",
                    mass = "77",
                    hairColor = "blond",
                    skinColor = "fair",
                    eyeColor = "blue",
                    gender = "male",
                    homeWorld = "Tatooine",
                    films = listOf("A New Hope", "Empire Strikes Back"),
                    species = emptyList(),
                    vehicles = emptyList(),
                    starships = emptyList()
                )
            ),
            onEvent = {},
            onBackClick = {}
        )
    }
}