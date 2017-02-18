
(ns client.comp.home
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.alias :refer [create-comp div span a img pre code]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.debug :refer [comp-debug]]
            [client.snippets :as snippets]))

(def style-logo
  {:width 80,
   :height 80,
   :background-image (str
                      "url(https://avatars3.githubusercontent.com/u/20469468?v=3&s=200)"),
   :background-size :cover,
   :display :inline-block,
   :vertical-align :middle})

(def style-logo-name {:font-size 40, :vertical-align :middle})

(defn render-feature [text] (div {:attrs {:inner-text text}}))

(def demo-props {:style ui/row})

(defn render-demo [text] (div {:attrs {:inner-text text}}))

(defn render-snippet [demo-code] (pre {} (code {:attrs {:inner-text demo-code}})))

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
         (img {:attrs {:src "https://img.shields.io/clojars/v/respo.svg"}}))
        (div
         {}
         (render-feature "Virtual DOM based solution")
         (render-feature "Persistent data structure")
         (render-feature "DSL powered by ClojureScript")
         (render-feature "Smooth hot code swapping")
         (render-feature "Inline with HashMaps")
         (render-feature "Store abstraction with Atom"))
        (div
         {}
         (div
          demo-props
          (render-demo "Component nesting")
          (render-snippet snippets/component))
         (div
          demo-props
          (render-demo "Store initialization")
          (render-snippet snippets/model))
         (div demo-props (render-demo "Component mounting") (render-snippet snippets/view))
         (div
          demo-props
          (render-demo "Event handling")
          (render-snippet snippets/controller))
         (div
          demo-props
          (render-demo "Inline Styles")
          (render-snippet snippets/inline-styles))
         (div
          demo-props
          (render-demo "Hot Swapping")
          (render-snippet snippets/hot-swapping))
         (div demo-props (render-demo "Component States") (render-snippet snippets/states))))))))
