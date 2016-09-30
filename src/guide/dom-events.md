
DOM events
----

Here is a simple demo handling `input` events:

```clojure
(defn on-input [mutate!]
  (fn [e dispatch!]
    (println (:value e))))

(input {:event {:input (on-input mutate!)}})
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

### Propagation

Respo only support a small subset of events by now. Events can be divided into two groups depending on whether or not propagate:

```clojure
(def no-bubble-events [:focus :blur :scroll])

(def bubble-events
 [:click
  :dblclick
  :change
  :input
  :keydown
  :keyup
  :wheel
  :mousedown
  :touchstart])
```

Event handlers are not attached to the DOM directly especially for the one that propagates. Elements with events may contain an attribute like `data-event="#{:keydown}"` that indicates it listens to `keydown` event:

```html
<input placeholder="Pick a name, and hit Enter"
       data-coord="[:container 5 :reply 0]"
       data-event="#{:keydown}"
       style="line-height:2;width:100%;">
```

Events that propagates will be listend at the mount point element. For events that does not propagate there will be a event listener attached to the specific elements for listening.

As an event is triggered, Respo seek the event handler in the virtual DOM, and call it with some arguments.
