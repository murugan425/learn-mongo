var MongoClient = require('mongodb').MongoClient,
    Twitter = require('twitter'),
    assert = require('assert');

//TODO - App not streaming the twitter data. Make this app work and load the data to mongodb
require('dotenv').config();
var twitterClient = new Twitter({
    consumer_key: process.env.TWITTER_CONSUMER_KEY,
    consumer_secret: process.env.TWITTER_CONSUMER_SECRET,
    access_token_key: process.env.TWITTER_ACCESS_TOKEN_KEY,
    access_token_secret: process.env.TWITTER_ACCESS_TOKEN_SECRET
});


MongoClient.connect('mongodb://localhost:27017/social', function(err, db) {
    assert.equal(null, err);
    console.log("Successfully connected to MongoDB.");

    /*twitterClient.stream('statuses/filter', {track: "marvel"}, function(stream) {
        stream.on('data', function(status) {
            console.log(status.text);
            db.collection("statuses").insertOne(status, function(err, res) {
                console.log("Inserted document with _id: " + res.insertedId + "\n");
            });
        });
 
        stream.on('error', function(error) {
            throw error;
        });
    });*/

    /*twitterClient.stream('statuses/filter', { follow: 'murugan425' }, (stream) => {
        stream.on('data', tweet => {
            console.log('tweet', tweet)
        })

        stream.on('error', error => {
            console.log('error', error)
        })
    });*/

});

