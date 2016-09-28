
React = require 'react'

ui = require '../style/ui'
theme = require '../style/theme'

{div} = React.DOM

module.exports = React.createClass
  displayName: 'guide-sidebar'

  render: ->
    div {},
      'sidebar'
