
(ns app.comp.home
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo-ui.colors :as colors]
            [respo.macros :refer [defcomp <> span div button a img pre code]]
            [respo.comp.space :refer [=<]]
            [respo-md.comp.md :refer [comp-md-block]]
            ["highlight.js" :as hljs]
            [build.util :refer [inline-resource]]))

(def style-description
  {:font-size 16, :font-weight 400, :color colors/texture, :font-family "Hind"})

(def style-footer {:min-height 200, :padding 32})

(def style-header {:padding-top 64, :padding-bottom 0, :background-color :white})

(def style-logo
  {:width 160,
   :height 160,
   :background-image (str "url(http://cdn.tiye.me/logo/respo.png)"),
   :background-size :cover,
   :display :inline-block,
   :vertical-align :middle})

(def style-suggest {:padding-top 48, :padding-bottom 48})

(defcomp
 comp-home
 ()
 (div
  {}
  (div {:style (merge ui/column ui/center style-header)} (div {:style style-logo}))
  (div
   {:style (merge ui/center ui/row style-suggest)}
   (div
    {:style style-description}
    (<> span "Respo: a Virtual DOM library in ClojureScript." nil))
   (=< 8 nil)
   (a
    {:href "https://github.com/Respo/respo-examples", :target "_blank"}
    (button {:inner-text "Read Examples", :style (merge ui/button)}))
   (=< 8 nil)
   (a
    {:href "https://github.com/Respo/respo/wiki/Beginner-Guide", :target "_blank"}
    (button {:inner-text "Beginner Guide", :style (merge ui/button)})))
  (div
   {:style {:width 800, :margin :auto, :font-size 16}}
   (comp-md-block
    (inline-resource "content.md")
    {:highlight (fn [code lang] (.-value (.highlight hljs lang code)))}))
  (div {:style style-footer} (img {:src "https://img.shields.io/clojars/v/respo.svg"}))))
