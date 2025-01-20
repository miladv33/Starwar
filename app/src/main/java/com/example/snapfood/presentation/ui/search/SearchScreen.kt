package com.example.snapfood.presentation.ui.search

import androidx.compose.runtime.Composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.snapfood.R
import com.example.snapfood.domain.model.StarWarsCharacter
import com.example.snapfood.presentation.theme.SnapFoodTheme
import com.example.snapfood.presentation.ui.common.CommonCard

@Composable
fun SearchScreen(
    state: SearchScreenState,
    modifier: Modifier = Modifier,
    onEvent: (SearchScreenEvent) -> Unit,
    onNavigateToDetails: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        SearchHeader()
        SearchBox(
            query = state.searchQuery,
            onQueryChange = { onEvent(SearchScreenEvent.OnSearchQueryChange(it)) }
        )

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            if (state.characters.isEmpty() && state.searchQuery.isNotBlank()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.no_characters_found),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            } else {
                CharactersList(
                    characters = state.characters,
                    onCharacterClick = onNavigateToDetails
                )
            }
        }
    }
}

@Composable
fun SearchHeader(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Text(
            text = stringResource(R.string.search_screen_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(15.dp)
        )
    }
}

@Composable
fun SearchBox(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = {
                Text(stringResource(R.string.search_placeholder))
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(25.dp))
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.secondary,
                    RoundedCornerShape(25.dp)
                ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary
            )
        )
    }
}

@Composable
fun CharactersList(
    characters: List<StarWarsCharacter>,
    onCharacterClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            items = characters,
            key = { it.id }
        ) { character ->
            CharacterCard(
                character = character,
                onClick = {
                    onCharacterClick(character.id)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatusBarPreview() {
    SnapFoodTheme {
    }
}

@Preview(showBackground = true)
@Composable
fun SearchHeaderPreview() {
    SnapFoodTheme {
        SearchHeader()
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBoxPreview() {
    SnapFoodTheme {
        SearchBox(
            query = "Luke",
            onQueryChange = {}
        )
    }
}

object CharacterCardDefaults {
    val CardCornerRadius = 8.dp
    val ContentPadding = 16.dp
    val SpaceBetweenElements = 5.dp
}

@Composable
fun CharacterCard(
    character: StarWarsCharacter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CommonCard(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(CharacterCardDefaults.CardCornerRadius))
            .clickable(onClick = onClick)
    ) {
        CharacterCardContent(
            name = character.name,
            description = character.getDescription(),
            modifier = Modifier.padding(CharacterCardDefaults.ContentPadding)
        )
    }
}

@Composable
private fun CharacterCardContent(
    name: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(CharacterCardDefaults.SpaceBetweenElements))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterCardPreview() {
    SnapFoodTheme {
        CharacterCard(
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
                films = emptyList(),
                species = emptyList(),
                vehicles = emptyList(),
                starships = emptyList()
            ),
            onClick = {}
        )
    }
}

// Dark theme preview
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun SearchScreenPreview() {
    SnapFoodTheme {
        SearchScreen(
            state = SearchScreenState(
                searchQuery = "Luke",
                characters = listOf(
                    StarWarsCharacter(
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
                        films = emptyList(),
                        species = emptyList(),
                        vehicles = emptyList(),
                        starships = emptyList()
                    )
                )
            ),
            onEvent = {},
            onNavigateToDetails = {}
        )
    }
}