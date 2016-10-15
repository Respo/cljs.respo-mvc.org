
React = require 'react'

ui = require '../style/ui'
theme = require '../style/theme'
widget = require '../style/widget'

{div, a} = React.DOM

styleContainer =
  backgroundColor: theme.barBg
  padding: 16
  color: theme.text

module.exports = React.createClass
  displayName: 'footer'

  render: ->
    div style: styleContainer,
      a style: widget.brightText, target: '_blanck', href: 'http://tiye.me/',
        'By 题叶'
