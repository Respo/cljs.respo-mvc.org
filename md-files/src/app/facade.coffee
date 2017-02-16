
hsl = require 'hsl'
React = require 'react'

ui = require '../style/ui'
theme = require '../style/theme'

Footer = React.createFactory require('./footer')
Features = React.createFactory require('./features')
FeatureList = React.createFactory require('./feature-list')

{div, a, img} = React.DOM

styleContainer = {}

styleBanner =
  alignItems: 'center'
  justifyContent: 'center'

styleLogo =
  backgroundImage: "url(https://avatars3.githubusercontent.com/u/20469468?v=3&s=200)"
  width: 64
  height: 64
  backgroundSize: 'cover'

styleSpace =
  width: 16

styleVerticalSpace =
  height: 160

styleText =
  fontSize: 32
  color: theme.text
  fontWeight: 'lighter'

styleDescription =
  fontSize: 16
  textAlign: 'center'
  color: theme.description

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
        'A front-end MVC library in ClojureScript'
        div style: {width: 8, display: 'inline-block'}
        a href: 'https://clojars.org/respo', style: {verticalAlign: 'middle'},
          img src: 'https://img.shields.io/clojars/v/respo.svg'
      FeatureList()
      Features()
      div style: styleVerticalSpace
      Footer()
