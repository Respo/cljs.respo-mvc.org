
(def comp-child
  (create-comp :child
    (fn [prop-b]
      (fn [state mutate!]
        (div {}
          (comp-text (str prop-b) nil))))))

(defn render [prop-a prop-b]
  (fn [state mutate!]
    (div {:style {}
          :event {}
          :attrs {:class-name "demo"}}
      (comp-text (str prop-a) nil)
      (comp-child prop-b))))

(def comp-demo (create-comp :demo render))

; respo.alias/div
; respo.alias/create-comp
; respo.comp/comp-text