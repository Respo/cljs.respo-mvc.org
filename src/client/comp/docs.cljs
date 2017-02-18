
(ns client.comp.docs
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.alias :refer [create-comp div span a]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.debug :refer [comp-debug]]
            [respo-markdown.comp.md-article :refer [comp-md-article]]
            [client.docs :as docs]))

(def style-section {:color colors/texture-light})

(defn render-section [text] (div {:style style-section} (comp-text text nil)))

(def style-entry {:cursor :pointer, :margin-left 16})

(defn render-entry [path]
  (div
   {:attrs {:inner-text path},
    :event {:click (fn [e dispatch!] (dispatch! :router/set (str "docs/" path ".html")))},
    :style style-entry}))

(def props-group {:style {:padding 8, :min-width 240}})

(defn render-sidebar [path]
  (div
   {}
   (div
    props-group
    (render-section "respo.alias")
    (render-entry "div")
    (render-entry "create-comp")
    (render-entry "create-element"))
   (div props-group (render-section "respo.comp.text") (render-entry "comp-text"))
   (div props-group (render-section "respo.comp.code") (render-entry "comp-code"))
   (div props-group (render-section "respo.comp.space") (render-entry "comp-space"))
   (div props-group (render-section "respo.comp.debug") (render-entry "comp-debug"))
   (div
    props-group
    (render-section "respo.core")
    (render-entry "render!")
    (render-entry "clear-cache!")
    (render-entry "falsify-stage!")
    (render-entry "gc-states!"))
   (div
    props-group
    (render-section "respo.render.html")
    (render-entry "make-string")
    (render-entry "make-html"))
   (div props-group (render-section "respo.render.expander") (render-entry "render-app"))
   (div
    props-group
    (render-section "respo.util.format")
    (render-entry "purify-element")
    (render-entry "mute-element"))
   (div
    props-group
    (render-section "respo.render.differ")
    (render-entry "find-element-diffs"))
   (div
    props-group
    (render-section "respo.render.patcher")
    (render-entry "apply-dom-changes"))
   (div
    props-group
    (render-section "respo.controller.client")
    (render-entry "initialize-instance")
    (render-entry "activate-instance")
    (render-entry "patch-instance")
    (render-entry "release-instance"))
   (div
    props-group
    (render-section "respo.controller.deliver")
    (render-entry "build-deliver-event")
    (render-entry "mutate-factory"))))

(def comp-docs
  (create-comp
   :docs
   (fn [path]
     (fn [state mutate!]
       (div
        {:style ui/row}
        (render-sidebar path)
        (comp-md-article
         (case path
           "activate-instance.html" docs/activate-instance
           "build-deliver-event.html" docs/build-deliver-event
           "comp-debug.html" docs/comp-debug
           "comp-space.html" docs/comp-space
           "comp-text.html" docs/comp-text
           "comp-code.html" docs/comp-code
           "create-comp.html" docs/create-comp
           "create-element.html" docs/create-element
           "element.html" docs/element
           "falsify-stage!.html" docs/falsify-stage_
           "find-element-diffs.html" docs/find-element-diffs
           "initialize-instance.html" docs/initialize-instance
           "make-html.html" docs/make-html
           "make-string.html" docs/make-string
           "mutate-factory.html" docs/mutate-factory
           "mute-element.html" docs/mute-element
           "overview.html" docs/overview
           "patch-instance.html" docs/patch-instance
           "purify-element.html" docs/purify-element
           "release-instance.html" docs/release-instance
           "render-app.html" docs/render-app
           "render!.html" docs/render_
           "gc-states!.html" docs/gc-states_
           "div.html" docs/div
           "clear-cache!.html" docs/clear-cache_
           "apply-dom-changes.html" docs/apply-dom-changes
           nil)
         {}))))))
