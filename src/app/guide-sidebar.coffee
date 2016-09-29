
hsl = require 'hsl'
React = require 'react'
recorder = require 'actions-in-recorder'
Immutable = require 'immutable'

ui = require '../style/ui'
theme = require '../style/theme'
tracking = require '../util/tracking'

{div, a} = React.DOM

styleContainer =
  padding: 8
  minWidth: 240

styleEntry =
  cursor: 'pointer'
  color: theme.blue
  display: 'block'
  textDecoration: 'none'

onRouteGuide = (path) -> (event) ->
  event.preventDefault()
  recorder.dispatch 'router/nav', "guide/#{path}.html"
  tracking.event 'router', "/guide/#{path}.html"

renderEntry = (path, name, focus) ->
  focusedPath = focus.replace('.html', '')
  a
    href: "/guide/#{path}.html"
    style: ui.merge styleEntry,
      if path is focusedPath then color: theme.cyan
    onClick: (onRouteGuide path), name

module.exports = React.createClass
  displayName: 'guide-sidebar'

  render: ->
    entry = @props.router.getIn ['data', 'entry']

    div style: styleContainer,
      renderEntry 'why-respo', 'Why Respo?', entry
      renderEntry 'pros-and-cons', 'Pros and Cons', entry
      renderEntry 'environment', 'Envionment', entry
      renderEntry 'tutorial', 'Tutorial', entry
      renderEntry 'dom-elements', 'DOM elements', entry
      renderEntry 'dom-properties', 'DOM properties', entry
      renderEntry 'dom-events', 'DOM events', entry
      renderEntry 'styles', 'Styles', entry
      renderEntry 'virtual-dom', 'Virtual DOM', entry
      renderEntry 'render-list', 'Render List', entry
      renderEntry 'hot-swapping', 'Hot swapping', entry
      renderEntry 'base-components', 'Base components', entry
      renderEntry 'trouble-shooting', 'Trouble shooting', entry
