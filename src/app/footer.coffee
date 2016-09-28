
React = require 'react'

ui = require '../style/ui'
theme = require '../style/theme'
widget = require '../style/widget'

{div, a} = React.DOM

styleContainer =
  backgroundColor: theme.dark
  padding: 16
  color: 'white'

module.exports = React.createClass
  displayName: 'footer'

  render: ->
    div style: styleContainer,
      a style: widget.brightLink, target: '_blanck', href: 'http://tiye.me/',
        'By 题叶'
