"Create elements in Clojure syntax:

```clojure
(div {:class-name "demo"
      :style {:color :red
              :font-size 16
              :font-family "Josefin Sans"}
      :on-click (fn [event dispatch! mutate!])})

; respo.core/div
```

Nest child elements:

```clojure
(div {}
  (span {})
  (div {}))

; respo.core/span
```

Add text nodes:

```clojure
(div {}
  (<> "text")
  (<> "text with style" {:color :red}))

; respo.core/<>
```

### Create Components

To define components, use `defcomp`, it's a Macro:

```clojure
(defcomp comp-demo [p1 p2]
  (div {}
    (<> p1)
    (<> p2)))

(comp-demo :a :b)

; respo.core/defcomp
```

Use `render!` to mount a component. It also handles re-rendered if mounting already happened.

```clojure
(defonce *store (atom {}))
(defn dispatch! [op op-data] (swap! *store assoc :a 1))

(render! mount-target (comp-container @*store) dispatch!)

; respo.core/render!
```

To hot replace app, use `render!` function. `clear-cache!` is for clearing internal rendering caches:

```clojure
(defn reload! []
  (clear-cache!)
  (render! mount-target (comp-container @*store) dispatch!))

; respo.core/clear-cache!
```

### States Management

Respo uses an Atom to maintain global states. Global states and "Single Source of Truth" are prefered:

```clojure
(defonce *store (atom {}))
(defn dispatch! [op op-data] (swap! *store assoc :a 1))

(add-watch *store :changes
           (fn []
               (render! mount-target (comp-container @*store) dispatch!)))
```

Respo has supports for [component-level states](https://github.com/Respo/respo/wiki/component-states). But is designed in an awkward syntax in order to make sure it's consistent with "Single Source of Truth". Read about `mutate!` and `cursor->` in the docs.

### Ecosystem

During developing Respo, a bunch of libraries are added:

* [hsl](https://github.com/mvc-works/hsl.clj) -- a function that returns color in string
* [ui](https://github.com/Respo/respo-ui) -- basic UI styles collected based on Flexbox
* [markdown](https://github.com/Respo/respo-markdown) -- subset Markdown syntax rendering to virtual DOM
* [router](https://github.com/Respo/respo-router) -- HTML5 router library decoupled from view part
* [tiny-app](https://github.com/Respo/tiny-app) -- a macro for initialising app in a easy way
* [reel](https://github.com/Respo/reel) -- time travelling developing tool
* [value](https://github.com/Respo/respo-value) -- to display collections
* [message](https://github.com/Respo/respo-message) -- displaying message on top-right corner
* [alerts](https://github.com/Respo/alerts) -- replacing alert/confirm/prompt components
* [global-popup](https://github.com/Respo/global-popup) and [inflow-popup](https://github.com/Respo/inflow-popup)

### Try Respo

Now it's your turn to read Guide and try Respo:

* [Read guides](https://github.com/Respo/respo/wiki)
* [Browse examples](https://github.com/Respo/respo-examples/)
* [Try minimal Respo app by your own](https://github.com/Respo/minimal-respo)

An easy way to use Respo is using [tiny-app](https://github.com/Respo/tiny-app/). It's a macro that handles dirty works in passing in configurations.

For Advanced developers, probably the best way to understand Respo is to [read code of how the author is using it](https://github.com/mvc-works/calcit-workflow/blob/master/src/app/main.cljs). [Contact me on Twitter](https://twitter.com/jiyinyiyong) anytime if you got questions.

[Send feedbacks on issues](https://github.com/Respo/respo.site/issues/1) if you want to improve this page. [Old versions](https://gist.github.com/jiyinyiyong/008a2be624a351a11d1ca44f809963a3).
