package com.github.sitdownrightnow2552.unpolyjetbrains.attribute

import com.github.sitdownrightnow2552.unpolyjetbrains.attribute.Attribute.Companion.VALUE_ALIGN
import com.github.sitdownrightnow2552.unpolyjetbrains.attribute.Attribute.Companion.VALUE_BOOLEAN
import com.github.sitdownrightnow2552.unpolyjetbrains.attribute.Attribute.Companion.VALUE_HTML
import com.github.sitdownrightnow2552.unpolyjetbrains.attribute.Attribute.Companion.VALUE_HTTP_METHOD
import com.github.sitdownrightnow2552.unpolyjetbrains.attribute.Attribute.Companion.VALUE_JS
import com.github.sitdownrightnow2552.unpolyjetbrains.attribute.Attribute.Companion.VALUE_JSON
import com.github.sitdownrightnow2552.unpolyjetbrains.attribute.Attribute.Companion.VALUE_LAYER_MATCHING
import com.github.sitdownrightnow2552.unpolyjetbrains.attribute.Attribute.Companion.VALUE_NUMBER
import com.github.sitdownrightnow2552.unpolyjetbrains.attribute.Attribute.Companion.VALUE_POSITION
import com.github.sitdownrightnow2552.unpolyjetbrains.attribute.Attribute.Companion.VALUE_REQUIRED
import com.github.sitdownrightnow2552.unpolyjetbrains.attribute.Attribute.Companion.VALUE_SELECTOR
import com.github.sitdownrightnow2552.unpolyjetbrains.attribute.Attribute.Companion.VALUE_TRANSITION
import com.github.sitdownrightnow2552.unpolyjetbrains.attribute.Attribute.Companion.VALUE_URI
import com.github.sitdownrightnow2552.unpolyjetbrains.attribute.Attribute.Companion.VALUE_WATCH_EVENT
import com.github.sitdownrightnow2552.unpolyjetbrains.attribute.Attribute.Companion.of

/**
 * Declaration of available attributes
 */
object UnpolyAttributes {
    private val UP_WATCH_MODIFIERS = setOf(
        of("[up-watch-event='change']", "The event types to observe.", VALUE_WATCH_EVENT),
        of(
            "[up-watch-delay]",
            "The number of miliseconds to wait between an observed event and validating.",
            VALUE_NUMBER
        ),
        of("[up-watch-disable]", "Whether to disable fields while validation is running."),
        of("[up-watch-feedback]", "Whether to give navigation feedback while validating.")
    )

    private val ACTION_MODIFIERS = setOf(
        of("[up-confirm]", "A message the user needs to confirm.", VALUE_REQUIRED),
        of("[up-duration]", "The duration of the transition or animation (in millisconds).", VALUE_NUMBER),
        of("[up-easing]", "The timing function that accelerates the transition or animation.", VALUE_REQUIRED),
    )

