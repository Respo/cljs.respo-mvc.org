
(ns client.comp.header
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.alias :refer [create-comp div span a]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.debug :refer [comp-debug]]))

(def style-header
  (merge
   ui/row
   {:justify-content :space-between,
    :align-items :center,
    :padding "0 32px",
    :border-bottom (str "1px solid " (hsl 0 0 94)),
    :line-height "40px",
    :font-family "Josefin Sans"}))

(def style-link {:cursor :pointer, :text-decoration :none, :font-size 16})

(def style-section {:display :inline-block, :margin-right 64})

(defn render-link [text path]
  (div
   {:style style-section,
    :event {:click (fn [e dispatch!]
              (let [event (:original-event e)] (.preventDefault event))
              (dispatch! :router/set path))}}
   (a {:attrs {:inner-text text, :href (str "/" path)}, :style style-link})))

(def style-github {:text-decoration :none})

(def comp-header
  (create-comp
   :header
   (fn []
     (fn [state mutate!]
       (div
        {:style style-header}
        (div
         {}
         (render-link "Respo" "")
         (render-link "Guide" "guide/why-respo.html")
         (render-link "API Docs" "docs/overview.html")
         (render-link "Discuss" "discuss.html"))
        (div
         {}
         (a
          {:attrs {:href "https://github.com/Respo",
                   :inner-text "GitHub",
                   :target "_blanck"},
           :style style-github})))))))
