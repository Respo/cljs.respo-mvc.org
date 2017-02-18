
## `build-deliver-event`

```clojure
(defn mount-app [markup target dispatch! states-ref]
  (let [element (render-element markup states-ref)
        deliver-event (build-deliver-event global-element dispatch!)]
    (comment println "mount app")
    (initialize-instance target deliver-event)
    (activate-instance (purify-element element) target deliver-event)
    (reset! global-element element)
    (reset! cache-element element)))
```
