
Hot swapping
----

Hot swapping is actually done by [`boot-reload`](https://github.com/adzerk-oss/boot-reload),
or [Figwheel](https://github.com/bhauman/lein-figwheel).
In the app, very short code is enough:

```clojure
(defn on-jsload []
  (clear-cache!) ; clear component cachees
  (render-app!) ; rerender DOM tree, I mean, a diff/patch loop
  (println "code update."))
```

If you are using Boot, and suppose the namespace is `stack-workflow.core`,
don't forget to add the code to trigger `on-jsload`:

```clojure
(reload :on-jsload 'stack-workflow.core/on-jsload
        :cljs-asset-path ".")
```

In Respo, you are asked to define `global-store` and `global-states` explicitly. They the global states of data. As an Atom, the value inside is immutable, but the reference is mutable. During hot swapping, variables defined with `defonce` will be retained. As a result, component states are persistent even code is swapped:

```clojure
(defonce global-store
 (atom
   (or
     (let [raw (or (.getItem js/localStorage "respo") "[]")]
       (read-string raw))
     schema/store)))

(defonce global-states (atom {}))
```