    private val UP_FOLLOW_MODIFIERS = ACTION_MODIFIERS + setOf(
        of("[up-navigate='true']", "Whether this fragment update is considered navigation."),
        of("[up-target]", "The target selector to update.", VALUE_REQUIRED),
        of(
            "[up-fallback='true']",
            "Specifies behavior if the target selector is missing from the current page or the server response.",
            setOf(VALUE_SELECTOR, VALUE_BOOLEAN, VALUE_REQUIRED)
        ),
        of(
            "[up-match='region']",
            "Controls which fragment to update when the [up-target] selector yields multiple results.",
            VALUE_REQUIRED
        ),
        of(
            "[up-match='region']",
            "Controls which fragment to update when the [up-target] selector yields multiple results.",
            VALUE_REQUIRED
        ),
        of("[up-method='get']", "The HTTP method to use for the request.", setOf(VALUE_REQUIRED, VALUE_HTTP_METHOD)),
        of(
            "[up-params]",
            "A JSON object with additional parameters that should be sent as the request's query string or payload.",
            VALUE_REQUIRED
        ),
        of("[up-headers]", "A JSON object with additional request headers.", VALUE_REQUIRED),
        of("[up-content]", "The new inner HTML for the targeted fragment.", setOf(VALUE_REQUIRED, VALUE_HTML)),
        of(
            "[up-fragment]",
            "A string of HTML comprising ONLY the new fragment's outer HTML.",
            setOf(VALUE_REQUIRED, VALUE_HTML)
        ),
        of("[up-document]", "A string of HTML containing the targeted fragment.", setOf(VALUE_REQUIRED, VALUE_HTML)),
        of("[up-fail]", "Whether the server response should be considered failed."),
        of("[up-history='auto']", "A JSON object with additional request headers.", VALUE_REQUIRED),
        of("[up-title]", "An explicit document title to set before rendering.", VALUE_BOOLEAN),

        of("[up-location]", "An explicit URL to set before rendering.", setOf(VALUE_REQUIRED, VALUE_URI)),
        of("[up-meta-tags]", "Whether to update meta tags in the <head>.", VALUE_BOOLEAN),
        of(
            "[up-transition]",
            "The name of an transition to morph between the old and few fragment.",
            setOf(VALUE_REQUIRED, VALUE_TRANSITION)
        ),
        of(
            "[up-fail-transition]",
            "The transition to use when the server responds with an error code.",
            setOf(VALUE_REQUIRED, VALUE_TRANSITION)
        ),
        of(
            "[up-animation]",
            "The name of an animation to reveal a new fragment when prepending or appending content.",
            VALUE_REQUIRED
        ),
        of(
            "[up-cache='auto']",
            "Whether to read from and write to the cache.",
            setOf(VALUE_REQUIRED, VALUE_BOOLEAN, "auto")
        ),
        of(
            "[up-revalidate='auto']",
            "Whether to reload the targeted fragment after it was rendered from a cached response.",
            setOf(VALUE_REQUIRED, VALUE_BOOLEAN, "auto")
        ),
        of("[up-expire-cache]", "Whether existing cache entries will be expired with this request."),
        of("[up-evict-cache]", "Whether existing cache entries will be evicted with this request."),
        of("[up-abort='target']", "Whether to abort existing requests before rendering."),
        of("[up-background='false']", "Whether this request will load in the background."),
        of(
            "[up-timeout]",
            "The number of milliseconds after which this request fails with a timeout.",
            setOf(VALUE_REQUIRED, VALUE_NUMBER)
        ),
        of("[up-peel='true']", "Whether to close overlays obstructing the updated layer when the fragment is updated."),
        of("[up-scroll='auto']", "How to scroll after the new fragment was rendered."),
        of(
            "[up-scroll-behavior='instant']",
            "Whether to animate the scroll motion when prepending or appending content."
        ),
        of(
            "[up-reveal-snap]",
            "When to snap to the top when scrolling to an element near the top edge of the viewport's scroll buffer."
        ),
        of("[up-reveal-top]", "When to move a revealed element to the top when scrolling to an element."),
        of("[up-reveal-padding]", "How much space to leave to the closest viewport edge when scrolling to an element."),
        of("[up-reveal-max]", "How many pixel lines of high element to reveal when scrolling to an element."),
        of("[up-save-scroll]", "Whether to save scroll positions before updating the fragment."),
        of("[up-focus='auto']", "What to focus after the new fragment was rendered."),
        of("[up-save-focus]", "Whether to save focus-related state before updating the fragment."),
        of(
            "[up-feedback='true']",
            "Whether to give the link an .up-active class and the targeted element an .up-loading class while loading content."
        ),
        of(
            "[up-on-loaded]",
            "A JavaScript snippet that is executed when the server responds with new HTML, but before the HTML is rendered.",
            setOf(VALUE_REQUIRED, VALUE_JS)
        ),
        of(
            "[up-on-rendered]",
            "A JavaScript snippet that is executed when Unpoly has updated fragments.",
            setOf(VALUE_REQUIRED, VALUE_JS)
        ),
        of(
            "[up-on-finished]",
            "A JavaScript snippet that is execvuted when no further DOM changes will be caused by this render pass.",
            setOf(VALUE_REQUIRED, VALUE_JS)
        ),
        of(
            "[up-on-offline]",
            "A JavaScript snippet that is executed when the fragment could not be loaded due to a disconnect or timeout.",
            setOf(VALUE_REQUIRED, VALUE_JS)
        ),
        of(
            "[up-on-error]",
            "A JavaScript snippet that is run when any error is thrown during the rendering process.",
            setOf(VALUE_REQUIRED, VALUE_JS)
        )
    )

