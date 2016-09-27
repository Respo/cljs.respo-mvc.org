
(def initial-store {
  :router nil
  :tasks []
})

(defonce store-ref (atom initial-store))

(defn updater [store op op-data]
  (case op
    :add-task (add-task store op-data)
    store))

(defn dispatch! [op op-data]
  (let [new-store (updater @store-ref op op-data)]
    (reset! store-ref new-store)))

(add-watch store-ref
  (fn []
    (println "store is changed!")))