package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Input
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AiChatMessage
import com.example.model.LanguageDefinition
import com.example.ui.theme.EditorBackground
import com.example.ui.theme.EditorGutter
import com.example.ui.theme.SurfaceBorderDark
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.SurfaceVariantDark
import com.example.ui.theme.TerminalCyan
import com.example.ui.theme.TerminalGreen
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.VioletSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfflineAiSheet(
    sheetState: SheetState,
    messages: List<AiChatMessage>,
    currentLanguage: LanguageDefinition,
    onSendMessage: (String) -> Unit,
    onApplyCodeToEditor: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var inputQuery by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    val quickPrompts = listOf(
        "🧠 Explain Code",
        "🔍 Detect Bugs",
        "⚡ Optimize Speed",
        "📊 Big-O Complexity",
        "🔄 Transpile to Python",
        "🔄 Transpile to JavaScript",
        "🔄 Transpile to Rust",
        "💡 Generate Binary Search"
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = SurfaceDark,
        tonalElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .padding(horizontal = 16.dp)
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
                            .background(VioletSecondary.copy(alpha = 0.2f), CircleShape)
                            .border(1.dp, VioletSecondary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Psychology, contentDescription = "AI", tint = VioletSecondary, modifier = Modifier.size(20.dp))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("PolyCode Offline AI", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .background(TerminalGreen.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 5.dp, vertical = 2.dp)
                            ) {
                                Text("100% OFFLINE", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = TerminalGreen)
                            }
                        }
                        Text("Active Context: ${currentLanguage.name}", fontSize = 11.sp, color = TextSecondary)
                    }
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close", tint = TextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Quick Prompt Chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                quickPrompts.forEach { prompt ->
                    FilterChip(
                        selected = false,
                        onClick = { onSendMessage(prompt) },
                        label = { Text(prompt, fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = SurfaceVariantDark,
                            labelColor = TextPrimary
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            borderColor = SurfaceBorderDark,
                            enabled = true,
                            selected = false
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Chat Message List
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(messages) { msg ->
                    AiChatBubble(
                        message = msg,
                        onApplyCode = onApplyCodeToEditor,
                        onCopyText = { text ->
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            clipboard.setPrimaryClip(ClipData.newPlainText("PolyCode AI", text))
                            Toast.makeText(context, "Copied to clipboard!", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Query Input Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = inputQuery,
                    onValueChange = { inputQuery = it },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("ai_query_input"),
                    placeholder = { Text("Ask PolyCode AI (e.g. explain, optimize, debug)...", fontSize = 13.sp, color = TextMuted) },
                    singleLine = true,
                    shape = RoundedCornerShape(24.dp),
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

                IconButton(
                    onClick = {
                        if (inputQuery.isNotBlank()) {
                            val q = inputQuery
                            inputQuery = ""
                            onSendMessage(q)
                        }
                    },
                    modifier = Modifier
                        .size(46.dp)
                        .background(MaterialTheme.colorScheme.primary, CircleShape)
                        .testTag("ai_send_button")
                ) {
                    Icon(
                        Icons.Default.Send,
                        contentDescription = "Send",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AiChatBubble(
    message: AiChatMessage,
    onApplyCode: (String) -> Unit,
    onCopyText: (String) -> Unit
) {
    val isUser = message.isUser
    val alignment = if (isUser) Alignment.End else Alignment.Start
    val bg = if (isUser) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else EditorGutter
    val border = if (isUser) MaterialTheme.colorScheme.primary else SurfaceBorderDark

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .background(bg, RoundedCornerShape(12.dp))
                .border(1.dp, border, RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isUser) "You" else "PolyCode AI Assistant",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = if (isUser) MaterialTheme.colorScheme.primary else TerminalCyan
                    )
                    IconButton(
                        onClick = { onCopyText(message.codeSnippet ?: message.text) },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = TextSecondary, modifier = Modifier.size(14.dp))
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = message.text,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = TextPrimary
                )

                // Optional Code Block
                if (message.codeSnippet != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(EditorBackground, RoundedCornerShape(8.dp))
                            .border(1.dp, SurfaceBorderDark, RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = message.codeLanguage?.uppercase() ?: "CODE",
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )

                            ElevatedButton(
                                onClick = { onApplyCode(message.codeSnippet) },
                                modifier = Modifier.height(28.dp),
                                shape = RoundedCornerShape(6.dp),
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = Color.Black
                                ),
                                contentPadding = ButtonDefaults.ContentPadding
                            ) {
                                Icon(Icons.Default.Input, contentDescription = null, modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Apply to Editor", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = message.codeSnippet,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp,
                            color = TextPrimary,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }
    }
}
