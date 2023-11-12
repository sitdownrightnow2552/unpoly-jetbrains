package com.github.sitdownrightnow2552.unpolyjetbrains.attribute

import com.intellij.psi.impl.source.html.dtd.HtmlElementDescriptorImpl
import com.intellij.psi.xml.XmlTag
import com.intellij.xml.XmlAttributeDescriptor
import com.intellij.xml.XmlAttributeDescriptorsProvider

class UnpolyAttributeDescriptorsProvider : XmlAttributeDescriptorsProvider {
    override fun getAttributeDescriptors(context: XmlTag): Array<XmlAttributeDescriptor> {
        if (context.descriptor !is HtmlElementDescriptorImpl) return emptyArray()

        val attributes = UnpolyAttributes.attributesByTags.getOrDefault("", emptySet()) +
            UnpolyAttributes.attributesByTags.getOrDefault(context.name, emptySet())

        return attributes
            .flatMap { if (containsAttribute(context, it.name)) it.modifiers else setOf(it) }
            .map { UnpolyAttributeDescriptor(it, context) }
            .toTypedArray()
    }

    override fun getAttributeDescriptor(attributeName: String, context: XmlTag): XmlAttributeDescriptor? {
        if (context.descriptor !is HtmlElementDescriptorImpl) return null

        val attribute = UnpolyAttributes.allAttributesByNames[attributeName] ?: return null
        if (attribute.tag != "" && attribute.tag != context.name) {
            return null
        }
        if (attribute.dependOn != null && !containsAttribute(context, attribute.dependOn!!.name)) {
            return null
        }
        return UnpolyAttributeDescriptor(attribute, context)
    }

    private fun containsAttribute(context: XmlTag, attributeName: String?): Boolean {
        return context.attributes.find { it.name == attributeName } != null
    }
}
