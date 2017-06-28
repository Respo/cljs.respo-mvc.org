
(defcomp comp-child [prop-b]
  (div {} (<> span (str prop-b) nil)))

(defcomp comp-demo [prop-a prop-b]
  (div {:class-name "demo" :style {} :event {}}
    (<> span (str prop-a) nil)
    (comp-child prop-b)))

; respo.macros/div
; respo.macros/<>
; respo.core/defcomp
