package com.example.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.CheatSheetDialog
import com.example.ui.components.CodeEditorView
import com.example.ui.components.LanguagePickerSheet
import com.example.ui.components.OfflineAiSheet
import com.example.ui.components.SnippetsDialog
import com.example.ui.components.TerminalView
import com.example.ui.theme.BackgroundDark
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
import com.example.viewmodel.PolyCodeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolyCodeScreen(viewModel: PolyCodeViewModel) {
    val context = LocalContext.current

    val selectedLanguage by viewModel.selectedLanguage.collectAsState()
    val code by viewModel.code.collectAsState()
    val stdin by viewModel.stdin.collectAsState()
    val executionResult by viewModel.executionResult.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()
    val activeTerminalTab by viewModel.activeTerminalTab.collectAsState()

    val isLanguagePickerOpen by viewModel.isLanguagePickerOpen.collectAsState()
    val isAiSheetOpen by viewModel.isAiSheetOpen.collectAsState()
    val isSnippetsOpen by viewModel.isSnippetsOpen.collectAsState()
    val isCheatSheetOpen by viewModel.isCheatSheetOpen.collectAsState()
    val isStdinOpen by viewModel.isStdinOpen.collectAsState()

    val aiMessages by viewModel.aiMessages.collectAsState()
    val savedSnippets by viewModel.savedSnippets.collectAsState()

    val langSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val aiSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val snippetsSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val docsSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(900),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = BackgroundDark,
        topBar = {
            PolyCodeTopBar(
                languageName = selectedLanguage.name,
                languageExtension = selectedLanguage.extension,
                categoryIcon = selectedLanguage.category.icon,
                isStdinActive = isStdinOpen,
                onLanguageClick = { viewModel.setLanguagePickerOpen(true) },
                onToggleStdin = { viewModel.setStdinOpen(!isStdinOpen) },
                onOpenSnippets = { viewModel.setSnippetsOpen(true) },
                onOpenDocs = { viewModel.setCheatSheetOpen(true) },
                onOpenAi = { viewModel.setAiSheetOpen(true) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Optional Stdin Drawer
            AnimatedVisibility(visible = isStdinOpen) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SurfaceVariantDark)
                        .border(1.dp, SurfaceBorderDark)
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Column {
                        Text("Standard Input (stdin)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TerminalCyan)
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedTextField(
                            value = stdin,
                            onValueChange = { viewModel.updateStdin(it) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .testTag("stdin_input_field"),
                            placeholder = { Text("Pass input lines to program...", fontSize = 12.sp, color = TextMuted) },
                            textStyle = androidx.compose.ui.text.TextStyle(
                                fontFamily = FontFamily.Monospace,
                                fontSize = 12.sp,
                                color = TextPrimary
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = SurfaceBorderDark,
                                focusedContainerColor = EditorGutter,
                                unfocusedContainerColor = EditorGutter
                            )
                        )
                    }
                }
            }

            // Top Half: Code Editor
            CodeEditorView(
                code = code,
                onCodeChange = { viewModel.updateCode(it) },
                language = selectedLanguage,
                modifier = Modifier
                    .weight(1.15f)
                    .fillMaxWidth()
            )

            // Middle Action Divider Bar with Run Button & Stats
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                color = SurfaceDark,
                tonalElevation = 6.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(if (isRunning) TerminalCyan else TerminalGreen, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isRunning) "Executing offline..." else "Offline Compiler Engine",
                            fontSize = 11.sp,
                            color = TextSecondary,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    // Run Button
                    ElevatedButton(
                        onClick = { viewModel.runCode(context) },
                        enabled = !isRunning,
                        modifier = Modifier
                            .height(34.dp)
                            .then(if (isRunning) Modifier.scale(pulseScale) else Modifier)
                            .testTag("run_code_button"),
                        shape = RoundedCornerShape(17.dp),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.Black
                        ),
                        contentPadding = ButtonDefaults.ContentPadding
                    ) {
                        Icon(
                            Icons.Default.PlayArrow,
                            contentDescription = "Run",
                            modifier = Modifier.size(16.dp),
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isRunning) "Running" else "Run Code",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            // Bottom Half: Terminal Output / Web Preview / SQL Table
            TerminalView(
                result = executionResult,
                isRunning = isRunning,
                selectedLanguage = selectedLanguage,
                activeTab = activeTerminalTab,
                onTabSelected = { viewModel.setActiveTerminalTab(it) },
                onClearTerminal = { viewModel.clearTerminal() },
                modifier = Modifier
                    .weight(0.95f)
                    .fillMaxWidth()
            )
        }
    }

    // Modal Bottom Sheets
    if (isLanguagePickerOpen) {
        LanguagePickerSheet(
            sheetState = langSheetState,
            currentLanguage = selectedLanguage,
            onLanguageSelected = { viewModel.selectLanguage(it) },
            onDismiss = { viewModel.setLanguagePickerOpen(false) }
        )
    }

    if (isAiSheetOpen) {
        OfflineAiSheet(
            sheetState = aiSheetState,
            messages = aiMessages,
            currentLanguage = selectedLanguage,
            onSendMessage = { viewModel.sendAiPrompt(it) },
            onApplyCodeToEditor = { viewModel.applyAiCodeToEditor(it) },
            onDismiss = { viewModel.setAiSheetOpen(false) }
        )
    }

    if (isSnippetsOpen) {
        SnippetsDialog(
            sheetState = snippetsSheetState,
            currentLanguage = selectedLanguage,
            savedSnippets = savedSnippets,
            onLoadCode = { viewModel.updateCode(it) },
            onSaveSnippet = { viewModel.saveSnippet(it) },
            onDeleteSnippet = { viewModel.deleteSnippet(it) },
            onDismiss = { viewModel.setSnippetsOpen(false) }
        )
    }

    if (isCheatSheetOpen) {
        CheatSheetDialog(
            sheetState = docsSheetState,
            language = selectedLanguage,
            onDismiss = { viewModel.setCheatSheetOpen(false) }
        )
    }
}

