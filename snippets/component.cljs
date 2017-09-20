
(defcomp comp-child [prop-b]
  (div {} (<> (str prop-b))))

(defcomp comp-demo [prop-a prop-b]
  (div {:class-name "demo" :style {} :on {}}
    (<> (str prop-a))
    (comp-child prop-b)))

; respo.macros/div
; respo.macros/<>
; respo.macros/defcomp
