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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.snapfood.R
import com.example.snapfood.domain.model.FilmInfo
import com.example.snapfood.domain.model.SpeciesInfo
import com.example.snapfood.domain.model.StarWarsCharacter
import com.example.snapfood.presentation.theme.SnapFoodTheme
import com.example.snapfood.presentation.ui.common.CommonCard
import com.example.snapfood.presentation.ui.common.CommonSpacing

@Composable
fun DetailsScreen(
    state: DetailScreenState,
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
                    character = character,
                    films = state.films,
                    loadingFilms = state.loadingFilms,
                    errorFilms = state.errorFilms,
                    species = state.species,
                    loadingSpecies = state.loadingSpecies,
                    errorSpecies = state.errorSpecies,
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
    films: Map<String, FilmInfo>,
    species: Map<String, SpeciesInfo>,
    loadingFilms: Set<String>,
    loadingSpecies: Set<String>,
    errorFilms: Map<String, String>,
    errorSpecies: Map<String, String>,
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
                title = stringResource(R.string.basic_information),
                items = character.getBasicInfo()
            )
        }

        item {
            SpeciesSection(
                title = stringResource(R.string.species),
                species = species,
                loadingSpecies = loadingSpecies,
                errorSpecies = errorSpecies
            )
        }

        item {
            FilmsSection(
                title = stringResource(R.string.films),
                films = films,
                loadingFilms = loadingFilms,
                errorFilms = errorFilms
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
                text = info.first,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = info.second,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun FilmsSection(
    title: String,
    films: Map<String, FilmInfo>,
    loadingFilms: Set<String>,
    errorFilms: Map<String, String>,
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

        when {
            loadingFilms.isNotEmpty() -> {
                LoadingCard()
            }
            films.isEmpty() -> {
                EmptyStateCard(message = stringResource(R.string.no_films_available))
            }
            else -> {
                films.forEach { (filmId, filmInfo) ->
                    FilmCard(
                        filmId = filmId,
                        filmInfo = filmInfo,
                        isLoading = false,
                        error = errorFilms[filmId]
                    )
                }
            }
        }
    }
}
@Composable
fun FilmCard(
    filmId: String,
    filmInfo: FilmInfo?,
    isLoading: Boolean,
    error: String?,
    modifier: Modifier = Modifier
) {
    CommonCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 2.dp
                        )
                    }
                }

                error != null -> {
                    Text(
                        text = stringResource(R.string.error_loading_film, filmId, error),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                filmInfo != null -> {
                    Text(
                        text = filmInfo.title,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (filmInfo.openingCrawl.isNotBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = filmInfo.openingCrawl,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SpeciesSection(
    title: String,
    species: Map<String, SpeciesInfo>,
    loadingSpecies: Set<String>,
    errorSpecies: Map<String, String>,
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

        when {
            loadingSpecies.isNotEmpty() -> {
                LoadingCard()
            }
            species.isEmpty() -> {
                EmptyStateCard(message = stringResource(R.string.no_species_information_available))
            }
            else -> {
                species.forEach { (speciesId, speciesInfo) ->
                    SpeciesCard(
                        speciesId = speciesId,
                        speciesInfo = speciesInfo,
                        isLoading = false,
                        error = errorSpecies[speciesId]
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingCard(
    modifier: Modifier = Modifier
) {
    CommonCard(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 2.dp
            )
        }
    }
}

@Composable
fun SpeciesCard(
    speciesId: String,
    speciesInfo: SpeciesInfo?,
    isLoading: Boolean,
    error: String?,
    modifier: Modifier = Modifier
) {
    CommonCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 2.dp
                        )
                    }
                }

                error != null -> {
                    Text(
                        text = "Error loading species $speciesId: $error",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                speciesInfo != null -> {
                    Text(
                        text = speciesInfo.name,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    SpeciesDetails(speciesInfo)
                }
            }
        }
    }
}

@Composable
private fun SpeciesDetails(speciesInfo: SpeciesInfo) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        SpeciesDetailItem(stringResource(R.string.classification), speciesInfo.classification)
        SpeciesDetailItem(stringResource(R.string.language), speciesInfo.language)
        SpeciesDetailItem(stringResource(R.string.designation), speciesInfo.designation)
        SpeciesDetailItem(stringResource(R.string.average_lifespan), speciesInfo.averageLifespan)
    }
}

@Composable
private fun SpeciesDetailItem(label: String, value: String) {
    Row {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
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
@Composable
fun EmptyStateCard(
    message: String,
    modifier: Modifier = Modifier
) {
    CommonCard(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
        }
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
            filmId = "1",
            filmInfo = FilmInfo(
                id = "1",
                title = "A New Hope",
                openingCrawl = "It is a period of civil war."
            ),
            isLoading = false,
            error = null
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
            onBackClick = {}
        )
    }
}