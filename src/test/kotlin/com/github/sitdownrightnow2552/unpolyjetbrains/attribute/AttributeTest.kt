package com.github.sitdownrightnow2552.unpolyjetbrains.attribute

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class AttributeTest : BasePlatformTestCase() {
    fun testParseNotationFull() {
        val (tag, name, value) = Attribute.parseNotation("a[up-fallback='true']")
        assertEquals("a", tag)
        assertEquals("up-fallback", name)
        assertEquals("true", value)
    }

    fun testParseNotationWithoutTag() {
        val (tag, name, value) = Attribute.parseNotation("[up-fallback='true']")
        assertEquals("", tag)
        assertEquals("up-fallback", name)
        assertEquals("true", value)
    }

    fun testParseNotationWithoutValue() {
        val (tag, name, value) = Attribute.parseNotation("a[up-fallback]")
        assertEquals("a", tag)
        assertEquals("up-fallback", name)
        assertEquals("", value)
    }

    fun testParseNotationWithoutTagAndValue() {
        val (tag, name, value) = Attribute.parseNotation("[up-fallback]")
        assertEquals("", tag)
        assertEquals("up-fallback", name)
        assertEquals("", value)
    }
}
