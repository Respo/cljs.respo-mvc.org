
## DOM elements

An element is defined like this with [`create-element`](/docs/create-element.html):

```clojure
(defn a [props & children] (create-element :a props children))
```

And for example an element defined like this:

```clojure
(input
  {:attrs {:placeholder "Pick a name, and hit Enter"}
   :event {:keydown (on-keydown mutate!)}
   :style {:line-height 2
           :width "100%"}})
```

might be rendered to an element with events bound:

```xml
<input placeholder="Pick a name, and hit Enter"
       style="line-height:2;width:100%;">
```

Some of the frequently used elements are defined in `respo.alias`:

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

Some are not, but you can create them very quickly with [`create-element`](/docs/create-element.html).

Since `meta` is a Clojure function, `meta'` is used here.
