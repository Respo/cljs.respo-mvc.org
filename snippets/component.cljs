
(defcomp comp-child [prop-b]
  (div {} (<> span (str prop-b) nil)))

(defcomp comp-demo [prop-a prop-b]
  (div {:class-name "demo" :style {} :on {}}
    (<> span (str prop-a) nil)
    (comp-child prop-b)))

; respo.macros/div
; respo.macros/<>
; respo.macros/defcomp
