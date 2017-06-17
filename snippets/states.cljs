
(defn on-input [state cursor]
  (fn [e dispatch!]
    ; dispatch :states action with cursor and new state
    (dispatch! :states [cursor (:value e)])))

(defcomp comp-field [states]
  (let
    ; states are inside store as a states tree
    ; use `or` to specify default state
    [state (or (:data states) "")]
    (div {}
      (div {}
        (comp-text "name" nil))
      (input {:placeholder "name"
              :value state
              :event {:input (on-input states cursor)}}))))

; cursor is defined inside the macro `defcomp`