    private val UP_SUBMIT_MODIFIERS = UP_FOLLOW_MODIFIERS + setOf(
        of(
            "[up-fail-target]",
            "The target selector to update when the server responds with an error code",
            VALUE_REQUIRED
        ),
        of("[up-disable]", "Whether to disable form controls while the form is submitting.")
    )

    // The list of attributes according to docs.
    private val attributes = setOf(
        // Linking to fragments
        of(
            notation = "a[up-dash]", deprecated = true,
            text = "Follows this link as fast as possible.",
        ),
        of(
            notation = "a[up-follow]", values = VALUE_SELECTOR,
            text = "Follows this link with JavaScript and updates a fragment with the server response.",
            modifiers = UP_FOLLOW_MODIFIERS,
        ),
        of(
            notation = "a[up-href]", values = setOf(VALUE_URI, VALUE_REQUIRED),
            text = "Follows this link with JavaScript and updates a fragment with the server response.",
            modifiers = UP_FOLLOW_MODIFIERS,
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

        // Custom JavaScript
        of(
            notation = "[up-asset]", values = VALUE_BOOLEAN,
            text = "Tracks an element as a frontend asset, usually JavaScripts and stylesheets.",
        ),
        of(
            notation = "[up-data]", values = VALUE_JSON,
            text = "Attaches structured data to an element, to be consumed by a compiler or event handler.",
        ),

        // Forms
        of(
            notation = "form[up-submit]",
            text = "Submits this form via JavaScript and updates a fragment with the server response.",
            modifiers = UP_SUBMIT_MODIFIERS
        ),
        of(
            notation = "[up-autosubmit]",
            text = "Automatically submits a form when a field changes.",
            modifiers = setOf(
                of("[up-watch-event='input']", "The type of event to watch.", setOf(VALUE_REQUIRED)),
                of("[up-disable]", "Whether to disable form controls while the form is submitting.")
            )
        ),
        of(
            notation = "[up-fieldset]", deprecated = true,
            text = "Marks this element as a from group, which (usually) contains a label, input and error message."
        ),
        of(
            notation = "[up-form-group]",
            text = "Marks this element as a from group, which (usually) contains a label, input and error message."
        ),
        of(
            notation = "[up-hide-for]", values = VALUE_REQUIRED,
            text = "Hides this element if an input field with [up-switch] has one of the given values."
        ),
        of(
            notation = "[up-show-for]", values = VALUE_REQUIRED,
            text = "Only shows this element if an input field with [up-switch] has one of the given values."
        ),
        of(
            notation = "[up-switch]", values = VALUE_SELECTOR,
            text = "Show or hide elements when a form field is set to a given value."
        ),
        of(
            notation = "[up-validate]", values = VALUE_SELECTOR,
            text = "Renders a new form state when a field changes, to show validation errors or update dependent fields.",
            modifiers = UP_WATCH_MODIFIERS
        ),
        of(
            notation = "[up-watch]", values = VALUE_REQUIRED,
            text = "Watches form fields and runs a callback when a value changes.",
            modifiers = UP_WATCH_MODIFIERS
        ),

        // Layers
        of(
            notation = "a[up-accept]",
            text = "Accepts the current layer when the link is clicked.",
            modifiers = ACTION_MODIFIERS
        ),
        of(
            notation = "a[up-dismiss]",
            text = "Dismisses the current layer when the link is clicked.",
            modifiers = ACTION_MODIFIERS + setOf(
                of(
                    "[up-animation]",
                    "The name of the overlay's close animation.",
                    VALUE_REQUIRED
                ),
            )
        ),
        of(
            notation = "a[up-layer='new']",
            text = "Follows this link and opens the result in a new overlay.",
            values = setOf(VALUE_REQUIRED, "new", "swap", "shatter"),
            modifiers = UP_FOLLOW_MODIFIERS + setOf(
                of(
                    "[up-mode]",
                    "Whether to stack the new overlay onto the current layer or replace existing overlays",
                    setOf("root", "modal", "drawer", "popup", "cover")
                ),
                of(
                    "[up-mode]",
                    "Whether to stack the new overlay onto the current layer or replace existing overlays",
                    setOf("root", "modal", "drawer", "popup", "cover")
                ),
                of("[up-size]", "The size of the overlay.", setOf("small", "medium", "large", "grow", "full")),
                of("[up-class]", "An optional HTML class for the overlay's container element."),
                of(
                    "[up-history]",
                    "Whether history of the overlay content is visible.",
                    setOf(VALUE_BOOLEAN, "auto")
                ),
                of(
                    "[up-dismissable]", "Whether history of the overlay content is visible.", VALUE_BOOLEAN
                ),
                of("[up-animation]", "The name of the opening animation.", setOf(VALUE_REQUIRED, VALUE_TRANSITION)),
                of(
                    "[up-on-opened]",
                    "A JavaScript snippet that is called when the overlay was inserted into the DOM.",
                    setOf(VALUE_REQUIRED, VALUE_JS)
                ),
                of(
                    "[up-on-accepted]",
                    "A JavaScript snippet that is called when the overlay was accepted.",
                    setOf(VALUE_REQUIRED, VALUE_JS)
                ),
                of(
                    "[up-on-dismissed]",
                    "A JavaScript snippet that is called when the overlay was dismissed.",
                    setOf(VALUE_REQUIRED, VALUE_JS)
                ),
                of(
                    "[up-accept-event]",
                    "One or more space-separated event types that will cause this overlay to automatically be accepted when a matching event occurs within the overlay.",
                    VALUE_REQUIRED
                ),
                of(
                    "[up-dismiss-event]",
                    "One or more space-separated event types that will cause this overlay to automatically be dismissed when a matching event occurs within the overlay.",
                    VALUE_REQUIRED
                ),
                of(
                    "[up-accept-location]",
                    "One or more space-separated URL patterns that will cause this overlay to automatically be accepted when the overlay reaches a matching location.",
                    setOf(VALUE_REQUIRED, VALUE_URI)
                ),
                of(
                    "[up-dismiss-location]",
                    "One or more space-separated URL patterns that will cause this overlay to automatically be dismissed when the overlay reaches a matching location.",
                    setOf(VALUE_REQUIRED, VALUE_URI)
                ),
                of(
                    "[up-position]",
                    "The position of the popup relative to the { origin } element that opened the overlay.",
                    VALUE_POSITION
                ),
                of(
                    "[up-align]",
                    "The alignment of the popup within its { position }.",
                    VALUE_ALIGN
                ),

                )
        ),
        // Fragment API
        of(
            notation = "[up-id]", values = VALUE_REQUIRED,
            text = "Sets an unique identifier for this element"
        ),
        of(
            notation = "[up-keep]",
            text = "Elements with an [up-keep] attribute will be persisted during fragment updates.",
            modifiers = setOf(
                of(
                    "[up-on-keep]",
                    "Code to run before an existing element is kept during a page update.",
                    setOf(VALUE_REQUIRED, VALUE_JS)
                )
            )
        ),
        of(
            notation = "[up-source]",
            text = "Sets this element's source URL for reloading and polling",
            setOf(VALUE_REQUIRED, VALUE_URI)
        ),
        of(
            notation = "[up-main]",
            text = "Marks this element as the primary content element of your application layout.",
        ),

        // Passive updates
        of(
            notation = "[up-hungry]",
            text = "Elements with an [up-hungry] attribute are updated whenever the server sends a matching element, even if the element isn't targeted.",
            modifiers = setOf(
                of(
                    "[up-transition]",
                    "The animated transition to apply when this element is updated.",
                    setOf(VALUE_REQUIRED, VALUE_TRANSITION)
                ),
                of("[up-duration]", "The duration of the transition or animation (in millisconds).", VALUE_NUMBER),
                of("[up-easing]", "The timing function that accelerates the transition or animation.", VALUE_REQUIRED),
                of(
                    "[up-if-layer='current']",
                    "Only piggy-back on updates on layers that match the given layer reference",
                    setOf(VALUE_REQUIRED, VALUE_LAYER_MATCHING)
                ),
                of(
                    "[up-on-hungry]",
                    "Code to run before this element is included in a fragment update.",
                    setOf(VALUE_REQUIRED, VALUE_JS)
                ),
            )
        ),
        of(
            notation = "[up-poll]",
            text = "Elements with an [up-poll] attribute are reloaded from the server periodically.",
            modifiers = setOf(
                of("[up-interval]", "The reload interval in milliseconds.", setOf(VALUE_REQUIRED, VALUE_NUMBER)),
                of("[up-source]", "The URL from which to reload the fragment.", setOf(VALUE_REQUIRED, VALUE_URI))
            )
        ),
        of(
            notation = "[up-flashes]", deprecated = true,
            text = "Experimental. Use an [up-flashes] element to show confirmations, alerts or warnings.",
        ),

        // Animation
        of(
            notation = "a[up-transition]", values = setOf(VALUE_REQUIRED, VALUE_TRANSITION),
            text = "Follows this link and swaps in the new fragment with an animated transition.",
            modifiers = UP_FOLLOW_MODIFIERS
        ),
        of(
            notation = "form[up-transition]", values = setOf(VALUE_REQUIRED, VALUE_TRANSITION),
            text = "Submits this form and swaps in the new fragment with an animated transition.",
            modifiers = UP_SUBMIT_MODIFIERS
        ),

        // Navigation feedback
        of(
            notation = "[up-nav]",
            text = "Marks this element as a navigation component, such as a menu or navigation bar."
        ),
        of(
            notation = "a[up-alias]", values = setOf(VALUE_REQUIRED, VALUE_URI),
            text = "Links within [up-nav] may use the [up-alias] attribute to pass a URL pattern for which they should also be highlighted as .up-current."
        ),

        // Events
        of(
            notation = "a[up-emit]", values = VALUE_REQUIRED,
            text = "Emits the given event when this link is clicked.",
            modifiers = setOf(
                of("[up-emit-props='{}']", "The event properties, serialized as JSON.", setOf(VALUE_REQUIRED, VALUE_JSON))
            )
        ),

        // Scrolling and focus
        of(
            notation = "[up-anchored='right']",
            text = "Marks this element as being anchored to the right edge of the screen, typically fixed navigation bars."
        ),
        of(
            notation = "[up-fixed='bottom']", values = setOf(VALUE_REQUIRED, "top", "bottom"),
            text = "Marks this element as being fixed to the top/bottom edge of the screen using position: fixed."
        ),
        of(
            notation = "[up-viewport]",
            text = "Marks this element as a scrolling container (\"viewport\"). Apply this attribute if your app uses a custom panel layout with fixed positioning instead of scrolling the <body> element."
        ),

        // History
        of(
            notation = "a[up-back]",
            text = "Changes the link's destination so it points to the previous URL."
        )
    )

    val attributesByTags = attributes.groupBy { it.tag }.mapValues { it.value.toSet() }
    val allAttributesByNames = attributes.flatMap { listOf(it, *it.modifiers.toTypedArray()) }.associateBy { it.name }
}
