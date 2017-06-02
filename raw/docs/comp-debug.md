
## `comp-debug`

This component is similiar to `comp-text`, it shows data in string with styles.
The differeces is, `variable-x` can be anything type(formatted with `pr-str`):

```clojure
(comp-debug variable-x {:color "red"})
```

This component also comes with special styles to show it in an absolute position.
And here's the default styles:

```clojure
(def default-style
 {:line-height 1.6,
  :box-shadow (str "0 0 1px " (hsl 0 0 0 0.8)),
  :color "white",
  :font-size "10px",
  :background-color (hsl 0 0 0),
  :opacity 0.4,
  :padding "2px 4px",
  :position "absolute",
  :pointer-events "none",
  :font-family "Menlo"})
```

```clojure
(comment (comp-debug x nil))
```