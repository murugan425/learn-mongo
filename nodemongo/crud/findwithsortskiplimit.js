var MongoClient = require('mongodb').MongoClient,
    commandLineArgs = require('command-line-args'), 
    assert = require('assert');


var options = commandLineOptions();

MongoClient.connect('mongodb://localhost:27017/crunchbase', function(err, db) {

    assert.equal(err, null);
    console.log("Successfully connected to MongoDB.");
    
    var query = queryDocument(options);
    var projection = {"_id": 0, "name": 1, "founded_year": 1,
                      "number_of_employees": 1, "crunchbase_url": 1};

    var cursor = db.collection('companies').find(query);
    cursor.projection;
    //Sort Format 1 - The order in which the sorting is done matters
    //Instead of array, if we used field, the order of sorting may not work out in js.    
    cursor.sort([["founded_year", 1], ["number_of_employees", -1]]);

    //Sort Format 2 
    //cursor.sort({founded_year:1});

    //skip and limit are always coupled with sorting for pagination purpose.
    cursor.skip(options.skip);
    cursor.limit(options.limit);

    //MongoDB always sort first, skip next then apply the limit irrespective of their order above.

    var numMatches = 0;

    cursor.forEach(
        function(doc) {
            numMatches += 1;
            console.log(doc.name + "\n\tfounded " + doc.founded_year +
                        "\n\t" + doc.number_of_employees + " employees"); 
        },
        function(err) {
            assert.equal(err, null);
            console.log("Command Options:"+ JSON.stringify(options));
            console.log("Our query was:" + JSON.stringify(query));
            console.log("Matching documents: " + numMatches);
            return db.close();
        }
    );

});


function queryDocument(options) {

    console.log(options);
    
    var query = {
        "founded_year": {
            "$gte": options.firstYear,
            "$lte": options.lastYear
        }//,
        //"number_of_employees" : { "$gte": options.employees }
    };

    if ("employees" in options) {
        query.number_of_employees = { "$gte": options.employees };
    }
        
    return query;
    
}

//TODO: Upgrade the command line args to a recent version using npm
function commandLineOptions() {

    var cli = commandLineArgs([
        { name: "firstYear", alias: "f", type: Number },
        { name: "lastYear", alias: "l", type: Number },
        { name: "employees", alias: "e", type: Number },
        { name: "skip", type: Number, defaultValue: 0 }, //Use --skip in cmd
        { name: "limit", type: Number, defaultValue: 20000 } //Use --limit in cmd
    ]);    
    var options = cli.parse()
    if ( !(("firstYear" in options) && ("lastYear" in options))) {
        console.log(cli.getUsage({
            title: "Usage",
            description: "The first two options below are required. The rest are optional."
        }));
        process.exit();
    }
    return options;    
}
