
(ns client.comp.docs
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.alias :refer [create-comp div span a]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.debug :refer [comp-debug]]
            [respo-markdown.comp.md-article :refer [comp-md-article]]))

(def style-entry {:cursor :pointer, :margin-left 16, :text-decoration :none})

(defn render-entry [path cursor]
  (div
   {}
   (a
    {:attrs {:inner-text path, :href (str "/docs/" path ".html")},
     :event {:click (fn [e dispatch!]
               (let [event (:original-event e)] (.preventDefault event))
               (dispatch! :router/set (str "docs/" path ".html")))},
     :style (merge
             style-entry
             (if (= (str path ".html") cursor) {:color colors/texture-light}))})))

(def props-group
  {:style {:padding "4px 16px",
           :min-width 240,
           :font-family "Monaco, Menlo, monospace",
           :line-height 1.6,
           :font-size 12}})

(def style-section {:color colors/texture-light})

(def style-sidebar {:padding-top 48})

(defn render-section [text] (div {:style style-section} (comp-text text nil)))

(defn render-sidebar [path]
  (div
   {:style style-sidebar}
   (render-entry "overview" path)
   (div
    props-group
    (render-section "respo.alias")
    (render-entry "div" path)
    (render-entry "create-comp" path)
    (render-entry "create-element" path))
   (div
    props-group
    (render-section "respo.comp.text")
    (render-entry "comp-text" path)
    (render-entry "comp-code" path))
   (div props-group (render-section "respo.comp.space") (render-entry "comp-space" path))
   (div props-group (render-section "respo.comp.debug") (render-entry "comp-debug" path))
   (div
    props-group
    (render-section "respo.core")
    (render-entry "render!" path)
    (render-entry "clear-cache!" path)
    (render-entry "falsify-stage!" path))
   (div
    props-group
    (render-section "respo.cursor")
    (render-entry "with-cursor" path)
    (render-entry "mutate" path))
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
    (render-entry "activate-instance" path)
    (render-entry "patch-instance" path)
    (render-entry "release-instance" path))
   (div
    props-group
    (render-section "respo.controller.deliver")
    (render-entry "build-deliver-event" path)
    (render-entry "mutate-factory" path))))

(def docs (js/require "../raw/docs/index"))

(def comp-docs
  (create-comp
   :docs
   (fn [path options]
     (fn [state mutate!]
       (div
        {:style ui/row}
        (render-sidebar path)
        (comp-md-article
         (case path
           "activate-instance.html" docs.activateInstance
           "build-deliver-event.html" docs.buildDeliverEvent
           "comp-debug.html" docs.compDebug
           "comp-space.html" docs.compSpace
           "comp-text.html" docs.compText
           "comp-code.html" docs.compCode
           "create-comp.html" docs.createComp
           "create-element.html" docs.createElement
           "falsify-stage!.html" docs.falsifyStage_
           "find-element-diffs.html" docs.findElementDiffs
           "make-html.html" docs.makeHtml
           "make-string.html" docs.makeString
           "mutate-factory.html" docs.mutateFactory
           "mute-element.html" docs.muteElement
           "overview.html" docs.overview
           "patch-instance.html" docs.patchInstance
           "purify-element.html" docs.purifyElement
           "release-instance.html" docs.releaseInstance
           "render-app.html" docs.renderApp
           "render!.html" docs.render_
           "div.html" docs.div
           "clear-cache!.html" docs.clearCache_
           "apply-dom-changes.html" docs.applyDomChanges
           "mutate.html" docs.mutate
           "with-cursor.html" docs.withCursor
           nil)
         options))))))
