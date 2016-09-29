
`div`
----

Here's how you use `div` function to create a tree of `<div>`s:

```clojure
(div {:style {}
      :event {}
      :attrs {}}
  (div {})
  (div {}))
```

It's first argument is a HashMap consisted of `:style` `:event` and `:attrs`.
Each of them is a HashMap, and you may have seen the demos.

Find more in [DOM elements](#/guide/dom-elements).
