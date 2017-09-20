
(defcomp comp-container [store]
  (div {}))

(defonce *store (atom {:states {}}))

(def mount-target (.querySelector js/document ".app"))

(defn render-app! []
  (let [app (comp-container @*store)]
    (render! mount-target app dispatch!)))

; respo.macros/div
; respo.core/create-comp
; respo.core/render!
