
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
  backgroundColor: hsl(0,0,96)
  minWidth: 600
  lineHeight: 1.6
  fontFamily: 'Menlo, Consolas, Ubuntu mono, monospace'

styleFeature =
  width: 240
  padding: 8

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
      @renderFeature 'Components', componentSnippet
      @renderFeature 'Model', modelSnippet
      @renderFeature 'View', viewSnippet
      @renderFeature 'Controller', controllerSnippet
      @renderFeature 'Inline Styles', inlineStylesSnippet
      @renderFeature 'Hot Swapping', hotSwappingSnippet
      @renderFeature 'Component States', statesSnippet
