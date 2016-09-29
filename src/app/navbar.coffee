
hsl = require 'hsl'
React = require 'react'
recorder = require 'actions-in-recorder'
Immutable = require 'immutable'

ui = require '../style/ui'
theme = require '../style/theme'
widget = require '../style/widget'
tracking = require '../util/tracking'

{div, a} = React.DOM

styleSpace =
  width: 32

styleLink =
  cursor: 'pointer'

styleContainer =
  backgroundColor: theme.dark
  color: 'white'
  position: 'fixed'
  width: '100%'
  padding: 8
  justifyContent: 'space-between'

onRouteHome = ->
  recorder.dispatch 'router/nav', '/'
  tracking.event 'router', '/'

onRouteGuide = ->
  recorder.dispatch 'router/nav', "/guide/why-respo.html"
  tracking.event 'router', "/guide/why-respo.html"

onRouteDocs = ->
  recorder.dispatch 'router/nav', "/docs/overview.html"
  tracking.event 'router', "/docs/overview.html"

onRouteDiscuss = ->
  recorder.dispatch 'router/nav', '/discuss.html'
  tracking.event 'router', '/discuss.html'

module.exports = React.createClass
  displayName: 'navbar'

  render: ->
    div style: (ui.merge ui.row, styleContainer),
      div style: ui.row,
        div style: styleLink, onClick: onRouteHome,
          'Respo'
        div style: styleSpace
        div style: styleLink, onClick: onRouteGuide,
          'Guide'
        div style: styleSpace
        div style: styleLink, onClick: onRouteDocs,
          'API docs'
        div style: styleSpace
        div style: styleLink, onClick: onRouteDiscuss,
          'Discuss'
      div style: ui.row,
        a style: widget.brightLink, target: '_blanck', href: 'http://github.com/Respo',
          'GitHub'
