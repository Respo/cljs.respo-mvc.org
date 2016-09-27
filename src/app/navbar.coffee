
hsl = require 'hsl'
React = require 'react'
recorder = require 'actions-in-recorder'
Immutable = require 'immutable'

ui = require '../style/ui'
theme = require '../style/theme'

{div} = React.DOM

styleSpace =
  width: 16

styleLink =
  cursor: 'pointer'

styleContainer =
  backgroundColor: theme.dark
  color: 'white'
  position: 'fixed'
  width: '100%'
  padding: 8

onRouteHome = ->
  info = Immutable.fromJS
    name: 'home'
    router: null
    data: {}
    query: {}
  recorder.dispatch 'router/go', info

onRouteGuide = ->
  info = Immutable.fromJS
    name: 'guide'
    router: null
    data:
      entry: 'tutorial'
    query: {}
  recorder.dispatch 'router/go', info

onRouteDocs = ->
  info = Immutable.fromJS
    name: 'docs'
    data:
      post: 'overview'
    query: {}
    router: null
  recorder.dispatch 'router/go', info

module.exports = React.createClass
  displayName: 'navbar'

  render: ->
    div style: (ui.merge ui.row, styleContainer),
      div style: styleLink, onClick: onRouteHome,
        'Respo'
      div style: styleSpace
      div style: styleLink, onClick: onRouteGuide,
        'Guide'
      div style: styleSpace
      div style: styleLink, onClick: onRouteDocs,
        'API docs'
