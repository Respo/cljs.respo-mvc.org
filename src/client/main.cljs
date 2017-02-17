
(ns client.main
  (:require [respo.core
             :refer
             [render! clear-cache! falsify-stage! render-element gc-states!]]
            [client.comp.container :refer [comp-container]]
            [cljs.reader :refer [read-string]]
            [respo-router.core :refer [render-url!]]
            [respo-router.util.listener :refer [listen! parse-address]]
            [client.schema :as schema]))

(defonce store-ref (atom {:router (parse-address js/location.pathname schema/routes)}))

(defn dispatch! [op op-data]
  (let [new-store (case op
                    :router/set
                      (assoc @store-ref :router (parse-address op-data schema/routes))
                    @store-ref)]
    (reset! store-ref new-store)))

(def mode :history)

(defonce states-ref (atom {}))

(defn render-app! []
  (let [target (.querySelector js/document "#app")]
    (render! (comp-container @store-ref) target dispatch! states-ref)))

(def ssr-stages
  (let [ssr-element (.querySelector js/document "#ssr-stages")
        ssr-markup (.getAttribute ssr-element "content")]
    (read-string ssr-markup)))

(defn -main! []
  (enable-console-print!)
  (if (not (empty? ssr-stages))
    (let [target (.querySelector js/document "#app")]
      (falsify-stage!
       target
       (render-element (comp-container @store-ref ssr-stages) states-ref)
       dispatch!)))
  (render-app!)
  (add-watch store-ref :gc (fn [] (gc-states! states-ref)))
  (add-watch store-ref :changes render-app!)
  (add-watch states-ref :changes render-app!)
  (add-watch
   store-ref
   :router
   (fn [] (render-url! (:router @store-ref) schema/routes :history)))
  (listen! schema/routes dispatch! :history)
  (println "App started!"))

(defn on-jsload! [] (clear-cache!) (render-app!) (println "Code updated."))

(set! (.-onload js/window) -main!)
