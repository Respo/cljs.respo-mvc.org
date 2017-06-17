
(defcomp comp-container [store]
  (div {}))

(defonce ref-store (atom {:states {}}))

(def mount-target (.querySelector js/document "#app"))

(defn render-app! []
  (let [app (comp-container @ref-store)]
    (render! app mount-target dispatch!)))

; respo.core/render!
; respo.alias/div
; respo.alias/create-comp
