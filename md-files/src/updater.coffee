
pathUtil = require 'router-as-view/lib/path'

routes = require './routes'

module.exports = (store, op, opData) ->
  console.log op, opData
  switch op
    when 'router/go' then store.set 'router', opData
    when 'router/go2'
      store.setIn ['router', 'router'], opData
    when 'router/nav'
      router = pathUtil.parseAddress opData, routes
      store.set 'router', router
    else store
