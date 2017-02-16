
exports.event = (category, action, labal, value) ->
  if window.ga?
    ga 'send', 'event', category, action, labal, value
