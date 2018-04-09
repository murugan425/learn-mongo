//Import the required data into mongodb.
//mongoimport -d crunchbase -c companies companies.json

var MongoClient = require('mongodb').MongoClient,
    assert = require('assert');

MongoClient.connect('mongodb://localhost:27017/crunchbase', function(err, db) {

    assert.equal(err, null);
    console.log("Successfully connected to MongoDB.");

    var query = {"category_code": "software"};
    //Query - Retrieve all the data and place it in a array object which takes memory
    db.collection('companies').find(query).toArray(function(err, docs) {

        assert.equal(err, null);
        assert.notEqual(docs.length, 0);
        
        docs.forEach(function(doc) {
            console.log( doc.name + " is a " + doc.category_code + " company." );
        });
    });
    //Cursor - Doesn't retrieve the data until the data is used.
    //Data will be retrieved as batches and the callback will be called for each batches.
    //So, the callback doesn't need to wait until all the data is retreived.
    //Callback Process is executed parallel to the DB Retreival, which improves the performance and reduces memory utilization
    var cursor = db.collection('companies').find(query);
    cursor.forEach(
        function(doc) {
            console.log( doc.name + " is a " + doc.category_code + " company." );
        },
        function(err) {
            assert.equal(err, null);
            return db.close();
        }
    )
});
