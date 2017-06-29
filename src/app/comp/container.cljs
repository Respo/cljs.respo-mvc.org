
(ns app.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.alias :refer [create-comp div span]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.debug :refer [comp-debug]]
            [app.comp.header :refer [comp-header]]
            [app.comp.home :refer [comp-home]]))

(def comp-container
  (create-comp
   :container
   (fn [store ssr-stages options]
     (fn [state mutate!]
       (let [router (:router store)]
         (div {:style (merge ui/global)} (comp-header) (comp-home options)))))))
