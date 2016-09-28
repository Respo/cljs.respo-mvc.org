
React = require 'react'
recorder = require 'actions-in-recorder'

ui = require '../style/ui'
theme = require '../style/theme'
routes = require '../routes'

Addressbar = React.createFactory require('router-as-view')
Navbar = React.createFactory require('./navbar')
Facade = React.createFactory require('./facade')
Guide = React.createFactory require('./guide')
Docs = React.createFactory require('./docs')
Discuss = React.createFactory require('./discuss')

{div} = React.DOM

module.exports = React.createClass
  displayName: 'app-container'

  onPopstate: (info, event) ->
    recorder.dispatch 'router/go', info

  render: ->
    router = @props.store.get('router')

    div className: 'app-container', style: (ui.merge ui.global),
      Navbar()
      div style: {height: 64}
      @renderBody router
      Addressbar
        route: router
        rules: routes
        inHash: true
        onPopstate: @onPopstate
        skipRendering: false

  renderBody: (router) ->
    switch router.get('name')
      when 'home' then Facade()
      when 'discuss' then Discuss()
      when 'docs' then Docs router: router
      when 'guide' then Guide router: router
      else '404'
