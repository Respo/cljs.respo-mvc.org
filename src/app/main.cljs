
(ns app.main
  (:require [respo.core :refer [render! clear-cache! falsify-stage! render-element]]
            [app.comp.container :refer [comp-container]]
            [cljs.reader :refer [read-string]]
            [app.schema :as schema]
            ["highlight.js" :as hljs]))

(defn dispatch! [op op-data] (println "Dispatch!" op))

(defonce store-ref (atom {}))

(defn highlight-code [code lang]
  (let [result ((aget hljs "highlight") lang code)] (.-value result)))

(def ssr-stages
  (let [ssr-element (.querySelector js/document "#ssr-stages")
        ssr-markup (.getAttribute ssr-element "content")]
    (read-string ssr-markup)))

(defn render-app! []
  (let [target (.querySelector js/document "#app")]
    (render!
     (comp-container @store-ref ssr-stages {:highlight highlight-code})
     target
     dispatch!)))

(defn main! []
  (enable-console-print!)
  (if (not (empty? ssr-stages))
    (let [target (.querySelector js/document "#app")]
      (falsify-stage!
       target
       (render-element (comp-container @store-ref ssr-stages {:highlight highlight-code}))
       dispatch!)))
  (render-app!)
  (add-watch store-ref :changes render-app!)
  (println "App started!"))

(def mode :history)

(defn reload! [] (clear-cache!) (render-app!) (println "Code updated."))

(set! (.-onload js/window) main!)
