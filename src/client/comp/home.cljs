
(ns client.comp.home
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.alias :refer [create-comp div span a img]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.debug :refer [comp-debug]]))

(def style-logo
  {:width 80,
   :height 80,
   :background-image (str
                      "url(https://avatars3.githubusercontent.com/u/20469468?v=3&s=200)"),
   :background-size :cover,
   :display :inline-block,
   :vertical-align :middle})

(def style-logo-name {:font-size 40, :vertical-align :middle})

(def comp-home
  (create-comp
   :home
   (fn []
     (fn [state mutate!]
       (div
        {:style (merge ui/column ui/center)}
        (comp-space nil 48)
        (div
         {}
         (div {:style style-logo})
         (comp-space 8 nil)
         (comp-text "Respo" style-logo-name))
        (comp-space nil 24)
        (div
         {}
         (comp-text " A front-end MVC library in ClojureScript" nil)
         (comp-space 8 nil)
         (img {:attrs {:src "https://img.shields.io/clojars/v/respo.svg"}})))))))
