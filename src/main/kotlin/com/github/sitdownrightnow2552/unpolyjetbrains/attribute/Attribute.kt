package com.github.sitdownrightnow2552.unpolyjetbrains.attribute

import com.intellij.openapi.util.IconLoader

/**
 * Contain information about an Unpoly attribute.
 */
data class Attribute(
    /**
     * Name of the attribute.
     */
    val name: String,
    /**
     * Short description for the attribute.
     */
    val text: String,
    /**
     * Values that this attribute accept, can be simple values, value group, value placeholder or value modifier.
     */
    val values: Set<String> = emptySet(),
    /**
     * Whether this attribute is deprecated (or experimental).
     */
    val deprecated: Boolean = false,
    /**
     * Default value of this attribute.
     */
    val defaultValue: String = "",
    /**
     * The tag that this attribute support. Set to empty to support all tags.
     */
    val tag: String = "",
    /**
     * This attribute modifiers.
     */
    val modifiers: Set<Attribute> = emptySet(),
    /**
     * Whether this attribute is Enumerated, meaning it has a fixed set of value.
     */
    val isEnumerated: Boolean = false,
) {
    val icon
        get() = ICON

    var dependOn: Attribute? = null
        private set

    companion object {
        // Value modifier start with "*".
        const val VALUE_REQUIRED = "*required"
        const val VALUE_ANY = "*any"
        const val VALUE_TOGGLEABLE = "*toggleable"

        // Value placeholder wrapped in "<" and ">".
        // TODO: add suggestion for those group.
        const val VALUE_URI = "<uri>"
        const val VALUE_SELECTOR = "<selector>"
        const val VALUE_NUMBER = "<number>"
        const val VALUE_JSON = "<json>"
        const val VALUE_HTML = "<html>"
        const val VALUE_JS = "<js>"

        // Value group separated by ",".
        const val VALUE_BOOLEAN = "true,false"
        const val VALUE_HTTP_METHOD = "get,post,put,delete"
        const val VALUE_TRANSITION =
            "fade-in,fade-out,move-to-top,move-from-top,move-to-bottom,move-from-bottom,move-to-left,move-from-left,move-to-right,move-from-right,none"
        const val VALUE_WATCH_EVENT = "change,input,$VALUE_ANY"
        const val VALUE_POSITION = "top,bottom,left,right"
        const val VALUE_ALIGN = "top,bottom,left,right,center"
        const val VALUE_LAYER_MATCHING = "current,parent,closest,overlay,ancestor,child,descendant,subtree"

        private val ICON = IconLoader.getIcon("/pluginIcon.svg", Attribute::class.java)

        /**
         * Create an Unpoly attribute using notation syntax.
         *
         * @param notation contains information of tag, name and default value, copied from document, for example: a[[up-layer='new']] (a[up-layer='new'], the former one is for escaping kdoc).
         * @param text the description of attribute.
         * @param values available value of this attribute.
         * @param deprecated whether this attribute is deprecated (or experimental).
         * @param modifiers a set of this attribute modifiers.
         */
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
                // try to set value type if a value was specified in notation but values-set is not specified manually.
                for (group in listOf(VALUE_HTTP_METHOD, VALUE_BOOLEAN)) {
                    if (group.split(",").contains(value)) {
                        supportedValues = setOf(VALUE_REQUIRED, group)
                        break
                    }
                }
            }

            // Since Unpoly 3.8 some attribute can be enabled or disabled using boolean value.
            if (values.contains(VALUE_TOGGLEABLE)) {
                supportedValues = values + setOf(VALUE_BOOLEAN)
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
        fun parseNotation(notation: String): Triple<String, String, String> {
            val match = Regex("(.+)?\\[(.+?)(='(.+?)')?\\]").find(notation)
                ?: throw IllegalArgumentException("No match found for notation $notation")
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
