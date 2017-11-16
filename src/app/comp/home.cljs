
(ns app.comp.home
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp <> span div button a img pre code]]
            [respo.comp.space :refer [=<]]))

(def style-card {:display :inline-block, :vertical-align :top, :margin 16})

(defn render-snippet [demo-code options]
  (let [highlight-code (:highlight options)]
    (pre
     {:class-name "code-snippet"}
     (code {:innerHTML (highlight-code demo-code "clojure")}))))

(def style-header {:padding-top 64, :padding-bottom 0, :background-color :white})

(def style-suggest {:padding-top 48, :padding-bottom 48})

(def style-footer {:min-height 200, :padding 32})

(def demo-props {:style style-card})

(def style-demo
  {:font-size 16, :font-family "Josefin Sans", :text-align :center, :line-height 1.4})

(defn render-demo [text] (div {:inner-text text, :style style-demo}))

(def style-logo
  {:width 160,
   :height 160,
   :background-image (str "url(http://logo.respo.site/respo.png)"),
   :background-size :cover,
   :display :inline-block,
   :vertical-align :middle})

(def snippets (js/require "../snippets/index"))

(def style-snippets
  {:background-color colors/paper,
   :width "100%",
   :display :flex,
   :flex-wrap :wrap,
   :justify-content :center,
   :align-items :center,
   :padding 64})

(def style-description
  {:font-size 16, :font-weight 400, :color colors/texture, :font-family "Hind"})

(defcomp
 comp-home
 (options)
 (div
  {}
  (div {:style (merge ui/column ui/center style-header)} (div {:style style-logo}))
  (div
   {:style (merge ui/center ui/row style-suggest)}
   (div
    {:style style-description}
    (<> span "Respo: a virtual DOM library in ClojureScript." nil))
   (=< 8 nil)
   (a
    {:href "https://github.com/Respo/respo-examples", :target "_blank"}
    (button {:inner-text "Read Examples", :style (merge ui/button)}))
   (=< 8 nil)
   (a
    {:href "https://github.com/Respo/respo/wiki/Beginner-Guide", :target "_blank"}
    (button {:inner-text "Beginner Guide", :style (merge ui/button)})))
  (div
   {:style style-snippets}
   (div
    demo-props
    (render-demo "Component Nesting")
    (render-snippet snippets.component options))
   (div
    demo-props
    (render-demo "Inline Styles")
    (render-snippet snippets.inlineStyles options))
   (div
    demo-props
    (render-demo "Event Handling")
    (render-snippet snippets.controller options))
   (div
    demo-props
    (render-demo "Hot Swapping")
    (render-snippet snippets.hotSwapping options))
   (div demo-props (render-demo "Store Management") (render-snippet snippets.model options))
   (div
    demo-props
    (render-demo "State Management")
    (render-snippet snippets.states options)))
  (div {:style style-footer} (img {:src "https://img.shields.io/clojars/v/respo.svg"}))))
