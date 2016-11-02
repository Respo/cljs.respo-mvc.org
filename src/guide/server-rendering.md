
Server rendering
----

Virtual DOM can be rendered on a server.
Most of the Respo code is in `.cljc` files so it can be rendered with JVM.
But I suggest using [Planck][planck] to render it with JavaScriptCore on Mac or Linux.
It's also okay if you want to render with Node.js .

[planck]: http://planck-repl.org/

In JVM, static checking might give error if JavaScript globals are used.
It throws error to if there's only `.cljs` files.
It would be easier running with Node.js or JavaScriptCode.

And here is a demo rendering with Planck: https://github.com/Respo/ssr-stages

To load the dependencies into Planck, try these commands and use `-i` to run script:

```clojure
export boot_deps=`boot show -c`
planck -c $boot_deps:src/ -i render.cljs
```

In `render.cljs` you need to import the component and render it to HTML.
[`make-string`](/docs/make-string.html) is the function to render HTML.

[`falsify-stage!`](/docs/falsify-stage!.html) is also useful to make first screen look smoother, make sure it's called before `render!`.
Notice that when rendering on server, events are not bound,
use [`mute-element`](/docs/mute-element.html) to remove events before rendering.

Without [`falsify-stage!`](/docs/falsify-stage!.html), [`render!`](/docs/render!.html) function will remove existing DOM and mount the whole tree at first running.
