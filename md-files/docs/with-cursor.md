
## `with-cursor`

Suppose you want to add a sub cursor called `:key`:

```clojure
(with-cursor :key (comp-demo (:key state) param))
```

Also you need to select `:key` in `state`, which is a tree.

Make sure the tree created with `with-cursor` function corresponds to the tree of state. An variable called `cursor` will be injected into render function as a parameter for dispatching.
