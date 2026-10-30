package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LanguageDefinition
import com.example.ui.theme.EditorBackground
import com.example.ui.theme.EditorGutter
import com.example.ui.theme.EditorGutterText
import com.example.ui.theme.SurfaceBorderDark
import com.example.ui.theme.SurfaceVariantDark
import com.example.ui.theme.TextPrimary

@Composable
fun CodeEditorView(
    code: String,
    onCodeChange: (String) -> Unit,
    language: LanguageDefinition,
    modifier: Modifier = Modifier
) {
    val verticalScrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()

    val lines = code.lines()
    val lineCount = maxOf(lines.size, 1)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(EditorBackground)
    ) {
        // Main Editor Area with Gutter
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(verticalScrollState)
            ) {
                // Line Number Gutter
                Column(
                    modifier = Modifier
                        .background(EditorGutter)
                        .padding(horizontal = 8.dp, vertical = 12.dp)
                        .widthIn(min = 36.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    for (i in 1..lineCount) {
                        Text(
                            text = i.toString(),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 13.sp,
                            color = EditorGutterText,
                            lineHeight = 20.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Editable Code Field with horizontal scroll
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 12.dp)
                        .horizontalScroll(horizontalScrollState)
                ) {
                    BasicTextField(
                        value = code,
                        onValueChange = onCodeChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("code_input_field"),
                        textStyle = TextStyle(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 13.sp,
                            lineHeight = 20.sp,
                            color = TextPrimary
                        ),
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                        decorationBox = { innerTextField ->
                            if (code.isEmpty()) {
                                Text(
                                    text = "// Write or paste ${language.name} code here...",
                                    color = EditorGutterText,
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 13.sp
                                )
                            }
                            innerTextField()
                        }
                    )
                }
            }
        }

        // Quick Symbol Accessory Bar for mobile coding convenience
        QuickSymbolBar(
            onInsertSymbol = { sym ->
                onCodeChange(code + sym)
            },
            onIndent = {
                onCodeChange(code + "    ")
            }
        )
    }
}

@Composable
fun QuickSymbolBar(
    onInsertSymbol: (String) -> Unit,
    onIndent: () -> Unit,
    modifier: Modifier = Modifier
) {
    val symbols = listOf(
        "TAB", "(", ")", "{", "}", "[", "]", "<", ">",
        "\"", "'", ";", ":", "=", "+", "-", "*", "/",
        "->", "=>", "$", "#", "!", "&", "|", "?", "%"
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp),
        color = SurfaceVariantDark,
        tonalElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 6.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            symbols.forEach { sym ->
                ElevatedButton(
                    onClick = {
                        if (sym == "TAB") onIndent() else onInsertSymbol(sym)
                    },
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .height(34.dp)
                        .testTag("quick_sym_$sym"),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = EditorGutter,
                        contentColor = TextPrimary
                    ),
                    contentPadding = ButtonDefaults.ContentPadding
                ) {
                    Text(
                        text = sym,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
