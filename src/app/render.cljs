
(ns app.render
  (:require [respo.render.html :refer [make-html make-string]]
            [app.comp.container :refer [comp-container]]
            [app.schema :as schema]
            [shell-page.core :refer [make-page spit slurp]]))

(def base-info
  {:title "Respo: a virtual DOM library in ClojureScript",
   :icon " http://logo.mvc-works.org/mvc.png",
   :ssr nil,
   :inner-html nil})

(def ga-html (slurp "entry/ga.html"))

(def hljs (js/require "highlight.js"))

(defn highlight-code [code lang] (.-value (.highlight hljs lang code)))

(defn prod-page []
  (let [html-content (make-string (comp-container schema/store {:highlight highlight-code}))
        manifest (.parse js/JSON (slurp "dist/manifest.json"))]
    (make-page
     (str html-content ga-html)
     (merge base-info {:ssr "respo-ssr", :styles [(aget manifest "main.css")], :scripts []}))))

(defn dev-page [] (make-page "" (merge base-info {:styles [], :scripts ["/main.js"]})))

(defn main! []
  (if (= js/process.env.env "dev")
    (spit "dist/index.html" (dev-page))
    (spit "dist/index.html" (prod-page))))

(main!)
