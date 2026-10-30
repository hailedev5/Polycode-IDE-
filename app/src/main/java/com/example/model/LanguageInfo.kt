package com.example.model

enum class LanguageCategory(val displayName: String, val icon: String) {
    POPULAR("Popular", "⭐"),
    WEB("Web & Frontend", "🌐"),
    SYSTEMS("Systems & Hardware", "⚙️"),
    MOBILE("Mobile & Modern", "📱"),
    DATA_AI("Data & AI", "📊"),
    SCRIPTING("Scripting & Shell", "📜"),
    FUNCTIONAL("Functional & Logic", "λ"),
    ESOTERIC("Esoteric & Fun", "🎮"),
    CONFIG_MARKUP("Markup & Config", "📑")
}

enum class ExecutionMode(val label: String) {
    JAVASCRIPT_NATIVE("V8 JavaScript Engine"),
    WEB_VIEW_RENDER("Live Web Sandbox"),
    SQL_NATIVE("Native SQLite Engine"),
    BRAINFUCK_NATIVE("Brainfuck VM"),
    LISP_NATIVE("Lisp/Scheme Evaluator"),
    REGEX_NATIVE("Regex Matcher Engine"),
    AST_SIMULATOR("Universal Offline AST & Simulator")
}

data class LanguageDefinition(
    val id: String,
    val name: String,
    val extension: String,
    val category: LanguageCategory,
    val executionMode: ExecutionMode,
    val year: Int,
    val description: String,
    val helloWorldCode: String,
    val algorithmCode: String,
    val keywords: List<String>,
    val cheatSheet: String,
    val paradigms: List<String>
)

data class ExecutionResult(
    val stdout: String = "",
    val stderr: String = "",
    val executionTimeMs: Long = 0,
    val memoryKb: Long = 0,
    val exitCode: Int = 0,
    val isSuccess: Boolean = true,
    val mode: ExecutionMode = ExecutionMode.AST_SIMULATOR,
    val tableColumns: List<String> = emptyList(),
    val tableRows: List<List<String>> = emptyList(),
    val webHtml: String = "",
    val astDetails: String = ""
)

data class AiChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val isUser: Boolean,
    val text: String,
    val codeSnippet: String? = null,
    val codeLanguage: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

data class SavedSnippet(
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val languageId: String,
    val code: String,
    val updatedAt: Long = System.currentTimeMillis()
)
