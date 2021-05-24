> Newer version of Respo has been moved to [calcit-js](https://github.com/Respo/respo.calcit). This page is deprecating.

Create a element:

```clojure
(div {:class-name "demo"
      :style {:color :red
              :font-size 16
              :font-family "Josefin Sans"}
      :on-click (fn [event dispatch! mutate!])})

; respo.core/div
```

Nested elements:

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

To define components, use `defcomp`, which is a cacro:

```clojure
(defcomp comp-demo [p1 p2]
  (div {}
    (<> p1)
    (<> p2)))

(comp-demo :a :b)

; respo.core/defcomp
```

Use `render!` to mount a component. It also handles re-rendering if mounting already happened.

```clojure
(defonce *store (atom {}))
(defn dispatch! [op op-data] (swap! *store assoc :a 1))

(render! mount-target (comp-container @*store) dispatch!)

; respo.core/render!
```

To hot replace app code, use `render!` function. `clear-cache!` for resetting internal rendering caches:

```clojure
(defn reload! []
  (clear-cache!)
  (render! mount-target (comp-container @*store) dispatch!))

; respo.core/clear-cache!
```

### Adding Effects

Define effects with `defeffect` macro. You may also compare arguments with old one to decide what to do:

```clojure
(defeffect effect-focus [a] [action el at-place?]
  (when (= :mount action)
    (.focus (.querySelector el "input"))))
```

Pass component results in a vector with effects defined:

```clojure
(defcomp comp-draft [data]
  [(effect-focus data)
   (div {}
      (input {})))]
```

The effect will be called with action in `:mount` `:before-update` `:update` and `unmount`.
Respo would compare `[data]` passed to `effect-focus` with old arguments and updates will be called when they change.

### States Management

At component level, use it with an explicit `states` parameter passed from root states.
The idea is based on cursors, but implemented differently:

```clojure
(defcomp comp-a [states]
  (let [cursor (:cursor states)
        state (or (:data states) {:a 1})]
    (div {}
      (<> (str "count" (:a state)))
      (button {:inner-text "Add"
               :on-click (fn [e d!]
                          (d! cursor (update state :a inc)))}))))
```

Respo uses an atom to maintain global states for smooth experiences of hot code swapping, might look tedious though:

```clojure
(defonce *store (atom {}))
(defn dispatch! [op op-data] (swap! *store assoc :a 1))

(add-watch *store :changes
           (fn []
               (render! mount-target (comp-container @*store) dispatch!)))
```

Read more about `cursor` and `>>` in the docs. at [component-level states](https://github.com/Respo/respo/wiki/component-states).

### Ecosystem

During developing Respo, a bunch of libraries are added:

* [ui](https://github.com/Respo/respo-ui) -- basic UI styles collected based on Flexbox
* [markdown](https://github.com/Respo/respo-markdown) -- subset Markdown syntax rendering to virtual DOM
* [message](https://github.com/Respo/respo-message) -- displaying message on top-right corner
* [feather](https://github.com/Respo/respo-feather) -- icons library of feather
* [alerts](https://github.com/Respo/alerts) -- replacing alert/confirm/prompt components
* [router](https://github.com/Respo/respo-router) -- HTML5 router library decoupled from view part
* [reel](https://github.com/Respo/reel) -- time travelling developing tool
* [value](https://github.com/Respo/respo-value) -- to display collections
* [form](https://github.com/Respo/form) -- simple form library
* [notifier](https://github.com/Respo/notifier) -- notification notifier

You may also try [Reacher](https://github.com/Respo/reacher) which is a React wrapper.

### Try Respo

Now it's your turn to read Guide and try Respo:

* [Read guides](https://github.com/Respo/respo/wiki)
* [Browse examples](https://github.com/Respo/respo-examples/)
* [Try minimal Respo app by your own](https://github.com/Respo/minimal-respo)

For Advanced developers, probably the best way to understand Respo is to [read code of how the author is using it](https://github.com/mvc-works/calcit-workflow/blob/master/src/app/main.cljs). [Contact me on Twitter](https://twitter.com/jiyinyiyong) anytime if you got questions.

[Send feedbacks on issues](https://github.com/Respo/respo.site/issues/1) if you want to improve this page. [Old versions](https://gist.github.com/jiyinyiyong/008a2be624a351a11d1ca44f809963a3).
