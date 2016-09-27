
; triggers by boot-cljs or figwheel
(defn on-jsload []
  ; remove caches of component tree
  (clear-cache!)
  ; now rendering process use no caches
  (render-app!)
  (println "code update."))

; respo.core/clear-cache!
