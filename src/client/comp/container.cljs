
(ns client.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.alias :refer [create-comp div span]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.debug :refer [comp-debug]]
            [client.comp.header :refer [comp-header]]
            [client.comp.home :refer [comp-home]]
            [client.comp.discuss :refer [comp-discuss]]
            [client.comp.guide :refer [comp-guide]]
            [client.comp.docs :refer [comp-docs]]))

(def comp-container
  (create-comp
   :container
   (fn [store ssr-stages options]
     (fn [state mutate!]
       (let [router (:router store)]
         (div
          {:style (merge ui/global)}
          (comp-header)
          (case (:name router)
            "home" (comp-home options)
            "index.html" (comp-home options)
            "dev.html" (comp-home options)
            "discuss.html" (comp-discuss)
            "guide" (comp-guide (get-in router [:data "guide-path"]) options)
            "docs" (comp-docs (get-in router [:data "docs-path"]) options)
            nil)
          (comment comp-debug store {:bottom 0})))))))
