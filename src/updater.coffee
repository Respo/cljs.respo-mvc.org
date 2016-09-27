
module.exports = (store, op, opData) ->
  console.log op, opData
  switch op
    when 'router/go' then store.set 'router', opData
    else store