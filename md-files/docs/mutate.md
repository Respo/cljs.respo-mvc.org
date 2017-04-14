
## `mutate`

Since states is maintained insides Store within `:states`, updating state is just dispatching an action called `:states` with data like:

```clojure
[cursor new-state]
```

The function `mutate` is a helper function to handle `:states` action in `updater`:

```clojure
(defn updater [store op op-data]
  (case op
    :states (update store :states (mutate op-data))
    store))
```
