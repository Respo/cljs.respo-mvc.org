
(defn input [e dispatch!]
  (dispatch! :confirm "from button")
  (println "clicked"))

(div {:style ui/button
      :event {:click on-click}}})

; respo-ui.style :as ui
