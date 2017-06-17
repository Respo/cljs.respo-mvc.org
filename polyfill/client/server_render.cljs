
(ns client.server-render
  (:require
    [respo.alias :refer [html head title script style meta' div link body]]
    [respo.render.html :refer [make-html make-string]]
    [respo-router.util.listener :refer [parse-address]]
    [client.comp.container :refer [comp-container]]))

(def dev? (= js/process.env.env "dev"))
(def all? (= js/process.env.page "all"))

(def fs (js/require "fs"))
(def hljs (js/require "highlight.js"))

(def ga-html (.readFileSync fs "entry/ga.html" "utf8"))

(defn slurp [x] (.readFileSync fs x "utf8"))

(defn highlight-code [code lang]
  (.-value (.highlight hljs lang code)))

(defn html-dsl [data resources html-content ssr-stages]
  (make-html
    (html {}
      (head {}
        (title {:attrs {:innerHTML "Respo"}})
        (link {:attrs {:rel "icon" :type "image/png" :href "http://logo.respo.site/respo.png"}})
        (link (:attrs {:rel "manifest" :href "manifest.json"}))
        (meta' {:attrs {:charset "utf-8"}})
        (meta' {:attrs {:name "viewport" :content "width=device-width, initial-scale=1"}})
        (meta' {:attrs {:id "ssr-stages" :content (pr-str ssr-stages)}})
        (if all? (div {:attrs {:innerHTML ga-html}}))
        (if (contains? resources :css)
          (link {:rel "stylesheet", :type "text/css", :href (:css resources)}))
        (script {:attrs {:id "config" :type "text/edn" :innerHTML (pr-str data)}}))
      (body {}
        (div {:attrs {:id "app" :innerHTML html-content}})
        (if (not (:build? data)) (script {:attrs {:src (:main resources)}}))))))

(defn generate-html []
  (let [ tree (comp-container {} #{:shell} {:highlight highlight-code})
         html-content (make-string tree)
         manifest (js/JSON.parse (slurp "dist/manifest.json"))
         resources {:css (str "/" (aget manifest "main.css"))}]
    (html-dsl {:build? true} resources html-content #{:shell})))

(defn generate-empty-html []
  (html-dsl {:build? false} {:main "/main.js"} "" {}))

(defn spit [file-name content]
  (println "Spit to" file-name)
  (.writeFileSync fs file-name content))

(defn -main []
  (if dev?
    (spit "dist/index.html" (generate-empty-html))
    (spit "dist/index.html" (generate-html))))

(-main)
