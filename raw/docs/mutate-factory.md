
## `mutate-factory`

```clojure
(defn render-element [markup states-ref]
  (let [build-mutate (mutate-factory global-element states-ref)]
    (render-app markup @states-ref build-mutate @cache-element)))
```
