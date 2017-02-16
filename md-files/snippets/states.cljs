
(defn init-state [& args] "")

(defn update-state [old-state content] content)

(defn on-input [mutate!]
  (fn [e dispatch!]
    (mutate! (:value e))))

(defn render []
  (fn [state mutate!]
    (div {}
      (div {}
        (comp-text "name" nil))
      (input {:event {:input (on-input mutate!)}
              :attrs {:placeholder "name"
                      :value state}}))))

(def comp-field
  (create-comp :field init-state update-state render))
