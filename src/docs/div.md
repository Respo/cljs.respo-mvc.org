
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

An element is defined like this with [`create-element`](#/docs/create-element/):

```clojure
(defn a [props & children] (create-element :a props children))
```

Some of the frequently used elements are already defined in `respo.alias`:

```clojure
a
body
br
button
canvas
code
footer
h1
h2
hr
head
header
html
img
input
link
meta'
p
pre
script
section
span
style
textarea
title
```

Some are not, but you can create them very quickly.

Since `meta` is a Clojure function, `meta'` is used here.
