
React = require 'react'
recorder = require 'actions-in-recorder'

routes = require '../routes'

Addressbar = React.createFactory require('router-as-view')

{div} = React.DOM

module.exports = React.createClass
  displayName: 'app-container'

  onClick: ->
    recorder.dispatch 'inc', null

  onPopstate: (info, event) ->
    recorder.dispatch 'router/go', info

  render: ->
    div className: 'app-container', onClick: @onClick,
      'store'
      Addressbar
        route: @props.store.get('router')
        rules: routes
        inHash: true
        onPopstate: @onPopstate
        skipRendering: false
