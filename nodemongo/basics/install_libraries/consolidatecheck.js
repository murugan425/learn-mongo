var express = require('express'),
    app = express(),
    engines = require('consolidate');

app.engine('html', engines.nunjucks); //nunjucks is a templating engine that will be used in this example
app.set('view engine', 'html'); //use this engine to render html files
app.set('views', __dirname + '/views'); //define where the template files are stored

app.get('/', function(req, res) {
    res.render('hello_template', { name : 'Murugan' }); //Use render to bind the value of attribute to the template
});

app.get('/:fullname', function(req, res, next) {
    var reqname = req.params.fullname;
    var getvar1 = req.query.var1;
    var getvar2 = req.query.var2;
    res.render('helloreqvar_template', { user : reqname, param1 : getvar1, param2 : getvar2 });
});

app.get('/:firstname/:lastname', function(req, res, next) {
    var reqname = req.params.firstname;
    var lname = req.params.lastname
    var userage = req.query.age;
    var userheight = req.query.height;
    console.log(lname);
    res.render('helloreqvar_template', { user : reqname, param1 : userage, param2 : userheight });
});

app.use(errorHandler);

function errorHandler(err, req, res, next) {
    console.error(err.message);
    console.error(err.stack);
    res.status(500).render('error_template', { error: err });
}

var server = app.listen(3000, function() {
    var port = server.address().port;
    console.log('Express server listening on port %s', port);
});
