
(defonce ref-store
  (atom
    {:router nil
     :tasks []
     :states {}}))

(defn updater [store op op-data]
  (case op
    :states (update store :states (mutate op-data))
    :add-task (add-task store op-data)
    store))

(defn dispatch! [op op-data]
  (let [new-store (updater @ref-store op op-data)]
    (reset! ref-store new-store)))

(add-watch ref-store
  (fn [] (println "store is changed!")))
