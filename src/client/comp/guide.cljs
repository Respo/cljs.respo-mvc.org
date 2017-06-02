
(ns client.comp.guide
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.alias :refer [create-comp div span a]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.debug :refer [comp-debug]]
            [respo-markdown.comp.md-article :refer [comp-md-article]]))

(def style-link {:cursor :pointer, :text-decoration :none})

(defn render-entry [path title cursor]
  (div
   {}
   (a
    {:attrs {:inner-text title, :href (str "/guide/" path ".html")},
     :style (merge
             style-link
             (if (= cursor (str path ".html")) {:color colors/texture-light})),
     :event {:click (fn [e dispatch!]
               (let [event (:original-event e)] (.preventDefault event))
               (dispatch! :router/set (str "guide/" path ".html")))}})))

(def style-sidebar {:width 240, :padding 16, :padding-top 48})

(defn render-sidebar [entry]
  (div
   {:style style-sidebar}
   (render-entry "why-respo" "Why Respo?" entry)
   (render-entry "pros-and-cons" "Pros and Cons" entry)
   (render-entry "environment" "Environment" entry)
   (render-entry "tutorial" "Tutorial" entry)
   (render-entry "dom-elements" "DOM Elements" entry)
   (render-entry "dom-properties" "DOM Properties" entry)
   (render-entry "dom-events" "DOM Events" entry)
   (render-entry "styles" "Styles" entry)
   (render-entry "virtual-dom" "Virtual DOM" entry)
   (render-entry "render-list" "List Rendering" entry)
   (render-entry "hot-swapping" "Hot Swapping" entry)
   (render-entry "base-components" "Base Components" entry)
   (render-entry "server-rendering" "Server Rendering" entry)
   (render-entry "trouble-shooting" "Trouble Shooting" entry)))

(def guide (js/require "../raw/guide/index"))

(def comp-guide
  (create-comp
   :guide
   (fn [path options]
     (fn [state mutate!]
       (div
        {:style ui/row}
        (render-sidebar path)
        (comp-md-article
         (case path
           "tutorial.html" guide.tutorial
           "dom-elements.html" guide.domElements
           "dom-events.html" guide.domEvents
           "dom-properties.html" guide.domProperties
           "environment.html" guide.environment
           "hot-swapping.html" guide.hotSwapping
           "styles.html" guide.styles
           "trouble-shooting.html" guide.troubleShooting
           "pros-and-cons.html" guide.prosAndCons
           "why-respo.html" guide.whyRespo
           "render-list.html" guide.renderList
           "base-components.html" guide.baseComponents
           "virtual-dom.html" guide.virtualDom
           "server-rendering.html" guide.serverRendering
           nil)
         options))))))
