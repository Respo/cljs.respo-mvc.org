
(defn on-input [e dispatch! mutate!]
  ; send next state to store, internally using dispatch!
  (mutate! (:value e)))

(defcomp comp-field [states]
  (let
    ; states are inside store as a states tree
    ; use `or` to specify initial state
    [state (or (:data states) "")]
    (div {}
      (div {}
        (<> span "name" nil))
      (input {:placeholder "name"
              :value state
              :on {:input on-input}}))))

(defcomp comp-parent [states]
  (div {}
    ; create a sub-cursor in states tree, pass down to it
    (cursor-> :field comp-field states)))

; respo.macros/cursor->
