
(ns client.comp.home
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.alias :refer [create-comp div span button a img pre code]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.debug :refer [comp-debug]]
            [client.snippets :as snippets]))

(def style-card {:display :inline-block, :vertical-align :top, :margin 16})

(defn render-snippet [demo-code options]
  (let [highlight-code (:highlight options)]
    (pre
     {:attrs {:class-name "code-snippet"}}
     (code {:attrs {:innerHTML (highlight-code demo-code "clojure")}}))))

(def style-header {:padding-top 64, :padding-bottom 0, :background-color :white})

(def style-suggest {:padding-top 48, :padding-bottom 48})

(def style-footer {:min-height 200, :padding 32})

(def demo-props {:style style-card})

(def style-demo
  {:font-size 16, :font-family "Josefin Sans", :text-align :center, :line-height 1.4})

(defn render-demo [text] (div {:attrs {:inner-text text}, :style style-demo}))

(def style-logo
  {:width 160,
   :height 160,
   :background-image (str "url(http://logo.respo.site/respo.png)"),
   :background-size :cover,
   :display :inline-block,
   :vertical-align :middle})

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

(def comp-home
  (create-comp
   :home
   (fn [options]
     (fn [state mutate!]
       (div
        {}
        (div {:style (merge ui/column ui/center style-header)} (div {:style style-logo}))
        (div
         {:style (merge ui/center ui/row style-suggest)}
         (div
          {:style style-description}
          (comp-text "Respo: a virtul DOM library in ClojureScript." nil))
         (comp-space 8 nil)
         (a
          {:attrs {:href "https://github.com/Respo/respo-examples", :target "_blank"}}
          (button {:style (merge ui/button), :attrs {:inner-text "Read Examples"}}))
         (comp-space 8 nil)
         (a
          {:attrs {:href "https://github.com/Respo/respo/wiki/Quick-Start",
                   :target "_blank"}}
          (button {:style (merge ui/button), :attrs {:inner-text "Quick Start"}})))
        (div
         {:style style-snippets}
         (div
          demo-props
          (render-demo "Component Nesting")
          (render-snippet snippets/component options))
         (div
          demo-props
          (render-demo "Inline Styles")
          (render-snippet snippets/inline-styles options))
         (div
          demo-props
          (render-demo "Event Handling")
          (render-snippet snippets/controller options))
         (div
          demo-props
          (render-demo "Hot Swapping")
          (render-snippet snippets/hot-swapping options))
         (div demo-props (render-demo "Model") (render-snippet snippets/model options))
         (div demo-props (render-demo "States") (render-snippet snippets/states options)))
        (div
         {:style style-footer}
         (img {:attrs {:src "https://img.shields.io/clojars/v/respo.svg"}})))))))
