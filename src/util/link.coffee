
recorder = require 'actions-in-recorder'

tracking = require './tracking'

onClick = (event) ->
  target = event.target
  if target.tagName is 'A'
    event.preventDefault()
    if target.href.indexOf(location.origin) is 0
      path = target.href.replace location.origin, ''
      recorder.dispatch 'router/nav', path
      tracking.event 'jump', path
    else
      tracking.event 'open-link', target.href
      window.open target.href

exports.onClick = onClick
