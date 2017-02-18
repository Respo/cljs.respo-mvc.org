
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

(defn render-entry [path cursor]
  (div
   {:attrs {:inner-text path},
    :event {:click (fn [e dispatch!] (dispatch! :router/set (str "docs/" path ".html")))},
    :style (merge
            style-entry
            (if (= (str path ".html") cursor) {:color colors/texture-light}))}))

(def props-group
  {:style {:padding "4px 16px",
           :min-width 240,
           :font-family "Monaco, Menlo, monospace",
           :line-height 1.6,
           :font-size 12}})

(def style-sidebar {:padding-top 48})

(defn render-sidebar [path]
  (div
   {:style style-sidebar}
   (div
    props-group
    (render-section "respo.alias")
    (render-entry "div" path)
    (render-entry "create-comp" path)
    (render-entry "create-element" path))
   (div props-group (render-section "respo.comp.text") (render-entry "comp-text" path))
   (div props-group (render-section "respo.comp.code") (render-entry "comp-code" path))
   (div props-group (render-section "respo.comp.space") (render-entry "comp-space" path))
   (div props-group (render-section "respo.comp.debug") (render-entry "comp-debug" path))
   (div
    props-group
    (render-section "respo.core")
    (render-entry "render!" path)
    (render-entry "clear-cache!" path)
    (render-entry "falsify-stage!" path)
    (render-entry "gc-states!" path))
   (div
    props-group
    (render-section "respo.render.html")
    (render-entry "make-string" path)
    (render-entry "make-html" path))
   (div
    props-group
    (render-section "respo.render.expander")
    (render-entry "render-app" path))
   (div
    props-group
    (render-section "respo.util.format")
    (render-entry "purify-element" path)
    (render-entry "mute-element" path))
   (div
    props-group
    (render-section "respo.render.differ")
    (render-entry "find-element-diffs" path))
   (div
    props-group
    (render-section "respo.render.patcher")
    (render-entry "apply-dom-changes" path))
   (div
    props-group
    (render-section "respo.controller.client")
    (render-entry "initialize-instance" path)
    (render-entry "activate-instance" path)
    (render-entry "patch-instance" path)
    (render-entry "release-instance" path))
   (div
    props-group
    (render-section "respo.controller.deliver")
    (render-entry "build-deliver-event" path)
    (render-entry "mutate-factory" path))))

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
