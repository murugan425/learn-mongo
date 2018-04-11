var express = require('express'),
    cons = require('consolidate'),
    mongodb = require('mongodb');

console.log("EXPRESS: "+ express.json.name);
console.log("MONGODB: "+ mongodb.MongoClient.name);

console.log("All Dependencies installed successfully.")