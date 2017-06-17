
(def comp-child
  (create-comp :child
    (fn [prop-b]
      (fn [cursor]
        (div {} (comp-text (str prop-b) nil))))))

(def comp-demo
  (create-comp :demo
    (fn [prop-a prop-b]
      (fn [cursor]
        (div {:style {} :event {} :attrs {:class-name "demo"}}
          (comp-text (str prop-a) nil)
          (comp-child prop-b))))))

; respo.alias/div
; respo.alias/create-comp
; respo.comp/comp-text
