
`render!`
----

`render!` comes with side effects, it renders virtual to the mount pointer:

```clojure
(render!
  (comp-container @global-store)
  target
  dispatch!
  global-states)
```

`target` is the mount pointer. `global-states` is the reference to the atom of states.

Internally there's a mutable state tracking the DOM state.
And the state `falsify-stage!` changes is this one.

