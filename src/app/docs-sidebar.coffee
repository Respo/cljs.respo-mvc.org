
hsl = require 'hsl'
React = require 'react'
recorder = require 'actions-in-recorder'
Immutable = require 'immutable'

ui = require '../style/ui'
theme = require '../style/theme'

{div} = React.DOM

styleContainer =
  padding: 16
  position: 'fixed'
  top: 80
  bottom: 0
  overflow: 'auto'
  width: 240

styleGroup = {}

styleEntry =
  fontFamily: 'Menlo, Consolas, Ubuntu mono, monospace'
  fontSize: 12
  padding: '0 8px'
  cursor: "pointer"

styleOverview =
  cursor: 'pointer'

styleName =
  color: hsl 0, 0, 80
  fontFamily: styleEntry.fontFamily
  fontSize: 12

onRoute = (path) -> (event) ->
  recorder.dispatch 'router/nav', "/docs/#{path}.html"

renderEntry = (name) ->
  div style: styleEntry, onClick: (onRoute name),
    name

module.exports = React.createClass
  displayName: 'docs'

  render: ->
    div style: styleContainer,
      div style: styleOverview, onClick: (onRoute 'overview'), 'Overview'
      div style: styleGroup,
        div style: styleName, 'respo.alias'
        renderEntry 'div'
        renderEntry 'create-comp'
        renderEntry 'create-element'
      div style: styleGroup,
        div style: styleName, 'respo.comp.text'
        renderEntry 'comp-text'
      div style: styleGroup,
        div style: styleName, 'respo.comp.space'
        renderEntry 'comp-space'
      div style: styleGroup,
        div style: styleName, 'respo.comp.debug'
        renderEntry 'comp-debug'
      div style: styleGroup,
        div style: styleName, 'respo.core'
        renderEntry 'clear-cache!'
        renderEntry 'falsify-stage!'
      div style: styleGroup,
        div style: styleName, 'respo.render.static-html'
        renderEntry 'make-string'
        renderEntry 'make-html'
      div style: styleGroup,
        div style: styleName, 'respo.render.expander'
        renderEntry 'render-app'
      div style: styleGroup,
        div style: styleName, 'respo.util.format'
        renderEntry 'purify-element'
        renderEntry 'mute-element'
      div style: styleGroup,
        div style: styleName, 'respo.render.differ'
        renderEntry 'find-element-diffs'
      div style: styleGroup,
        div style: styleName, 'respo.render.patcher'
        renderEntry 'apply-dom-changes'
      div style: styleGroup,
        div style: styleName, 'respo.controller.client'
        renderEntry 'initialize-instance'
        renderEntry 'activate-instance'
        renderEntry 'patch-instance'
        renderEntry 'release-instance'
      div style: styleGroup,
        div style: styleName, 'respo.controller.deliver'
        renderEntry 'build-deliver-event'
        renderEntry 'mutate-factory'
