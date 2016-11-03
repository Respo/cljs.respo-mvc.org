
hsl = require 'hsl'
hljs = require 'highlight.js'
React = require 'react'

ui = require '../style/ui'
theme = require '../style/theme'

componentSnippet = require '../snippets/component.cljs'
modelSnippet = require '../snippets/model.cljs'
viewSnippet = require '../snippets/view.cljs'
controllerSnippet = require '../snippets/controller.cljs'
inlineStylesSnippet = require '../snippets/inline-styles.cljs'
hotSwappingSnippet = require '../snippets/hot-swapping.cljs'
statesSnippet = require '../snippets/states.cljs'

{div, pre} = React.DOM

styleContainer =
  alignItems: 'center'

styleRow =
  marginTop: 32

styleCode =
  padding: 8
  backgroundColor: theme.blockCodeBg
  color: theme.blockCodeText
  minWidth: 600
  lineHeight: 1.6
  fontFamily: 'Menlo, Consolas, Ubuntu mono, monospace'
  boxShadow: '0 0 2px hsla(0,0%,0%,0.3)'

styleFeature =
  width: 200
  padding: 8
  fontSize: 16

transformCode = (code) ->
  result = hljs.highlightAuto(code.trim(), ['clojure'])
  pre
    style: styleCode
    dangerouslySetInnerHTML:
      __html: result.value

module.exports = React.createClass
  displayName: 'features'

  renderFeature: (feature, code) ->
    div style: (ui.merge ui.row, styleRow),
      div style: styleFeature, feature
      transformCode code

  render: ->

    div style: (ui.merge ui.column, styleContainer),
      @renderFeature 'Component nesting', componentSnippet
      @renderFeature 'Store initialization', modelSnippet
      @renderFeature 'Component mounting', viewSnippet
      @renderFeature 'Event handling', controllerSnippet
      @renderFeature 'Inline Styles', inlineStylesSnippet
      @renderFeature 'Hot Swapping', hotSwappingSnippet
      @renderFeature 'Component States', statesSnippet
