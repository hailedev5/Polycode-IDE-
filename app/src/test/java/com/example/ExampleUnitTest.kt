package com.example

import com.example.ai.OfflineAiEngine
import com.example.catalog.LanguageCatalog
import com.example.model.ExecutionMode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun testLanguageCatalogHasOver100Languages() {
        val total = LanguageCatalog.allLanguages.size
        assertTrue("Expected 100+ languages, found $total", total >= 100)
    }

    @Test
    fun testOfflineAiBugDetection() {
        val py = LanguageCatalog.getLanguage("python")
        val buggyCode = "def test():\n    while (true):\n        pass"
        val analysis = OfflineAiEngine.findBugs(py, buggyCode)
        assertTrue(analysis.text.contains("Infinite Loop") || analysis.text.contains("Detected"))
    }

    @Test
    fun testOfflineAiExplanation() {
        val rust = LanguageCatalog.getLanguage("rust")
        val code = "fn main() { for i in 1..10 { println!(\"{}\", i); } }"
        val explanation = OfflineAiEngine.explainCode(rust, code)
        assertTrue(explanation.text.contains("Rust"))
        assertTrue(explanation.text.contains("Time Complexity"))
    }
}
