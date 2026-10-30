package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LanguageDefinition
import com.example.ui.theme.EditorBackground
import com.example.ui.theme.EditorGutter
import com.example.ui.theme.SurfaceBorderDark
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.SurfaceVariantDark
import com.example.ui.theme.TerminalCyan
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheatSheetDialog(
    sheetState: SheetState,
    language: LanguageDefinition,
    onDismiss: () -> Unit
) {
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
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), CircleShape)
                            .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Book, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text("${language.name} Documentation", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        Text("${language.category.displayName} • Inception: ${language.year}", fontSize = 11.sp, color = TextSecondary)
                    }
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close", tint = TextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Overview card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceVariantDark, RoundedCornerShape(10.dp))
                    .border(1.dp, SurfaceBorderDark, RoundedCornerShape(10.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Text("Overview & History", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(language.description, color = TextPrimary, fontSize = 13.sp, lineHeight = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Paradigms: ${language.paradigms.joinToString(", ")}", color = TerminalCyan, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                    Text("Runtime Execution Mode: ${language.executionMode.label}", color = TextSecondary, fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Syntax Cheat Sheet
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(EditorGutter, RoundedCornerShape(10.dp))
                    .border(1.dp, SurfaceBorderDark, RoundedCornerShape(10.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Text("Core Syntax & Idioms", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = language.cheatSheet,
                        color = TextPrimary,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Keywords
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(EditorBackground, RoundedCornerShape(10.dp))
                    .border(1.dp, SurfaceBorderDark, RoundedCornerShape(10.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Text("Standard Keywords & Reserved Tokens", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = language.keywords.joinToString("  "),
                        color = TerminalCyan,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
