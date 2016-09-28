
React = require 'react'

ui = require '../style/ui'
theme = require '../style/theme'

GuideSidebar = React.createFactory require('./guide-sidebar')

{div} = React.DOM

module.exports = React.createClass
  displayName: 'guide'

  render: ->
    div {},
      GuideSidebar()
      'guide'
