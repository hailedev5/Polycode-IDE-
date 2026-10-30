package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.model.LanguageDefinition
import com.example.model.SavedSnippet
import com.example.ui.theme.EditorGutter
import com.example.ui.theme.SurfaceBorderDark
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.SurfaceVariantDark
import com.example.ui.theme.TerminalCyan
import com.example.ui.theme.TerminalRed
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnippetsDialog(
    sheetState: SheetState,
    currentLanguage: LanguageDefinition,
    savedSnippets: List<SavedSnippet>,
    onLoadCode: (String) -> Unit,
    onSaveSnippet: (String) -> Unit,
    onDeleteSnippet: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var newSnippetTitle by remember { mutableStateOf("") }

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
                    Text("Code Templates & Snippets", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text("Preset Algorithms & User Saved Scripts", fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close", tint = TextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Built-in Templates for Current Language
            Text("Built-in ${currentLanguage.name} Templates", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextSecondary)
            Spacer(modifier = Modifier.height(6.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TemplateCard(
                    title = "Hello World",
                    subtitle = "Standard Starter",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onLoadCode(currentLanguage.helloWorldCode)
                        onDismiss()
                    }
                )

                TemplateCard(
                    title = "Algorithm Demo",
                    subtitle = "Sorting & Logic",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onLoadCode(currentLanguage.algorithmCode)
                        onDismiss()
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Save Current Code Section
            Text("Save Current Script", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextSecondary)
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = newSnippetTitle,
                    onValueChange = { newSnippetTitle = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Snippet Title...", fontSize = 12.sp, color = TextMuted) },
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = SurfaceBorderDark,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        focusedContainerColor = SurfaceVariantDark,
                        unfocusedContainerColor = SurfaceVariantDark
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (newSnippetTitle.isNotBlank()) {
                            onSaveSnippet(newSnippetTitle)
                            newSnippetTitle = ""
                        }
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.Black)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Save", modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Save", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Saved User Snippets
            Text("Saved Offline Snippets (${savedSnippets.size})", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextSecondary)
            Spacer(modifier = Modifier.height(8.dp))

            if (savedSnippets.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No saved snippets yet. Save your custom algorithms to access them offline anytime!", color = TextMuted, fontSize = 12.sp)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(savedSnippets) { snippet ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(EditorGutter, RoundedCornerShape(8.dp))
                                .border(1.dp, SurfaceBorderDark, RoundedCornerShape(8.dp))
                                .clickable {
                                    onLoadCode(snippet.code)
                                    onDismiss()
                                }
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(snippet.title, fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 14.sp)
                                Spacer(modifier = Modifier.height(2.dp))
                                Text("Language: ${snippet.languageId.uppercase()} • ${snippet.code.lines().size} lines", color = TextSecondary, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                            }
                            IconButton(onClick = { onDeleteSnippet(snippet.id) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = TerminalRed, modifier = Modifier.size(18.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TemplateCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(SurfaceVariantDark, RoundedCornerShape(10.dp))
            .border(1.dp, SurfaceBorderDark, RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(12.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Code, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(title, fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 13.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(subtitle, color = TextSecondary, fontSize = 11.sp)
        }
    }
}
