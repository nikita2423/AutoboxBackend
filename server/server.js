'use strict';
var loopback = require('loopback');
var boot = require('loopback-boot');

var app = module.exports = loopback();



app.start = function() {
    // start the web server
    return app.listen(function() {
        app.emit('started');
        console.log('Web server listening at: %s', app.get('url'));
    });
};

app.use(loopback.context());
app.use(loopback.cookieParser('Some secret?'));

//Add cookie support
//https://loopback.io/doc/en/lb2/Making-authenticated-requests.html#using-current-user-id-as-a-literal-in-urls-for-rest
/*app.use(loopback.token({
 model: app.models.accessToken
 }));*/
// use loopback.token middleware on all routes
// setup gear for authentication using cookie (access_token)
// Note: requires cookie-parser (defined in middleware.json)
app.use(loopback.token({
    model: app.models.accessToken,
    currentUserLiteral: 'me',
    searchDefaultTokenKeys: false,
    cookies: ['access_token'],
    headers: ['access_token', 'X-Access-Token'],
    params: ['access_token']
}));
app.disable('x-powered-by');

app.use(function (req, res, next) {
    if(req.headers.authorization){
        const AccessToken = app.models["AccessToken"];
        if(req.headers.authorization){
            AccessToken.findById(req.headers.authorization)
                .then(function (accessToken) {
                    req.accessToken = accessToken || null;
                    next();
                })
                .catch(function (error) {
                    req.accessToken = null;
                    next();
                });
        }else{
            next();
        }
    }else{
        //req.accessToken = null;
        next();
    }
});

// Bootstrap the application, configure models, datasources and middleware.
// Sub-apps like REST API are mounted via boot scripts.
boot(app, __dirname, function(err) {
    if (err) throw err;
    //Now load the PLUGINS..
    var helper = require(__dirname + '/../common/helper')(app);
    helper.initPlugins();

    // start the server if `$ node server.js`
    if (require.main === module)
    //Now memoizing the listen http Server.
        app.start = app.start();
});
