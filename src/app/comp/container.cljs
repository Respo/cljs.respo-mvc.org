
(ns app.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.macros :refer [defcomp div span <>]]
            [respo.comp.space :refer [=<]]
            [respo.comp.inspect :refer [comp-inspect]]
            [app.comp.header :refer [comp-header]]
            [app.comp.home :refer [comp-home]]))

(defcomp comp-container (store) (div {:style (merge ui/global)} (comp-header) (comp-home)))
