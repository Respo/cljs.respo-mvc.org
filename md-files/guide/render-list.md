
Render list
----

To render a list, you need to know another way of calling element functions like `div`:

```clojure
(div {:style {}}
  [["a" (comp-text "this is A" nil)]
   ["b" (comp-text "this is B" nil)]])
```

This is how `div` works internally. Its second argument consists of key/value pairs in a list.

More specificly, it's common pattern to use `->>` to transform the list:

```clojure
(div
  {:style style-list, :attrs {:class-name "task-list"}}
  (->>
    tasks
    (reverse)
    (map (fn [task] [(:id task) (task-component task)]))))
```

Child elements are rendered in the order that items appear in the list. Diffing is not very fast, so don't make the list too large.
