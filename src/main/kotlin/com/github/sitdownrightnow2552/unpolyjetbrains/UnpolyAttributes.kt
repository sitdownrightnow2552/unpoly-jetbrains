package com.github.sitdownrightnow2552.unpolyjetbrains

import com.github.sitdownrightnow2552.unpolyjetbrains.Attribute.Companion.VALUE_BOOLEAN
import com.github.sitdownrightnow2552.unpolyjetbrains.Attribute.Companion.VALUE_NUMBER
import com.github.sitdownrightnow2552.unpolyjetbrains.Attribute.Companion.VALUE_REQUIRED
import com.github.sitdownrightnow2552.unpolyjetbrains.Attribute.Companion.VALUE_SELECTOR
import com.github.sitdownrightnow2552.unpolyjetbrains.Attribute.Companion.VALUE_URI
import com.github.sitdownrightnow2552.unpolyjetbrains.Attribute.Companion.of

object UnpolyAttributes {
    private val upFollowModifiers = setOf(
        of("[up-navigate='true']", "Whether this fragment update is considered navigation."),
        of("[up-target]", "The target selector to update."),
        of(
            "[up-fallback='true']",
            "Specifies behavior if the target selector is missing from the current page or the server response.",
            setOf(VALUE_SELECTOR, VALUE_BOOLEAN, VALUE_REQUIRED)
        ),
    )

    val attributes = setOf(
        of(
            notation = "a[up-dash]", deprecated = true,
            text = "Follows this link as fast as possible.",
        ),
        of(
            notation = "a[up-follow]", values = VALUE_SELECTOR,
            text = "Follows this link with JavaScript and updates a fragment with the server response.",
            modifiers = upFollowModifiers,
        ),
        of(
            notation = "a[up-href]", values = setOf(VALUE_URI, VALUE_REQUIRED),
            text = "Follows this link with JavaScript and updates a fragment with the server response.",
            modifiers = upFollowModifiers,
        ),
        of(
            notation = "a[up-instant]",
            text = "Follows this link on mousedown instead of click. This will save precious milliseconds that otherwise spent on waiting for the user to release the mouse button.",
        ),
        of(
            notation = "a[up-preload]",
            text = "Preloads this link when the user hovers over it.",
            modifiers = setOf(
                of(
                    "[up-preload-delay]",
                    "The number of milliseconds to wait between hovering and preloading",
                    VALUE_NUMBER
                )
            )
        ),
        of(
            notation = "[up-expand]", values = VALUE_SELECTOR,
            text = "Follows this link on mousedown instead of click. This will save precious milliseconds that otherwise spent on waiting for the user to release the mouse button.",
        ),
    )
}
