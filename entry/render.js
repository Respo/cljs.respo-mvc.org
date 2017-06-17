
var fs = require('fs');

require.extensions['.cljs'] = function(module, filename) {
  return module.exports = fs.readFileSync(filename, 'utf8');
};

require('../target/client.server_render.js');
