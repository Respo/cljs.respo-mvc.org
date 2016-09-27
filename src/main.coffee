
React = require 'react'
recorder = require 'actions-in-recorder'
ReactDOM = require 'react-dom'
Immutable = require 'immutable'
installDevtools = require 'immutable-devtools'
{parseAddress} = require 'router-as-view/lib/path'

schema = require './schema'
routes = require './routes'
updater = require './updater'

Container = React.createFactory require('./app/container')

require './main.css'

initialAddress = location.hash.substr 1
router = parseAddress initialAddress, routes
initialStore = schema.store.set 'router', router

render = (core) ->
  mountPoint = document.querySelector('#app')
  ReactDOM.render (Container store: core.get('store')), mountPoint

main = ->
  installDevtools Immutable

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
