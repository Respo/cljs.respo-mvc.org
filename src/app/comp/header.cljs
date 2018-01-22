
(ns app.comp.header
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo-ui.colors :as colors]
            [respo.macros :refer [defcomp div span a]]
            [respo.comp.space :refer [=<]]))

(def style-link {:cursor :pointer, :text-decoration :none, :font-size 16})

(def style-section {:display :inline-block, :margin-right 64})

(defn render-link [text path]
  (div {:style style-section} (a {:inner-text text, :href (str path), :style style-link})))

(def style-github {:text-decoration :none})

(def style-header
  (merge
   ui/row
   {:justify-content :space-between,
    :align-items :center,
    :padding "0 32px",
    :border-bottom (str "1px solid " (hsl 0 0 94)),
    :line-height "40px",
    :font-family "Josefin Sans"}))

(defcomp
 comp-header
 ()
 (div
  {:style style-header}
  (div
   {}
   (render-link "Respo" "/")
   (render-link "Guide" "https://github.com/Respo/respo/wiki")
   (render-link "API Docs" "https://github.com/Respo/respo/wiki/API")
   (render-link "Community" "https://github.com/Respo/respo/wiki/Community"))
  (div
   {}
   (a
    {:href "https://github.com/Respo",
     :inner-text "GitHub",
     :target "_blanck",
     :style style-github}))))
