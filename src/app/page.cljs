
(ns app.page
  (:require [respo.render.html :refer [make-string]]
            [app.comp.container :refer [comp-container]]
            [app.schema :as schema]
            [shell-page.core :refer [make-page spit slurp]]
            [cljs.reader :refer [read-string]]
            [app.config :as config]
            [app.util :refer [get-env!]]))

(def base-info
  (merge
   {:title (:title config/site),
    :icon (:icon config/site),
    :ssr nil,
    :inline-styles [(slurp "entry/github-gist.css") (slurp "entry/main.css")]}))

(defn dev-page []
  (make-page "" (merge base-info {:styles [(:dev-ui config/site)], :scripts ["/client.js"]})))

(def ga-html (slurp "entry/ga.html"))

(def local-bundle? (= "local-bundle" (get-env! "mode")))

(defn prod-page []
  (let [html-content (make-string (comp-container schema/store))
        assets (read-string (slurp "dist/assets.edn"))
        cdn (if local-bundle? "" (:cdn-url config/site))
        prefix-cdn (fn [x] (str cdn x))]
    (make-page
     (str html-content ga-html)
     (merge
      base-info
      {:styles [(:release-ui config/site)],
       :scripts (map #(-> % :output-name prefix-cdn) assets),
       :ssr "respo-ssr",
       :inline-styles [(slurp "./entry/main.css")]}))))

(defn main! []
  (if (contains? config/bundle-builds (get-env! "mode"))
    (spit "dist/index.html" (prod-page))
    (spit "target/index.html" (dev-page))))

(main!)
