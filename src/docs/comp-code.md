
`comp-code`
----

This component is like [`comp-text`](/docs/comp-text.html) but renders a `<code>` element:

```clojure
(comp-code "console.log" {:color "blue"})
```

```clojure
(code {:attrs {:inner-text "console.log"}
       :style {:color "blue"}})
```
