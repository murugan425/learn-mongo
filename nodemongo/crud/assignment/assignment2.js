//Import the required data into mongodb.
//mongoimport -d crunchbase -c companies companies.json

var MongoClient = require('mongodb').MongoClient,
    assert = require('assert');

MongoClient.connect('mongodb://localhost:27017/assignment', function(err, db) {

    assert.equal(err, null);
    console.log("Successfully connected to MongoDB.");

    var filter = {"category_code": "software"};
    var projection = {"name": 1, "category_code": 1, "_id": 0};
    //Append Projection to cursor
    var cursor = db.collection('grades').find();
    cursor.sort({"grade" : 1});
    cursor.skip(6);
    cursor.limit(2);
    
    cursor.forEach(
        function(doc) {
            //console.log( doc.name + " is a " + doc.category_code + " company." );
            console.log(doc);
        },
        function(err) {
            assert.equal(err, null);
            return db.close();
        }
    )
});
