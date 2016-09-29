
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
  color: 'white'
  cursor: 'pointer'
  textDecoration: 'none'

styleContainer =
  backgroundColor: theme.dark
  color: 'white'
  position: 'fixed'
  width: '100%'
  padding: 8
  justifyContent: 'space-between'

onRouteHome = (event) ->
  event.preventDefault()
  recorder.dispatch 'router/nav', '/'
  tracking.event 'router', '/'

onRouteGuide = (event) ->
  event.preventDefault()
  recorder.dispatch 'router/nav', "/guide/why-respo.html"
  tracking.event 'router', "/guide/why-respo.html"

onRouteDocs = (event) ->
  event.preventDefault()
  recorder.dispatch 'router/nav', "/docs/overview.html"
  tracking.event 'router', "/docs/overview.html"

onRouteDiscuss = (event) ->
  event.preventDefault()
  recorder.dispatch 'router/nav', '/discuss.html'
  tracking.event 'router', '/discuss.html'

onOpenGitHub = (event) ->
  event.preventDefault()
  window.open 'http://github.com/Respo'
  tracking.event 'open-link', target.href

module.exports = React.createClass
  displayName: 'navbar'

  render: ->
    div style: (ui.merge ui.row, styleContainer),
      div style: ui.row,
        a style: styleLink, onClick: onRouteHome, href: '/',
          'Respo'
        div style: styleSpace
        a style: styleLink, onClick: onRouteGuide, href: '/guide/why-respo.html',
          'Guide'
        div style: styleSpace
        a style: styleLink, onClick: onRouteDocs, href: '/docs/overview.html',
          'API docs'
        div style: styleSpace
        a style: styleLink, onClick: onRouteDiscuss, href: '/discuss.html',
          'Discuss'
      div style: ui.row,
        a
          style: widget.brightLink, target: '_blanck'
          href: 'http://github.com/Respo', onClick: onOpenGitHub,
          'GitHub'
