
(defcomp comp-container [store]
  (div {}))

(defonce ref-store (atom {:states {}}))

(def mount-target (.querySelector js/document "#app"))

(defn render-app! []
  (let [app (comp-container @ref-store)]
    (render! mount-target app dispatch!)))

; respo.macros/div
; respo.core/create-comp
; respo.core/render!
