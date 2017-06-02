
## `comp-space`

This is the component to add spaces.
It decouples whitespaces from margins of elements, so I consider it a good practice.

```clojure
(comp-space 8 nil) ; for horizontal space
(comp-space nil 8) ; for vertical space
```

Make sure that one of them left `nil` so the component may fill it.

It's also okay to use strings:

```clojure
(comp-space nil "8px") ; for vertical space
```

The bad aspect is every `<div>` actually costs.
And margin is still an alternative solution.

> Notice, marge is more performant than an extra element.
  Make you choice when you feel your app is slower.
