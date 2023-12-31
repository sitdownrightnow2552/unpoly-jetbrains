package com.github.sitdownrightnow2552.unpolyjetbrains.attribute

import com.intellij.openapi.util.IconLoader

data class Attribute(
    val name: String,
    val text: String,
    val values: Set<String> = emptySet(),
    val deprecated: Boolean = false,
    val defaultValue: String = "",
    val tag: String = "",
    val modifiers: Set<Attribute> = emptySet(),
    val isEnumerated: Boolean = false,
) {
    val icon
        get() = ICON

    var dependOn: Attribute? = null
        private set

    companion object {
        // Value modifier start with "*"
        const val VALUE_REQUIRED = "*required"
        const val VALUE_ANY = "*any"

        // Value placeholder wrapped in "<" and ">"
        const val VALUE_URI = "<uri>"
        const val VALUE_SELECTOR = "<selector>"
        const val VALUE_NUMBER = "<number>"
        const val VALUE_JSON = "<json>"
        const val VALUE_HTML = "<html>"
        const val VALUE_JS = "<js>"

        // Value group separated by ","
        const val VALUE_BOOLEAN = "true,false"
        const val VALUE_HTTP_METHOD = "get,post,put,delete"
        const val VALUE_TRANSITION =
            "fade-in,fade-out,move-to-top,move-from-top,move-to-bottom,move-from-bottom,move-to-left,move-from-left,move-to-right,move-from-right,none"
        const val VALUE_WATCH_EVENT = "change,input,$VALUE_ANY"
        const val VALUE_POSITION = "top,bottom,left,right"
        const val VALUE_ALIGN = "top,bottom,left,right,center"
        const val VALUE_LAYER_MATCHING = "current,parent,closest,overlay,ancestor,child,descendant,subtree"

        private val ICON = IconLoader.getIcon("/pluginIcon.svg", Attribute::class.java)

        @JvmStatic
        fun of(
            notation: String,
            text: String = "",
            values: Set<String> = emptySet(),
            deprecated: Boolean = false,
            modifiers: Set<Attribute> = emptySet()
        ): Attribute {
            val (tag, name, value) = parseNotation(notation)

            var supportedValues = values
            if (values.isEmpty()) {
                // try to set value type if a value was specified in notation but values Set is not specified manually
                for (group in listOf(VALUE_HTTP_METHOD, VALUE_BOOLEAN)) {
                    if (group.split(",").contains(value)) {
                        supportedValues = setOf(VALUE_REQUIRED, group)
                        break
                    }
                }
            }

            supportedValues = supportedValues
                .filter { !isModifier(it) }
                .joinToString(",")
                .split(",")
                .filter { it.isNotBlank() }
                .toSet()

            val isNotContainAnyTypePlaceHolder = supportedValues.find { isPlaceHolder(it) && it != VALUE_ANY } == null

            val attribute = Attribute(
                name = name,
                text = text,
                values = supportedValues,
                deprecated = deprecated,
                tag = tag,
                defaultValue = value,
                modifiers = modifiers,
                isEnumerated = isNotContainAnyTypePlaceHolder,
            )
            modifiers.forEach { it.dependOn = attribute }
            return attribute
        }

        @JvmStatic
        fun of(
            notation: String,
            text: String,
            values: String,
            deprecated: Boolean = false,
            modifiers: Set<Attribute> = emptySet()
        ): Attribute {
            return of(
                notation = notation,
                text = text,
                values = values.split(",").filter { it.isNotBlank() }.toSet(),
                deprecated = deprecated,
                modifiers = modifiers
            )
        }

        @JvmStatic
        ///spring-libman-app
        fun parseNotation(notation: String): Triple<String, String, String> {
            val match = Regex("(.+)?\\[(.+?)(='(.+?)')?\\]").find(notation) ?: throw IllegalArgumentException("No match found for notation $notation")
            val (_, tag, name, _, value) = match.groupValues
            return Triple(tag, name, value)
        }

        private fun isModifier(s: String): Boolean {
            return s.startsWith("*")
        }

        private fun isPlaceHolder(s: String): Boolean {
            return s.startsWith("<") && s.endsWith(">")
        }
    }
}