@Composable
fun PolyCodeTopBar(
    languageName: String,
    languageExtension: String,
    categoryIcon: String,
    isStdinActive: Boolean,
    onLanguageClick: () -> Unit,
    onToggleStdin: () -> Unit,
    onOpenSnippets: () -> Unit,
    onOpenDocs: () -> Unit,
    onOpenAi: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = SurfaceDark,
        tonalElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // App Branding & Language Selector
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // Brackets Logo
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp))
                        .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "< / >",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Clickable Language Selector Pill
                Surface(
                    onClick = onLanguageClick,
                    shape = RoundedCornerShape(18.dp),
                    color = SurfaceVariantDark,
                    border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorderDark),
                    modifier = Modifier.testTag("language_selector_btn")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = categoryIcon, fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = languageName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = TextPrimary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = ".${languageExtension.take(3)}",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Select Language",
                            tint = TextSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            // Action Icons: Stdin, Snippets, Docs, Offline AI
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onToggleStdin,
                    modifier = Modifier.testTag("stdin_btn")
                ) {
                    Icon(
                        Icons.Default.Keyboard,
                        contentDescription = "Standard Input",
                        tint = if (isStdinActive) MaterialTheme.colorScheme.primary else TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                IconButton(
                    onClick = onOpenSnippets,
                    modifier = Modifier.testTag("snippets_btn")
                ) {
                    Icon(
                        Icons.Default.Bookmarks,
                        contentDescription = "Snippets",
                        tint = TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                IconButton(
                    onClick = onOpenDocs,
                    modifier = Modifier.testTag("docs_btn")
                ) {
                    Icon(
                        Icons.Default.MenuBook,
                        contentDescription = "Documentation",
                        tint = TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // AI Assistant Button with Glow Border
                Surface(
                    onClick = onOpenAi,
                    shape = RoundedCornerShape(16.dp),
                    color = VioletSecondary.copy(alpha = 0.2f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, VioletSecondary),
                    modifier = Modifier.testTag("ai_assistant_btn")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Psychology,
                            contentDescription = "Offline AI",
                            tint = VioletSecondary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "AI Assist",
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = TextPrimary
                        )
                    }
                }
            }
        }
    }
}
