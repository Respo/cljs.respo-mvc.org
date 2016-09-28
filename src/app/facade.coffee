
hsl = require 'hsl'
React = require 'react'

ui = require '../style/ui'
theme = require '../style/theme'

logo = require '../images/respo.png'

Footer = React.createFactory require('./footer')
Features = React.createFactory require('./features')

{div} = React.DOM

styleContainer = {}

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

styleVerticalSpace =
  height: 160

styleText =
  fontSize: 32
  color: theme.dark
  fontWeight: 'lighter'

styleDescription =
  fontSize: 16
  textAlign: 'center'
  color: theme.blue

module.exports = React.createClass
  displayName: 'facade'

  render: ->
    div style: styleContainer,
      div style: (ui.merge ui.row, styleBanner),
        div style: styleLogo
        div style: styleSpace
        div style: styleText, 'Respo'
      div style: {height: 16}
      div style: styleDescription,
        'Respo is an MVC library in ClojureScript'
      Features()
      div style: styleVerticalSpace
      Footer()
