
(ns app.comp.home
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo-ui.colors :as colors]
            [respo.macros :refer [defcomp <> span div button a img pre code]]
            [respo.comp.space :refer [=<]]
            [respo-md.comp.md :refer [comp-md-block]]
            ["highlight.js" :as hljs]))

(def style-description
  {:font-size 16, :font-weight 400, :color colors/texture, :font-family "Hind"})

(def style-footer {:min-height 200, :padding 32})

(def style-header {:padding-top 64, :padding-bottom 0, :background-color :white})

(def style-logo
  {:width 160,
   :height 160,
   :background-image (str "url(http://logo.respo.site/respo.png)"),
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
   {:style {:width 800, :margin :auto}}
   (comp-md-block
    "Respo is built with ClojureScript. You can create elements in Clojure syntax:\n\n```clojure\n(div {:class-name \"demo\"\n      :style {:color :red\n              :font-size 16\n              :font-family \"Josefin Sans\"}\n      :on-click (fn [event dispatch! mutate!])})\n\n; respo.macros/div\n```\n\nAnd to nest child elements:\n\n```clojure\n(div {}\n  (span {})\n  (div {}))\n\n; respo.macros/span\n```\n\nTo add text node:\n\n```clojure\n(div {}\n  (<> \"text\")\n  (<> \"text with style\" {:color :red}))\n\n; respo.macros/<>\n```\n\nTo define a component, use a macro `defcomp`:\n\n```clojure\n(defcomp comp-demo [p1 p2]\n  (div {}\n    (<> p1)\n    (<> p2)))\n\n(comp-demo :a :b)\n\n; respo.macros/defcomp\n```\n\nTo mount component:\n\n```clojure\n(defonce *store (atom {}))\n(defn dispatch! [op op-data] (swap! *store assoc :a 1))\n\n(render! mount-target (comp-container @*store) dispatch!)\n\n; respo.core/render!\n```\n\nTo hot replace app:\n\n```clojure\n(defn reload! []\n  (clear-cache!)\n  (render! mount-target (comp-container @*store) dispatch!))\n\n; respo.core/clear-cache!\n```\n\nRespo has advanced feature for states management, which brings more complexity. You need to explore more about that before digging into the states part.\n\n* [Read guides](https://github.com/Respo/respo/wiki)\n* [Try minimal Respo app by your own](https://github.com/Respo/minimal-respo)\n* [Create tiny apps with a macro](https://github.com/Respo/tiny-app/)\n"
    {:highlight (fn [code lang] (.-value (.highlight hljs lang code)))}))
  (div {:style style-footer} (img {:src "https://img.shields.io/clojars/v/respo.svg"}))))
