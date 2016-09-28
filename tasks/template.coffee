
fs = require 'fs'
stir = require('stir-template')
settings = require('./settings')
resource = require('./resource')

{html, head, title, meta, link, script, body, div, style} = stir

logoUrl = 'https://avatars3.githubusercontent.com/u/20469468?v=3&s=200'

module.exports = (env) ->
  config = settings.get(env)
  assets = resource.get(config)
  gaHtml = fs.readFileSync './tasks/ga.html', 'utf8'

  stir.render stir.doctype(),
    html {},
      head {},
        title {}, 'Respo: an MVC library in ClojureScript'
        meta charset: 'utf-8'
        meta name: 'description', content: 'an MVC library learning from React.js'
        link rel: 'icon', href: logoUrl
        if assets.style?
          link rel: 'stylesheet', href: assets.style
        script src: assets.vendor, defer: true
        script src: assets.main, defer: true
        style {}, 'body * {box-sizing: border-box;}'
        if config.useGa then gaHtml
    body style: 'margin: 0;',
      div id: 'app'
