
`comp-text`
----

In Respo, `textNode`s are not avaible, so I use `<span>` to add text contents, like:

```clojure
(span {:attrs {:innerHTML "content"}
       :style {:color "blue"}})
```

People always hate writing simple content in this why, so I added a component:

```clojure
(comp-text "content" {:color "blue"})
```

You may use `nil` instead of a HashMap when no styles is required:

```clojure
(comp-text "content" nil)
```

It's actually a component that contains a single element.
You may also define a function to do that if you like.
