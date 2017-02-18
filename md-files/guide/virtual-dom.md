
## Virtual DOM

There are elements and components before it's actually rendered. After rendered, if all of elements. The definitions of them are:

```clojure
(defrecord Element [name coord attrs style event children])

(defrecord
  Component
  [name coord args init-state update-state render tree cost])
```

`coord` means "coordinate" in Respo, it looks like `[0 1 3]` or even `[0 0 0 :container 0 0 "a"]`.

If you define component like this:

```clojure
(div
  {:style {:color "red"}
   :attrs {:class-name "demo"}
   :event {:click (fn [e dispatch!])}}
  (div {}))
```

You may get a piece of data in ClojureScript:

```clojure
#respo.alias.Element{:name :div,
                     :coord nil,
                     :attrs ([:class-name "demo"]),
                     :style {:color "red"},
                     :event {:click #object[Function "function (e,dispatch_BANG_){
                                                        return null;
                                                      }"]},
                     :children [[0 #respo.alias.Element{:name :div,
                                                        :coord nil,
                                                        :attrs (),
                                                        :style nil,
                                                        :event (),
                                                        :children []}]]}
```

You may have noticed that in `children` field it's a vector.
There is a `0` indicating it's the first child.
And yes internally that's the true representation of children.

As I told, virtual DOM is normal ClojureScript data,
you can [transform the virtual DOM][transform] in the runtime:

[transform]: https://github.com/Respo/respo-border/blob/master/compiled/src/respo_border/transform/border.cljs

```clojure
(defn interpose-borders [element border-style]
  (if (contains? element :children)
    (update
      element
      :children
      (fn [children]
        (interpose-item
          []
          0
          children
          (hr {:style (merge default-style border-style)}))))))
```

This demo inserts borders among child elements. You can think of more.
