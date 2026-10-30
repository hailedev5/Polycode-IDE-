package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.TableChart
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.model.ExecutionMode
import com.example.model.ExecutionResult
import com.example.model.LanguageDefinition
import com.example.ui.theme.EditorGutter
import com.example.ui.theme.SurfaceBorderDark
import com.example.ui.theme.SurfaceVariantDark
import com.example.ui.theme.TerminalBackground
import com.example.ui.theme.TerminalCyan
import com.example.ui.theme.TerminalGreen
import com.example.ui.theme.TerminalRed
import com.example.ui.theme.TerminalYellow
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun TerminalView(
    result: ExecutionResult?,
    isRunning: Boolean,
    selectedLanguage: LanguageDefinition,
    activeTab: Int,
    onTabSelected: (Int) -> Unit,
    onClearTerminal: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val tabs = listOf(
        Pair("Console", Icons.Default.Terminal),
        Pair("Web Preview", Icons.Default.Language),
        Pair("SQL Table", Icons.Default.TableChart),
        Pair("AST & Specs", Icons.Default.DataObject)
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(TerminalBackground)
    ) {
        // Tab Header & Actions
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceVariantDark)
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ScrollableTabRow(
                selectedTabIndex = activeTab,
                modifier = Modifier.weight(1f),
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary,
                edgePadding = 0.dp,
                indicator = { tabPositions ->
                    if (activeTab < tabPositions.size) {
                        TabRowDefaults.SecondaryIndicator(
                            Modifier.tabIndicatorOffset(tabPositions[activeTab]),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            ) {
                tabs.forEachIndexed { index, (title, icon) ->
                    Tab(
                        selected = activeTab == index,
                        onClick = { onTabSelected(index) },
                        modifier = Modifier.testTag("terminal_tab_$index"),
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(icon, contentDescription = title, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(title, fontSize = 12.sp, fontWeight = if (activeTab == index) FontWeight.Bold else FontWeight.Normal)
                            }
                        }
                    )
                }
            }

            // Copy Action
            IconButton(
                onClick = {
                    val textToCopy = when (activeTab) {
                        0 -> "${result?.stdout}\n${result?.stderr}"
                        3 -> result?.astDetails ?: ""
                        else -> result?.stdout ?: ""
                    }
                    if (textToCopy.isNotBlank()) {
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        clipboard.setPrimaryClip(ClipData.newPlainText("PolyCode Terminal", textToCopy))
                        Toast.makeText(context, "Terminal output copied!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.testTag("copy_terminal_button")
            ) {
                Icon(Icons.Default.ContentCopy, contentDescription = "Copy Output", tint = TextSecondary, modifier = Modifier.size(18.dp))
            }

            // Clear Action
            IconButton(
                onClick = onClearTerminal,
                modifier = Modifier.testTag("clear_terminal_button")
            ) {
                Icon(Icons.Default.Clear, contentDescription = "Clear Terminal", tint = TextSecondary, modifier = Modifier.size(18.dp))
            }
        }

        // Status Strip (Exit Code, Time, Memory)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(EditorGutter)
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isRunning) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(12.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Compiling & Executing...", color = TerminalCyan, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                } else if (result != null) {
                    val statusColor = if (result.isSuccess) TerminalGreen else TerminalRed
                    val statusText = if (result.isSuccess) "EXIT: 0 (OK)" else "EXIT: ${result.exitCode} (ERR)"
                    Box(
                        modifier = Modifier
                            .background(statusColor.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                            .border(1.dp, statusColor, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(statusText, color = statusColor, fontSize = 11.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Time: ${result.executionTimeMs}ms", color = TextSecondary, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Mem: ~${result.memoryKb}KB", color = TextSecondary, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                } else {
                    Text("Engine: Ready (${selectedLanguage.executionMode.label})", color = TextMuted, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                }
            }
            Text(selectedLanguage.extension.uppercase(), color = MaterialTheme.colorScheme.primary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }

        // Main Tab Content Panel
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            when (activeTab) {
                0 -> ConsoleTab(result = result, isRunning = isRunning)
                1 -> WebPreviewTab(result = result, selectedLanguage = selectedLanguage)
                2 -> SqlTableTab(result = result)
                3 -> AstMetricsTab(result = result, selectedLanguage = selectedLanguage)
            }
        }
    }
}

@Composable
fun ConsoleTab(result: ExecutionResult?, isRunning: Boolean) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(scrollState)
    ) {
        if (isRunning) {
            Text("[Process running offline inside PolyCode Sandbox...]", color = TerminalYellow, fontFamily = FontFamily.Monospace, fontSize = 12.sp)
        } else if (result == null) {
            Text(
                "Terminal initialized.\nPress ▶️ Run to compile and execute offline.",
                color = TextMuted,
                fontFamily = FontFamily.Monospace,
                fontSize = 12.sp,
                lineHeight = 18.sp
            )
        } else {
            if (result.stdout.isNotEmpty()) {
                Text(
                    text = result.stdout,
                    color = TextPrimary,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                )
            }
            if (result.stderr.isNotEmpty()) {
                if (result.stdout.isNotEmpty()) Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = result.stderr,
                    color = TerminalRed,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Composable
fun WebPreviewTab(result: ExecutionResult?, selectedLanguage: LanguageDefinition) {
    val html = result?.webHtml?.ifEmpty { null }
        ?: if (selectedLanguage.executionMode == ExecutionMode.WEB_VIEW_RENDER) {
            "<html><body style='background:#0d1117;color:#94a3b8;font-family:sans-serif;padding:20px;text-align:center;'><h3>Web Preview Ready</h3><p>Click Run to render HTML/CSS/JS/SVG</p></body></html>"
        } else {
            "<html><body style='background:#0d1117;color:#94a3b8;font-family:sans-serif;padding:20px;text-align:center;'><h3>Not a Web Document</h3><p>Selected language: ${selectedLanguage.name}. Switch to HTML5, CSS3, SVG, Canvas 2D, or Tailwind for live visual preview.</p></body></html>"
        }

    AndroidView(
        factory = { ctx ->
            WebView(ctx).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.cacheMode = WebSettings.LOAD_NO_CACHE
                loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
            }
        },
        update = { webView ->
            webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
        },
        modifier = Modifier
            .fillMaxSize()
            .testTag("web_preview_view")
    )
}

@Composable
fun SqlTableTab(result: ExecutionResult?) {
    val columns = result?.tableColumns ?: emptyList()
    val rows = result?.tableRows ?: emptyList()

    if (columns.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "No SQL table data.\nExecute a SQL query with 'SELECT' to view the grid here.",
                color = TextMuted,
                fontFamily = FontFamily.Monospace,
                fontSize = 12.sp
            )
        }
        return
    }

    val horizontalScroll = rememberScrollState()
    val verticalScroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .horizontalScroll(horizontalScroll)
            .verticalScroll(verticalScroll)
    ) {
        // Table Header
        Row(
            modifier = Modifier
                .background(SurfaceVariantDark)
                .border(1.dp, SurfaceBorderDark)
                .padding(8.dp)
        ) {
            columns.forEach { col ->
                Text(
                    text = col,
                    modifier = Modifier.width(130.dp),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        // Table Rows
        rows.forEachIndexed { idx, row ->
            val rowBg = if (idx % 2 == 0) TerminalBackground else EditorGutter
            Row(
                modifier = Modifier
                    .background(rowBg)
                    .border(0.5.dp, SurfaceBorderDark)
                    .padding(8.dp)
            ) {
                row.forEach { cell ->
                    Text(
                        text = cell,
                        modifier = Modifier.width(130.dp),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp,
                        color = TextPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun AstMetricsTab(result: ExecutionResult?, selectedLanguage: LanguageDefinition) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
            .verticalScroll(scrollState)
    ) {
        Text("Language Specifications", color = MaterialTheme.colorScheme.primary, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text("• Name: ${selectedLanguage.name}", color = TextPrimary, fontSize = 12.sp)
        Text("• Paradigm: ${selectedLanguage.paradigms.joinToString(", ")}", color = TextPrimary, fontSize = 12.sp)
        Text("• Execution Runtime: ${selectedLanguage.executionMode.label}", color = TextPrimary, fontSize = 12.sp)
        Text("• Inception Year: ${selectedLanguage.year}", color = TextPrimary, fontSize = 12.sp)

        Spacer(modifier = Modifier.height(14.dp))
        Text("AST Static Diagnostic Report", color = MaterialTheme.colorScheme.primary, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        val astText = result?.astDetails?.ifEmpty { null }
            ?: "Run the code to compute token counts, cyclomatic complexity, and memory requirements."
        Text(astText, color = TextSecondary, fontFamily = FontFamily.Monospace, fontSize = 12.sp, lineHeight = 18.sp)
    }
}
