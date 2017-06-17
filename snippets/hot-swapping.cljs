
; triggered by build tool
(defn reload! []
  ; remove caches of component tree
  (clear-cache!)
  ; now rendering process use no caches
  (render-app!)
  (println "Code update."))

; respo.core/clear-cache!
