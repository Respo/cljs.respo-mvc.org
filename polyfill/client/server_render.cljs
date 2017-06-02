
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
        (if (:build? data) (script {:src (:vendor resources)}))
        (script {:attrs {:src (:main resources)}})))))

(defn generate-html [store]
  (let [ tree (comp-container store #{:shell} {:highlight highlight-code})
         html-content (make-string tree)
         manifest (js/JSON.parse (slurp "dist/manifest.json"))
         resources {:css (str "/" (aget manifest "main.css"))
                    :main (str "/" (aget manifest "main.js"))
                    :vendor (str "/" (aget manifest "vendor.js"))}]
    (html-dsl {:build? true} resources html-content #{:shell})))

(defn generate-empty-html []
  (html-dsl {:build? false} {:main "/main.js"} "" {}))

(defn spit [file-name content]
  (println "Spit to" file-name)
  (.writeFileSync fs file-name content))

(def pages [
  "/index.html"
  "/discuss.html"
  "/guide/why-respo.html"
  "/guide/pros-and-cons.html"
  "/guide/environment.html"
  "/guide/tutorial.html"
  "/guide/dom-elements.html"
  "/guide/dom-properties.html"
  "/guide/dom-events.html"
  "/guide/styles.html"
  "/guide/render-list.html"
  "/guide/hot-swapping.html"
  "/guide/base-components.html"
  "/guide/trouble-shooting.html"
  "/guide/virtual-dom.html"
  "/guide/server-rendering.html"
  "/docs/overview.html"
  "/docs/div.html"
  "/docs/create-comp.html"
  "/docs/create-element.html"
  "/docs/comp-text.html"
  "/docs/comp-code.html"
  "/docs/comp-space.html"
  "/docs/comp-debug.html"
  "/docs/render!.html"
  "/docs/gc-states!.html"
  "/docs/clear-cache!.html"
  "/docs/falsify-stage!.html"
  "/docs/make-string.html"
  "/docs/make-html.html"
  "/docs/render-app.html"
  "/docs/purify-element.html"
  "/docs/mute-element.html"
  "/docs/find-element-diffs.html"
  "/docs/apply-dom-changes.html"
  "/docs/activate-instance.html"
  "/docs/patch-instance.html"
  "/docs/release-instance.html"
  "/docs/build-deliver-event.html"
  "/docs/mutate-factory.html"])

(def routes
  {"home" [],
   "index.html" [],
   "dev.html" [],
   "guide" ["guide-path"],
   "docs" ["docs-path"],
   "discuss.html" []})

(defn generate-all! []
  (doseq [page-path pages]
    (spit (str "dist" page-path)
          (generate-html {:router (parse-address page-path routes)}))))

(defn -main []
  (if dev?
    (spit "dist/dev.html" (generate-empty-html))
    (if all?
      (generate-all!)
      (spit "dist/index.html" (generate-html {:router (parse-address "" routes)})))))

(-main)
