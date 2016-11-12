
React = require 'react'
marked = require 'marked'

ui = require '../style/ui'
link = require '../util/link'
theme = require '../style/theme'
docs = require '../docs/index'

DocsSidebar = React.createFactory require('./docs-sidebar')

{div} = React.DOM

styleContent =
  paddingBottom: 240
  marginLeft: 240
  padding: 8

module.exports = React.createClass
  displayName: 'docs'

  render: ->
    div style: ui.row,
      DocsSidebar router: @props.router
      @renderDocsContent()

  renderDocsContent: ->
    docName = @props.router.getIn(['data', 'post']).replace('.html', '')
    content = switch docName
      when 'activate-instance' then docs.activateInstance
      when 'build-deliver-event' then docs.buildDeliverEvent
      when 'comp-debug' then docs.compDebug
      when 'comp-space' then docs.compDpace
      when 'comp-text' then docs.compText
      when 'comp-code' then docs.compCode
      when 'create-comp' then docs.createComp
      when 'create-element' then docs.createElement
      when 'element' then docs.element
      when 'falsify-stage!' then docs.falsifyStage_
      when 'find-element-diffs' then docs.findElementDiffs
      when 'initialize-instance' then docs.initializeInstance
      when 'make-html' then docs.makeHtml
      when 'make-string' then docs.makeString
      when 'mutate-factory' then docs.mutateFactory
      when 'mute-element' then docs.muteElement
      when 'overview' then docs.overview
      when 'patch-instance' then docs.patchInstance
      when 'purify-element' then docs.purifyElement
      when 'release-instance' then docs.releaseInstance
      when 'render-app' then docs.renderApp
      when 'render!' then docs.render_
      when 'gc-states!' then docs.gcStates_
      when 'div' then docs.div
      when 'clear-cache!' then docs.clearCache_
      when 'apply-dom-changes' then docs.applyDomChanges
      else '404'

    div
      style: styleContent
      onClick: link.onClick
      className: 'markdown-docs'
      dangerouslySetInnerHTML: {__html: marked(content)}
