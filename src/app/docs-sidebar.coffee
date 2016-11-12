
hsl = require 'hsl'
React = require 'react'
recorder = require 'actions-in-recorder'
Immutable = require 'immutable'

ui = require '../style/ui'
theme = require '../style/theme'
tracking = require '../util/tracking'

{div, a} = React.DOM

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
  padding: '0 16px'
  cursor: "pointer"
  color: theme.sideLink
  display: 'block'
  textDecoration: 'none'

styleOverview =
  cursor: 'pointer'
  color: theme.sideLink

styleName =
  color: hsl 0, 0, 80
  fontFamily: styleEntry.fontFamily
  fontSize: 12

onRoute = (path) -> (event) ->
  event.preventDefault()
  recorder.dispatch 'router/nav', "/docs/#{path}.html"
  tracking.event 'router', "/docs/#{path}.html"

renderEntry = (name, post) ->
  postName = post.replace '.html', ''

  a
    href: "/docs/#{name}.html"
    style: ui.merge styleEntry,
      if name is postName then color: theme.sideLinkActive
    onClick: (onRoute name)
    name

module.exports = React.createClass
  displayName: 'docs'

  render: ->
    post = @props.router.getIn ['data', 'post']

    div style: styleContainer,
      div
        style: ui.merge styleOverview,
          if post is 'overview.html' then color: theme.sideLinkActive
        onClick: (onRoute 'overview')
        'Overview'
      div style: styleGroup,
        div style: styleName, 'respo.alias'
        renderEntry 'div', post
        renderEntry 'create-comp', post
        renderEntry 'create-element', post
      div style: styleGroup,
        div style: styleName, 'respo.comp.text'
        renderEntry 'comp-text', post
      div style: styleGroup,
        div style: styleName, 'respo.comp.code'
        renderEntry 'comp-code', post
      div style: styleGroup,
        div style: styleName, 'respo.comp.space'
        renderEntry 'comp-space', post
      div style: styleGroup,
        div style: styleName, 'respo.comp.debug'
        renderEntry 'comp-debug', post
      div style: styleGroup,
        div style: styleName, 'respo.core'
        renderEntry 'clear-cache!', post
        renderEntry 'falsify-stage!', post
      div style: styleGroup,
        div style: styleName, 'respo.render.html'
        renderEntry 'make-string', post
        renderEntry 'make-html', post
      div style: styleGroup,
        div style: styleName, 'respo.render.expander'
        renderEntry 'render-app', post
      div style: styleGroup,
        div style: styleName, 'respo.util.format'
        renderEntry 'purify-element', post
        renderEntry 'mute-element', post
      div style: styleGroup,
        div style: styleName, 'respo.render.differ'
        renderEntry 'find-element-diffs', post
      div style: styleGroup,
        div style: styleName, 'respo.render.patcher'
        renderEntry 'apply-dom-changes', post
      div style: styleGroup,
        div style: styleName, 'respo.controller.client'
        renderEntry 'initialize-instance', post
        renderEntry 'activate-instance', post
        renderEntry 'patch-instance', post
        renderEntry 'release-instance', post
      div style: styleGroup,
        div style: styleName, 'respo.controller.deliver'
        renderEntry 'build-deliver-event', post
        renderEntry 'mutate-factory', post
