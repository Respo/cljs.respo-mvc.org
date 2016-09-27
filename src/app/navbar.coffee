
hsl = require 'hsl'
React = require 'react'

ui = require '../style/ui'
theme = require '../style/theme'

{div} = React.DOM

styleContainer =
  backgroundColor: theme.dark
  color: 'white'
  position: 'fixed'
  width: '100%'
  padding: 8

module.exports = React.createClass
  displayName: 'navbar'

  render: ->
    div style: styleContainer,
      div {}, 'Respo'
      div {}
