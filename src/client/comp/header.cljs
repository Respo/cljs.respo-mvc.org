
(ns client.comp.header
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.alias :refer [create-comp div span a]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.debug :refer [comp-debug]]))

(def style-header (merge ui/row {:justify-content :space-between, :padding "0 16px"}))

(def style-link {:display :inline-block, :width 80, :cursor :pointer})

(defn render-link [text path]
  (div
   {:style style-link,
    :event {:click (fn [e dispatch!] (dispatch! :router/set path))},
    :attrs {:inner-text text}}))

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
          {:attrs {:href "https://github.com/Respo", :inner-text "GitHub"},
           :style style-github})))))))
