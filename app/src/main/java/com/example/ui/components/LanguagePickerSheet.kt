package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catalog.LanguageCatalog
import com.example.model.LanguageCategory
import com.example.model.LanguageDefinition
import com.example.ui.theme.EditorGutter
import com.example.ui.theme.SurfaceBorderDark
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.SurfaceVariantDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguagePickerSheet(
    sheetState: SheetState,
    currentLanguage: LanguageDefinition,
    onLanguageSelected: (LanguageDefinition) -> Unit,
    onDismiss: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<LanguageCategory?>(null) }

    val filteredLanguages = remember(searchQuery, selectedCategory) {
        LanguageCatalog.allLanguages.filter { lang ->
            val matchesCategory = selectedCategory == null || lang.category == selectedCategory
            val matchesSearch = searchQuery.isBlank() ||
                    lang.name.contains(searchQuery, ignoreCase = true) ||
                    lang.extension.contains(searchQuery, ignoreCase = true) ||
                    lang.id.contains(searchQuery, ignoreCase = true) ||
                    lang.keywords.any { it.contains(searchQuery, ignoreCase = true) }
            matchesCategory && matchesSearch
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = SurfaceDark,
        tonalElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .padding(horizontal = 16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "100+ Language Catalog",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(
                        text = "${LanguageCatalog.allLanguages.size} Languages & Web Technologies Available",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close", tint = TextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("language_search_input"),
                placeholder = { Text("Search by name, extension, keyword (e.g. python, rust, sql)...", fontSize = 13.sp, color = TextMuted) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = TextSecondary) },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = SurfaceBorderDark,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    focusedContainerColor = SurfaceVariantDark,
                    unfocusedContainerColor = SurfaceVariantDark
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Category Chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { selectedCategory = null },
                    label = { Text("All (${LanguageCatalog.allLanguages.size})", fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = Color.Black
                    )
                )

                LanguageCategory.values().forEach { cat ->
                    FilterChip(
                        selected = selectedCategory == cat,
                        onClick = { selectedCategory = if (selectedCategory == cat) null else cat },
                        label = { Text("${cat.icon} ${cat.displayName}", fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.Black
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Language List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("language_list"),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredLanguages) { lang ->
                    LanguageListItem(
                        language = lang,
                        isSelected = lang.id == currentLanguage.id,
                        onClick = {
                            onLanguageSelected(lang)
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LanguageListItem(
    language: LanguageDefinition,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) MaterialTheme.colorScheme.primary else SurfaceBorderDark
    val bg = if (isSelected) SurfaceVariantDark else EditorGutter

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(bg, RoundedCornerShape(10.dp))
            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Extension badge
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(SurfaceVariantDark, CircleShape)
                    .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = language.extension.take(3).uppercase(),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = language.name,
                        color = TextPrimary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "• ${language.year}",
                        color = TextMuted,
                        fontSize = 11.sp
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "${language.category.icon} ${language.category.displayName} • ${language.executionMode.label}",
                    color = TextSecondary,
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }
        }

        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
