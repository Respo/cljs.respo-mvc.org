
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
    "Create elements in Clojure syntax:\n\n```clojure\n(div {:class-name \"demo\"\n      :style {:color :red\n              :font-size 16\n              :font-family \"Josefin Sans\"}\n      :on-click (fn [event dispatch! mutate!])})\n\n; respo.macros/div\n```\n\nNest child elements:\n\n```clojure\n(div {}\n  (span {})\n  (div {}))\n\n; respo.macros/span\n```\n\nAdd text nodes:\n\n```clojure\n(div {}\n  (<> \"text\")\n  (<> \"text with style\" {:color :red}))\n\n; respo.macros/<>\n```\n\n### Create Components\n\nTo define components, use `defcomp`, it's a Macro:\n\n```clojure\n(defcomp comp-demo [p1 p2]\n  (div {}\n    (<> p1)\n    (<> p2)))\n\n(comp-demo :a :b)\n\n; respo.macros/defcomp\n```\n\nUse `render!` to mount a component. It also handles re-rendered if mounting already happened.\n\n```clojure\n(defonce *store (atom {}))\n(defn dispatch! [op op-data] (swap! *store assoc :a 1))\n\n(render! mount-target (comp-container @*store) dispatch!)\n\n; respo.core/render!\n```\n\nTo hot replace app, use `render!` function. `clear-cache!` is for clearing internal rendering caches:\n\n```clojure\n(defn reload! []\n  (clear-cache!)\n  (render! mount-target (comp-container @*store) dispatch!))\n\n; respo.core/clear-cache!\n```\n\n### States Management\n\nRespo uses an Atom to maintain global states. Global states and \"Single Source of Truth\" are prefered:\n\n```clojure\n(defonce *store (atom {}))\n(defn dispatch! [op op-data] (swap! *store assoc :a 1))\n\n(add-watch *store :changes\n           (fn []\n               (render! mount-target (comp-container @*store) dispatch!)))\n```\n\nRespo has supports for [component-level states](https://github.com/Respo/respo/wiki/component-states). But is designed in an awkward syntax in order to make sure it's consistent with \"Single Source of Truth\". Read about `mutate!` and `cursor->` in the docs.\n\n### Ecosystem\n\nDuring developing Respo, a bunch of libraries are added:\n\n* [hsl](https://github.com/mvc-works/hsl.clj) -- a function that returns color in string\n* [ui](https://github.com/Respo/respo-ui) -- basic UI styles collected based on Flexbox\n* [markdown](https://github.com/Respo/respo-markdown) -- subset Markdown syntax rendering to virtual DOM\n* [router](https://github.com/Respo/respo-router) -- HTML5 router library decoupled from view part\n* [tiny-app](https://github.com/Respo/tiny-app) -- a macro for inialising app in a easy way\n* [reel](https://github.com/Respo/reel) -- time travelling developing tool\n* [value](https://github.com/Respo/respo-value) -- to display collections\n* [message](https://github.com/Respo/respo-message) -- displaying message on top-right corner\n* [global-popup](https://github.com/Respo/global-popup) and [inflow-popup](https://github.com/Respo/inflow-popup)\n\n### Try Respo\n\nNow it's your turn to read Guide and try Respo:\n\n* [Read guides](https://github.com/Respo/respo/wiki)\n* [Browse examples](https://github.com/Respo/respo-examples/)\n* [Try minimal Respo app by your own](https://github.com/Respo/minimal-respo)\n\nAn easy way to use Respo is using [tiny-app](https://github.com/Respo/tiny-app/). It's a macro that handles dirty works in passing in configurations.\n\nFor Advanced developers, probably the best way to understand Respo is to [read code of how the author is using it](https://github.com/mvc-works/calcit-workflow/blob/master/src/app/main.cljs). [Contact me on Twitter](https://twitter.com/jiyinyiyong) anytime if you got questions.\n\n[Send feedbacks on issues](https://github.com/Respo/respo.site/issues/1) if you want to improve this page. [Old versions](https://gist.github.com/jiyinyiyong/008a2be624a351a11d1ca44f809963a3).\n"
    {:highlight (fn [code lang] (.-value (.highlight hljs lang code)))}))
  (div {:style style-footer} (img {:src "https://img.shields.io/clojars/v/respo.svg"}))))
