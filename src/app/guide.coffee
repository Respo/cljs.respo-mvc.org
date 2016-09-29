
React = require 'react'
marked = require 'marked'
recorder = require 'actions-in-recorder'

ui = require '../style/ui'
link = require '../util/link'
theme = require '../style/theme'
guide = require '../guide/index'

GuideSidebar = React.createFactory require('./guide-sidebar')

{div} = React.DOM

styleContent = {}

module.exports = React.createClass
  displayName: 'guide'

  renderGuide: ->
    guideName = @props.router.getIn(['data', 'entry']).replace('.html', '')
    content = switch guideName
      when 'tutorial' then guide.tutorial
      when 'dom-elements' then guide.domElements
      when 'dom-events' then guide.domEvents
      when 'dom-properties' then guide.domProperties
      when 'environment' then guide.environment
      when 'hot-swapping' then guide.hotSwapping
      when 'styles' then guide.styles
      when 'trouble-shooting' then guide.troubleShooting
      when 'pros-and-cons' then guide.prosAndCons
      when 'why-respo' then guide.whyRespo
      when 'render-list' then guide.renderList
      when 'base-components' then guide.baseComponents
      else '404'

    div
      style: styleContent,
      className: 'markdown-docs'
      dangerouslySetInnerHTML: {__html: marked(content)}
      onClick: link.onClick

  render: ->
    div style: (ui.row),
      GuideSidebar router: @props.router
      @renderGuide()
