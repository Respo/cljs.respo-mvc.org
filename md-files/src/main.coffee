
hljs = require 'highlight.js'
React = require 'react'
marked = require 'marked'
recorder = require 'actions-in-recorder'
ReactDOM = require 'react-dom'
Immutable = require 'immutable'
{parseAddress} = require 'router-as-view/lib/path'

if __DEV__
  installDevtools = require 'immutable-devtools'

schema = require './schema'
routes = require './routes'
updater = require './updater'

Container = React.createFactory require('./app/container')

require './main.css'

router = parseAddress window._initialState.path, routes
initialStore = schema.store.set 'router', router

render = (core) ->
  mountPoint = document.querySelector('#app')
  ReactDOM.render (Container store: core.get('store')), mountPoint

main = ->
  if __DEV__
    installDevtools Immutable
  marked.setOptions
    highlight: (code, lang, callback) ->
      result = hljs.highlightAuto(code.trim(), [lang])
      result.value

  recorder.setup
    initial: initialStore
    updater: updater

  recorder.request render
  recorder.subscribe render

  console.log 'loaded'

window.onload = main

if module.hot
  module.hot.accept './app/container', ->
    Container = React.createFactory require('./app/container')
    recorder.request render
