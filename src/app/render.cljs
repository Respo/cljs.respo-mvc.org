
(ns app.render
  (:require [respo.render.html :refer [make-string]]
            [app.comp.container :refer [comp-container]]
            [app.schema :as schema]
            [shell-page.core :refer [make-page spit slurp]]
            [cljs.reader :refer [read-string]]))

(def base-info
  {:title "Respo: a virtual DOM library in ClojureScript",
   :icon "http://cdn.tiye.me/logo/mvc-works.png",
   :ssr nil,
   :inline-styles [(slurp "entry/github-gist.css") (slurp "entry/main.css")]})

(defn dev-page []
  (make-page
   ""
   (merge base-info {:styles ["http://localhost:8100/main.css"], :scripts ["/main.js"]})))

(def ga-html (slurp "entry/ga.html"))

(def hljs (js/require "highlight.js"))

(defn highlight-code [code lang] (.-value (.highlight hljs lang code)))

(def preview? (= js/process.env.prod "preview"))

(defn prod-page []
  (let [html-content (make-string (comp-container schema/store {:highlight highlight-code}))
        assets (read-string (slurp "dist/assets.edn"))
        cdn (if preview? "" "http://cdn.tiye.me/respo.site/")
        prefix-cdn (fn [x] (str cdn x))]
    (make-page
     (str html-content ga-html)
     (merge
      base-info
      {:ssr "respo-ssr",
       :styles ["http://cdn.tiye.me/favored-fonts/main.css"],
       :scripts (map #(-> % :output-name prefix-cdn) assets)}))))

(defn main! []
  (if (= js/process.env.env "dev")
    (spit "target/index.html" (dev-page))
    (spit "dist/index.html" (prod-page))))

(main!)
