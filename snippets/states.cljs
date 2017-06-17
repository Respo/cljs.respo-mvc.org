
(defn on-input [state cursor]
  (fn [e dispatch!]
    ; dispatch :states action with cursor and new state
    (dispatch! :states [cursor (:value e)])))

(def comp-field
  (create-comp :field
    (fn [states]
      (fn [cursor]
        (let
          ; states are inside store as a states tree
          ; use `or` to specify default state
          [state (or (:data states) "")]
          (div {}
            (div {}
              (comp-text "name" nil))
            (input {:event {:input (on-input states cursor)}
                    :attrs {:placeholder "name"
                            :value state}})))))))
