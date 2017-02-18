
## `create-comp`

This is the function to create components(and "comp" is short for "component" here).
There are two ways to call that function depending on the existence of component state:

```clojure
(create-comp comp-name render)
(create-comp comp-name init-state update-date render)
```

A simple demo can be:

```clojure
(defn init-state [prop-a prop-b]
  0)

(defn update-state [old-state arg-1 arg-2]
  (+ old-state arg-1 arg-2))

(defn render [prop-a prop-b]
  ; `state` is created by `init-state`
  ; `mutate!` can be called like `(mutate! 1 2)`
  (fn [state mutate!]
    (div {})))

(create-comp :demo init-state update-state render)
```

Let's talk about the params.

* `comp-name`

It's a component name in Keyword. It's mostly used during debugging.

* `init-state`

Pure function to create the initial state for a component.

* `update-state`

Pure function to update state. It's called with a helper function: `mutate!`.
It can be called with arbitry numbers of arguments.

* `render`

You can see from the demo, it's a higher order function that returns a function that returns a piece of virtual DOM. You must keep this function as pure as possible to stay away from side effects.
