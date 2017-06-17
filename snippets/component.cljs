
(defcomp comp-child [prop-b]
  (div {} (comp-text (str prop-b) nil)))

(defcomp comp-demo [prop-a prop-b]
  (div {:class-name "demo" :style {} :event {}}
    (comp-text (str prop-a) nil)
    (comp-child prop-b)))

; respo.macros/defcomp
; respo.alias/div
; respo.comp/comp-text
