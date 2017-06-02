
(def comp-container
  (create-comp :container
    (fn [store] (fn [cursor] (div {})))))

(defonce ref-store (atom {:states {}}))

(defn render-app! []
  (let [target (.querySelector js/document "#app")]
    (render! (comp-container @ref-store) target dispatch!)))

; respo.core/render!
; respo.alias/div
; respo.alias/create-comp
