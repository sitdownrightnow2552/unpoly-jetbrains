package com.github.sitdownrightnow2552.unpolyjetbrains

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.patterns.XmlPatterns
import com.intellij.psi.xml.XmlTokenType
import com.intellij.util.ProcessingContext


class UnpolyCompletionContributor : CompletionContributor() {
    init {
        extend(CompletionType.BASIC,
            PlatformPatterns.psiElement(XmlTokenType.XML_NAME).withParent(XmlPatterns.xmlAttribute()),
            object : CompletionProvider<CompletionParameters?>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    val el = LookupElementBuilder.create("up-test")
                        .withCaseSensitivity(false)
                        .withTypeText("testing stuff")
                        .withInsertHandler(XmlTagInsertHandler.INSTANCE)

                    result.addElement(el)
                }
            }
        )
    }
}
