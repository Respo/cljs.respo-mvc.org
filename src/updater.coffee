
module.exports = (store, op, opData) ->
  console.log op, opData
  switch op
    when 'router/go' then store.set 'router', opData
    when 'router/go2'
      store.setIn ['router', 'router'], opData
    else store
