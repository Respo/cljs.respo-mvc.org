
(defn render [store]
  (fn [state mutate!]
    (div {})))

(def comp-container (create-comp :container render))

(defonce store-ref (atom {}))
(defonce states-ref (atom {}))

(defn render-app! []
  (let [target (.querySelector js/document "#app")]
    (render! (comp-container @store-ref) target dispatch! states-ref)))

; respo.core/render!
; respo.alias/div
; respo.alias/create-comp
