
React = require 'react'

ui = require '../style/ui'
theme = require '../style/theme'

{div} = React.DOM

styleContainer =
  maxWidth: 1200
  margin: 'auto'
  marginTop: 40
  textAlign: 'center'

styleFeature =
  width: 320
  height: 48
  display: 'inline-block'
  margin: 'auto'
  backgroundColor: theme.showcaseBg
  color: theme.showcaseText
  padding: 8
  marginRight: 8
  marginBottom: 8

renderFeature = (content) ->
  div style: styleFeature, content

module.exports = React.createClass
  displayName: 'feature-list'

  render: ->
    div style: styleContainer,
      renderFeature 'Virtual DOM based solution'
      renderFeature 'Persistent data structure'
      renderFeature 'DSL powered by ClojureScript'
      renderFeature 'Smooth hot code swapping'
      renderFeature 'Inline styles with HashMaps'
      renderFeature 'Store abstraction with Atom'
