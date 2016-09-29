
DOM elements
----

An element is defined like this with [`create-element`](#/docs/create-element.html):

```clojure
(defn a [props & children] (create-element :a props children))
```

And for example an element defined like this:

```cloure
(input
  {:attrs {:placeholder "Pick a name, and hit Enter"}
   :event {:keydown (on-keydown mutate!)}
   :style {:line-height 2
           :width "100%"}})
```

might be rendered to an element that looks like(`data-coord` may vary):

```html
<input placeholder="Pick a name, and hit Enter"
       data-coord="[:container 5 :reply 0]"
       data-event="#{:keydown}"
       style="line-height:2;width:100%;">
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
