
## DOM events

Here is a simple demo handling `input` events:

```clojure
(defn on-input []
  (fn [e dispatch!]
    (println (:value e))))

(input {:event {:input (on-input)}})
```

`e` is a HashMap with several entries:

```clojure
(def e
  {:type "input"
   :original-event event})
```

The details:

```clojure
(defn event->edn [event]
  (comment .log js/console "simplify event:" event)
  (-> (case (.-type event)
        "click"   {:type :click}
        "keydown" {:key-code (.-keyCode event), :type :keydown}
        "keyup"   {:key-code (.-keyCode event), :type :keyup}
        "input"   {:value (aget (.-target event) "value"), :type :input}
        "change"  {:value (aget (.-target event) "value"), :type :change}
        "focus"   {:type :focus}
        {:msg (str "Unhandled event: " (.-type event)), :type (.-type event)})
   (assoc :original-event event)))
```

Events are bound directly on the elements for simplicity and consistency. And it stops propagation when event is triggered.
