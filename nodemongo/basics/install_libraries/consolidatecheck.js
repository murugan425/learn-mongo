var express = require('express'),
    app = express(),
    engines = require('consolidate');

app.engine('html', engines.nunjucks); //nunjucks is a templating engine that will be used in this example
app.set('view engine', 'html'); //use this engine to render html files
app.set('views', __dirname + '/views'); //define where the template files are stored

app.get('/', function(req, res) {
    res.render('hello_template', { name : 'Murugan' }); //Use render to bind the value of attribute to the template
});

app.use(function(req, res){
    res.sendStatus(404); 
});

var server = app.listen(3000, function() {
    var port = server.address().port;
    console.log('Express server listening on port %s', port);
});
