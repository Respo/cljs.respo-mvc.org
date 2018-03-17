
(ns app.main
  (:require [respo.core :refer [render! clear-cache! realize-ssr!]]
            [app.comp.container :refer [comp-container]]
            [cljs.reader :refer [read-string]]
            [app.schema :as schema]
            ["highlight.js" :as hljs]
            ["highlight.js/lib/languages/bash" :as bash-lang]
            ["highlight.js/lib/languages/clojure" :as clojure-lang]))

(defonce *store (atom {}))

(defn dispatch! [op op-data] (println "Dispatch!" op))

(defn highlight-code [code lang]
  (let [result ((aget hljs "highlight") lang code)] (.-value result)))

(def mount-target (.querySelector js/document ".app"))

(defn render-app! [renderer]
  (let [app (comp-container @*store)] (renderer mount-target app dispatch!)))

(def ssr? (some? (.querySelector js/document "meta.respo-ssr")))

(defn main! []
  (if ssr? (render-app! realize-ssr!))
  (.registerLanguage hljs "clojure" clojure-lang)
  (.registerLanguage hljs "bash" bash-lang)
  (render-app! render!)
  (add-watch *store :changes (fn [] (render-app! render!)))
  (println "App started!"))

(def mode :history)

(defn reload! [] (clear-cache!) (render-app! render!) (println "Code updated."))

(set! (.-onload js/window) main!)
