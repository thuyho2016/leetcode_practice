var http = require('http');
var dt = require('./myfirstmodule');

http.createServer(function (req, res) {
    res.writeHead(200, {'Content-Type': 'text/html'});
    res.end('Hello World!');
    res.write("The date and time are currrently: " + dt.myDateTime());
    res.end();
}).listen(8080);
