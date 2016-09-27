
hsl = require 'hsl'
React = require 'react'

ui = require '../style/ui'
theme = require '../style/theme'

logo = require '../images/respo.png'

{div} = React.DOM

styleContainer =
  paddingTop: 80

styleBanner =
  alignItems: 'center'
  justifyContent: 'center'

styleLogo =
  backgroundImage: "url(#{logo})"
  width: 64
  height: 64
  backgroundSize: 'cover'

styleSpace =
  width: 16

styleText =
  fontSize: 32
  color: theme.dark
  fontWeight: 'lighter'

module.exports = React.createClass
  displayName: 'facade'

  render: ->
    div style: styleContainer,
      div style: (ui.merge ui.row, styleBanner),
        div style: styleLogo
        div style: styleSpace
        div style: styleText, 'Respo'
