#!/usr/bin/env node

/*Written by Robins Gupta*/
"use strict";


var program = require('commander');
var exec = require('child_process').exec;

var run = function(cmd){
    var child = exec(cmd, function (error, stdout, stderr) {
        if (stderr !== null) {
            console.log('' + stderr);
        }
        if (stdout !== null) {
            console.log('' + stdout);
        }
        if (error !== null) {
            console.log('' + error);
        }
    });
};

program
    .version('0.1.3')
    .option('i, --install ', 'install packages')
    .parse(process.argv);



if (program.install) {
    run('npm install');
}


var count = 0;


// If parameter is missing or not supported, display help
program.options.filter(function (option) {
    if(!(option.short == process.argv[2]))
        count++
});

if(count == program.options.length)
    program.help();