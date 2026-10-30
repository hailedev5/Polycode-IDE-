package com.example.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai.OfflineAiEngine
import com.example.catalog.LanguageCatalog
import com.example.engine.OfflineCompilerEngine
import com.example.model.AiChatMessage
import com.example.model.ExecutionMode
import com.example.model.ExecutionResult
import com.example.model.LanguageDefinition
import com.example.model.SavedSnippet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PolyCodeViewModel : ViewModel() {

    private val _selectedLanguage = MutableStateFlow(LanguageCatalog.getLanguage("python"))
    val selectedLanguage: StateFlow<LanguageDefinition> = _selectedLanguage.asStateFlow()

    private val _code = MutableStateFlow(_selectedLanguage.value.helloWorldCode)
    val code: StateFlow<String> = _code.asStateFlow()

    private val _stdin = MutableStateFlow("")
    val stdin: StateFlow<String> = _stdin.asStateFlow()

    private val _executionResult = MutableStateFlow<ExecutionResult?>(null)
    val executionResult: StateFlow<ExecutionResult?> = _executionResult.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()

    private val _activeTerminalTab = MutableStateFlow(0)
    val activeTerminalTab: StateFlow<Int> = _activeTerminalTab.asStateFlow()

    private val _isLanguagePickerOpen = MutableStateFlow(false)
    val isLanguagePickerOpen: StateFlow<Boolean> = _isLanguagePickerOpen.asStateFlow()

    private val _isAiSheetOpen = MutableStateFlow(false)
    val isAiSheetOpen: StateFlow<Boolean> = _isAiSheetOpen.asStateFlow()

    private val _isSnippetsOpen = MutableStateFlow(false)
    val isSnippetsOpen: StateFlow<Boolean> = _isSnippetsOpen.asStateFlow()

    private val _isCheatSheetOpen = MutableStateFlow(false)
    val isCheatSheetOpen: StateFlow<Boolean> = _isCheatSheetOpen.asStateFlow()

    private val _isStdinOpen = MutableStateFlow(false)
    val isStdinOpen: StateFlow<Boolean> = _isStdinOpen.asStateFlow()

    private val _savedSnippets = MutableStateFlow(
        listOf(
            SavedSnippet(
                title = "Quicksort Algorithm",
                languageId = "python",
                code = LanguageCatalog.getLanguage("python").algorithmCode
            ),
            SavedSnippet(
                title = "Interactive HTML5 Card",
                languageId = "html5",
                code = LanguageCatalog.getLanguage("html5").helloWorldCode
            ),
            SavedSnippet(
                title = "SQLite Developer Queries",
                languageId = "sql",
                code = LanguageCatalog.getLanguage("sql").helloWorldCode
            )
        )
    )
    val savedSnippets: StateFlow<List<SavedSnippet>> = _savedSnippets.asStateFlow()

    private val _aiMessages = MutableStateFlow(
        listOf(
            AiChatMessage(
                isUser = false,
                text = "👋 Hello! I am your **Offline AI Coding Assistant**.\n" +
                        "I run 100% on this device with zero network needed. I can explain code, detect bugs, analyze Big-O complexity, optimize loops, and transpile across 100+ languages!"
            )
        )
    )
    val aiMessages: StateFlow<List<AiChatMessage>> = _aiMessages.asStateFlow()

    fun selectLanguage(language: LanguageDefinition) {
        _selectedLanguage.value = language
        _code.value = language.helloWorldCode
        _executionResult.value = null
        // Switch tab automatically to best match runtime
        _activeTerminalTab.value = when (language.executionMode) {
            ExecutionMode.WEB_VIEW_RENDER -> 1 // Web preview tab
            ExecutionMode.SQL_NATIVE -> 2 // SQL table tab
            else -> 0 // Console output tab
        }
    }

    fun updateCode(newCode: String) {
        _code.value = newCode
    }

    fun updateStdin(newStdin: String) {
        _stdin.value = newStdin
    }

    fun setActiveTerminalTab(tab: Int) {
        _activeTerminalTab.value = tab
    }

    fun setLanguagePickerOpen(open: Boolean) {
        _isLanguagePickerOpen.value = open
    }

    fun setAiSheetOpen(open: Boolean) {
        _isAiSheetOpen.value = open
    }

    fun setSnippetsOpen(open: Boolean) {
        _isSnippetsOpen.value = open
    }

    fun setCheatSheetOpen(open: Boolean) {
        _isCheatSheetOpen.value = open
    }

    fun setStdinOpen(open: Boolean) {
        _isStdinOpen.value = open
    }

    fun clearTerminal() {
        _executionResult.value = null
    }

    fun runCode(context: Context) {
        if (_isRunning.value) return
        _isRunning.value = true

        viewModelScope.launch {
            val result = OfflineCompilerEngine.execute(
                language = _selectedLanguage.value,
                code = _code.value,
                stdin = _stdin.value,
                context = context
            )
            _executionResult.value = result
            _isRunning.value = false

            // Auto-switch to Web tab or SQL tab if applicable
            if (_selectedLanguage.value.executionMode == ExecutionMode.WEB_VIEW_RENDER) {
                _activeTerminalTab.value = 1
            } else if (_selectedLanguage.value.executionMode == ExecutionMode.SQL_NATIVE && result.tableColumns.isNotEmpty()) {
                _activeTerminalTab.value = 2
            }
        }
    }

    fun sendAiPrompt(prompt: String) {
        val userMsg = AiChatMessage(isUser = true, text = prompt)
        _aiMessages.value = _aiMessages.value + userMsg

        viewModelScope.launch {
            val aiResponse = when {
                prompt.contains("Explain", ignoreCase = true) -> OfflineAiEngine.explainCode(_selectedLanguage.value, _code.value)
                prompt.contains("Detect Bugs", ignoreCase = true) || prompt.contains("Debug", ignoreCase = true) -> OfflineAiEngine.findBugs(_selectedLanguage.value, _code.value)
                prompt.contains("Optimize", ignoreCase = true) -> OfflineAiEngine.optimizeCode(_selectedLanguage.value, _code.value)
                prompt.contains("Transpile to Python", ignoreCase = true) -> OfflineAiEngine.transpile(_selectedLanguage.value, LanguageCatalog.getLanguage("python"), _code.value)
                prompt.contains("Transpile to JavaScript", ignoreCase = true) -> OfflineAiEngine.transpile(_selectedLanguage.value, LanguageCatalog.getLanguage("javascript"), _code.value)
                prompt.contains("Transpile to Rust", ignoreCase = true) -> OfflineAiEngine.transpile(_selectedLanguage.value, LanguageCatalog.getLanguage("rust"), _code.value)
                else -> OfflineAiEngine.answerQuery(prompt, _selectedLanguage.value, _code.value)
            }
            _aiMessages.value = _aiMessages.value + aiResponse
        }
    }

    fun applyAiCodeToEditor(codeSnippet: String) {
        _code.value = codeSnippet
        _isAiSheetOpen.value = false
    }

    fun saveSnippet(title: String) {
        val snippet = SavedSnippet(
            title = title,
            languageId = _selectedLanguage.value.id,
            code = _code.value
        )
        _savedSnippets.value = listOf(snippet) + _savedSnippets.value
    }

    fun deleteSnippet(id: String) {
        _savedSnippets.value = _savedSnippets.value.filter { it.id != id }
    }
}
