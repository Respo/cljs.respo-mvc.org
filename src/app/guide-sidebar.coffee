
React = require 'react'
recorder = require 'actions-in-recorder'
Immutable = require 'immutable'

ui = require '../style/ui'
theme = require '../style/theme'

{div} = React.DOM

styleContainer =
  padding: 8
  minWidth: 240

styleEntry =
  cursor: 'pointer'

onRouteGuide = (path) -> (event) ->
  info = Immutable.fromJS
    name: 'guide'
    data:
      entry: path
    query: {}
    router: null
  recorder.dispatch 'router/go', info

renderEntry = (path, name) ->
  div style: styleEntry, onClick: (onRouteGuide path), name

module.exports = React.createClass
  displayName: 'guide-sidebar'

  render: ->
    div style: styleContainer,
      renderEntry 'why-respo', 'Why Respo?'
      renderEntry 'environment', 'Envionment'
      renderEntry 'tutorial', 'Tutorial'
      renderEntry 'dom-elements', 'DOM elements'
      renderEntry 'dom-properties', 'DOM properties'
      renderEntry 'dom-events', 'DOM events'
      renderEntry 'styles', 'Styles'
      renderEntry 'render-list', 'Render List'
      renderEntry 'hot-swapping', 'Hot swapping'
      renderEntry 'trouble-shooting', 'Trouble shooting'
      renderEntry 'pros-and-cons', 'Pros and Cons'
