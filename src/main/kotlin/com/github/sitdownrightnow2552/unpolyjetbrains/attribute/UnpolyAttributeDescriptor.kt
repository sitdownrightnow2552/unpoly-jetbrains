package com.github.sitdownrightnow2552.unpolyjetbrains.attribute

import com.intellij.psi.PsiElement
import com.intellij.psi.meta.PsiPresentableMetaData
import com.intellij.psi.xml.XmlTag
import com.intellij.xml.impl.BasicXmlAttributeDescriptor
import javax.swing.Icon

class UnpolyAttributeDescriptor(
    private val attribute: Attribute,
    private val xmlTag: XmlTag
) :
    BasicXmlAttributeDescriptor(),
    PsiPresentableMetaData {
    override fun isFixed(): Boolean {
        return false
    }

    override fun getDefaultValue(): String? {
        return if (attribute.defaultValue == "") {
            null
        } else if (!attribute.values.contains(Attribute.VALUE_REQUIRED)) {
            null
        } else {
            attribute.defaultValue
        }
    }

    override fun isEnumerated(): Boolean {
        return attribute.isEnumerated
    }

    override fun getEnumeratedValues(): Array<String> {
        return attribute.values.filter { !it.startsWith("*") }.toTypedArray()
    }

    override fun getDeclaration(): PsiElement {
        return xmlTag
    }

    override fun getName(): String {
        return attribute.name
    }

    override fun init(element: PsiElement?) {
    }

    override fun getTypeName(): String {
        return attribute.text
    }

    override fun getIcon(): Icon {
        return attribute.icon
    }

    override fun isRequired(): Boolean {
        return attribute.values.contains(Attribute.VALUE_REQUIRED)
    }

    override fun hasIdType(): Boolean {
        return false
    }

    override fun hasIdRefType(): Boolean {
        return false
    }
}
