
(ns app.main
  (:require [respo.core :refer [render! clear-cache! realize-ssr!]]
            [app.comp.container :refer [comp-container]]
            [cljs.reader :refer [read-string]]
            [app.schema :as schema]
            ["highlight.js" :as hljs]))

(def ssr? (some? (.querySelector js/document "meta.respo-ssr")))

(defn dispatch! [op op-data] (println "Dispatch!" op))

(defonce store-ref (atom {}))

(defn highlight-code [code lang]
  (let [result ((aget hljs "highlight") lang code)] (.-value result)))

(def mount-target (.querySelector js/document ".app"))

(defn render-app! [renderer]
  (let [app (comp-container @store-ref {:highlight highlight-code})]
    (renderer mount-target app dispatch!)))

(defn main! []
  (if ssr? (render-app! realize-ssr!))
  (render-app! render!)
  (add-watch store-ref :changes (fn [] (render-app! render!)))
  (println "App started!"))

(def mode :history)

(defn reload! [] (clear-cache!) (render-app! render!) (println "Code updated."))

(set! (.-onload js/window) main!)
