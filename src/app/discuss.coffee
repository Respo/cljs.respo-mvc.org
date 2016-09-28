
React = require 'react'
marked = require 'marked'

ui = require '../style/ui'
theme = require '../style/theme'
guide = require '../guide/index'

Footer = React.createFactory require('./footer')

{div} = React.DOM

styleContainer = {}

styleContent =
  width: '80%'
  margin: 'auto'

module.exports = React.createClass
  displayName: 'discuss'

  render: ->
    div style: styleContainer,
      div
        style: styleContent
        className: 'markdown-docs',
        dangerouslySetInnerHTML: {__html: marked(guide.discuss)}
      div style: {height: 120}
      Footer()
