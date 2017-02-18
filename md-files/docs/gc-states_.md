
## `gc-states!`

When components are unmounted, their states in `global-states` should be cleaned.
This normally happens on every store change, use `add-watch`:

```clojure
(add-watch global-store :gc (fn [] (gc-states! global-states)))
```

This is optional since not all apps using component states heavily.

> Notice, doing GC may trigger an extra render loop, even it's actually neccessary.
