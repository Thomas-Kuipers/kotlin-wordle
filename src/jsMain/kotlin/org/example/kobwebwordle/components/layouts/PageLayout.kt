package org.example.kobwebwordle.components.layouts

import androidx.compose.runtime.*
import kotlinx.browser.document
import org.jetbrains.compose.web.dom.*

@Composable
fun PageLayout(title: String, content: @Composable () -> Unit) {
    LaunchedEffect(title) {
        document.title = title
    }

    Main(attrs = {
        classes("main")
    }) {
        content()
    }
}