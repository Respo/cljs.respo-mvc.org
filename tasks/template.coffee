
fs = require 'fs'
hljs = require 'highlight.js'
stir = require 'stir-template'
React = require 'react'
marked = require 'marked'
ReactDOM = require 'react-dom/server'
recorder = require 'actions-in-recorder'
{parseAddress} = require 'router-as-view/lib/path'

routes = require '../src/routes'
schema = require '../src/schema'
settings = require './settings'
resource = require './resource'

require.extensions['.md'] = (module, filename) ->
  module.exports = fs.readFileSync filename, 'utf8'

require.extensions['.cljs'] = (module, filename) ->
  module.exports = fs.readFileSync filename, 'utf8'

Container = React.createFactory require('../src/app/container')

{html, head, title, meta, link, script, body, div, style} = stir

logoUrl = 'https://avatars3.githubusercontent.com/u/20469468?v=3&s=200'

marked.setOptions
  highlight: (code, lang, callback) ->
    result = hljs.highlightAuto(code.trim(), [lang])
    result.value

module.exports = (env, path) ->
  config = settings.get(env)
  assets = resource.get(config)
  gaHtml = fs.readFileSync './tasks/ga.html', 'utf8'

  router = parseAddress path, routes
  initialStore = schema.store.set 'router', router

  recorder.setup initial: initialStore

  stir.render stir.doctype(),
    html {},
      head {},
        title {}, 'Respo: an MVC library in ClojureScript'
        meta charset: 'utf-8'
        meta name: 'description', content: 'an MVC library learning from React.js'
        link rel: 'icon', href: logoUrl
        if assets.style?
          link rel: 'stylesheet', href: assets.style
        script {},
          "window._initialState = #{JSON.stringify(path: path)}"
        script {},
          "window._config = #{JSON.stringify(config)}"
        script src: assets.vendor, defer: true
        script src: assets.main, defer: true
        style {}, 'body * {box-sizing: border-box;}'
        if config.useGa then gaHtml
    body style: 'margin: 0;',
      div id: 'app',
        ReactDOM.renderToString (Container store: recorder.getCore().get('store'))

