
(ns client.comp.discuss
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.alias :refer [create-comp div span a]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.debug :refer [comp-debug]]
            [respo-markdown.comp.md-article :refer [comp-md-article]]
            ["../../../raw/guide/index" :as guide]))

(def style-page {:width 1200, :margin :auto, :margin-top 80})

(def comp-discuss
  (create-comp
   :discuss
   (fn [] (fn [state mutate!] (div {:style style-page} (comp-md-article guide/discuss {}))))))
