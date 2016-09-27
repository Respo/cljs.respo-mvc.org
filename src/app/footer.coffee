
React = require 'react'

ui = require '../style/ui'
theme = require '../style/theme'

{div} = React.DOM

styleContainer =
  backgroundColor: theme.dark
  padding: 16
  color: 'white'

module.exports = React.createClass
  displayName: 'footer'

  render: ->
    div style: styleContainer,
      div {}, 'container'
