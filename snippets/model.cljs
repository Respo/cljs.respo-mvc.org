
; atom to define mutable store
(defonce *store
  (atom
    {:tasks []
     ; this is states
     :states {}}))

(defn updater [store op op-data]
  (case op
    :states (update store :states (mutate op-data))
    :add-task (add-task store op-data)
    store))

(defn dispatch! [op op-data]
  ; mutate store
  (reset! *store (updater @*store op op-data)))

; watch store changes
(add-watch *store
  (fn [] (println "store is changed!")))
