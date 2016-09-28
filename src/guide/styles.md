
Styles
----

Styles are represented in HashMap so it's very trival to extend with `merge` and `if`:

```clojure
(def style-a {:line-height 1.6
              :color (hsl 0 0 80)})

(def style-b
  (merge style-a
    {:font-size "16px"}
    (if (> size 0)
      {:font-weight "bold"})))
```

Also I prepared a function called [`hsl`](https://github.com/mvc-works/hsl.clj) as a helper function.

In Respo, style updates are defined with direct accessing to `el.style`:

```clojure
(defn add-style [target op]
  (let [style-name (dashed->camel (name (key op)))
        style-value (val op)]
    (aset (.-style target) style-name style-value)))

(defn rm-style [target op]
  (let [style-name (dashed->camel (name op))]
    (aset (.-style target) style-name nil)))

(defn replace-style [target op]
  (let [style-name (dashed->camel (name (key op)))
        style-value (val op)]
    (aset (.-style target) style-name style-value)))
```

For convenience, I collected my frequent used styles in a package called [respo-ui](https://github.com/Respo/respo-ui).
You can find more in the [source code][styles].

[styles]: https://github.com/Respo/respo-ui/blob/master/compiled/src/respo_ui/style.cljc

Actually there's one more solution to specify CSS content, the `<style>` element:

```clojure
(style {:attrs {:innerHTML ".demo {color: red;}"}})
```

It may look awkward but sometimes quite useful for pseudo classes.
