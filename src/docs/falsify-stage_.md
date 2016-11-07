
`falsify-state!`
----

This one is complicated. I wrote [a long post before trying to explain this new feature][progressive].
The code looks like this:

[progressive]: https://medium.com/@jiyinyiyong/progressive-server-side-rendering-that-we-may-need-8980e7c4d61a

```clojure
(if (not (empty? ssr-stages))
  (let [target (.querySelector js/document "#app")]
    (falsify-stage!
      target
      (render-element
        (comp-container @store-ref ssr-stages)
        states-ref)
      dispatch!)))
```

The job of `falsify-state!` function is to simulate a virtual DOM of currently rendered HTML from server, so that the followed virtual DOM rendering steps can run a little easier.
