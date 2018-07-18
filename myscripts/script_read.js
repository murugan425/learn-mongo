use sample;
show collections;

coll_inventory = db.inventory
coll_bios = db.bios
coll_students = db.students
coll_score = db.scores

//SELECT * FROM inventory
coll_inventory.find()
print("=======================================================================")

//SELECT * FROM inventory WHERE status = "A"
coll_inventory.find({status: 'A'})
print("=======================================================================")

//SELECT * FROM inventory WHERE status in ("A", "D")
coll_inventory.find({status:{$in:['A','D']}})
print("=======================================================================")

//SELECT * FROM inventory WHERE status = "A" AND qty < 30
coll_inventory.find({status: 'A', qty: {$lt: 30}})

//In this case only the price lt 80 is retreived the other query is not considered
coll_inventory.find({price:{$lt:110}, price:{$lt:80}})
//Use AND/OR operator to rescue
coll_inventory.find({$and:[{price:{$lt:110}}, {price:{$lt:105}}]})
coll_inventory.find({$or:[{price:{$lt:80}}, {price:{$gt:90}}]})

//AND will be helpful if in case you need to use same field in the query multiple times
coll_inventory.find({$and:[{status: 'A'}, {qty: {$lt: 30}}]})

coll_inventory.find({$and:[{item: {$regex: '^p'}}, {item: {$regex: 'r$'}}, {qty: {$lt: 150}}]})
print("=======================================================================")

coll_inventory.find( { $or: [ { status: "A" }, { qty: { $lt: 80 } } ] } )
print("=======================================================================")

coll_inventory.find({status: {$ne: 'A'}})
print("=======================================================================")

//Is different from $lte, return those documents which doesn't have the field as well along with $lte.
//$not & $nor both returns the documents which doesn't contains the specified field/fields in the query
coll_inventory.find( { price: { $lte: 2 }  } )
print("**********")
coll_inventory.find( { price: { $not: { $gt: 2 } } } )
print("**********")
print("=======================================================================")
//REGEX
coll_inventory.find( { item:  /^p.*/ }) 
print("**********")
coll_inventory.find( { item: { $not: /^p.*/ } })
print("=======================================================================")

print("$nor: [ { price: 100 }, { sale: true } ]");
coll_inventory.find( { $nor: [ { price: 100 }, { sale: true } ]  } )
print("**********")
print("$nor: [ { price: {$gt:10}}, { qty: { $lt: 52 } }, { sale: true } ]")
coll_inventory.find( { $nor: [ { price: {$gt:10}}, { qty: { $lt: 52 } }, { sale: true } ] } )
print("**********")
print("$nor: [ { price: 1.99 }, { price: { $exists: false } },{ sale: true }, { sale: { $exists: false } } ]")
//Both price & sale should exists and contradicts with the query condition, price != 100 & sale != true
coll_inventory.find( { $nor: [ { price: 100 }, { price: { $exists: false } }, 
                         { sale: true }, { sale: { $exists: false } } ] } )
print("=======================================================================")

coll_bios.find().pretty()

//ARRAY ELEMENTS MATCH
coll_students.find( { score: { $eq: 5 } } );
print("=========================================")
coll_students.find( { score: { $lt: 0 } } );
print("=======================================================================")
// if contribs is an array - mongo checks inside the array to check any one element matchint this query
//Array contains 
coll_bios.find( { contribs: "UNIX" } ).pretty()
print("=========================================")
//EMPTY Array Check
coll_bios.find({"contribs":[]}).pretty()
print("=========================================")
//Regex Match applied on all elements in the array and direct match is also check if contribs is not an array
coll_bios.find({"contribs":{$regex:"GOL$"}}).pretty()
print("=========================================")
//Any single element in the array 'awards' which has a specific field match 'by'
coll_bios.find( { "awards.by": "ACM" } ).pretty()

//Those documents in which the array elements meets the criteria, not necessarily the same element
coll_bios.find( { "awards.by": "IEEE", "awards.year":2001 } ).pretty()

//All of the specified elements in the query should occur in the document array
//The order doesn't matter here, (it should be subset or exact match)
coll_students.find( { score: { $all: [5, 1] } } );

//All document with score array that contains any one of these query element mentioned in the IN condition
coll_students.find( { score: { $in: [0, -1] } } );

//Match multiple array elements - mixing $in and $all
db.users.find({friends:{$all:["Joe", "Bob"]}, favorites:{$in:["running","pickles"]}})

//Those documents in which the array elements meets the criteria, in the same element, not mutliple elements
coll_bios.find({awards: {$elemMatch: {year: { $eq: 2001 }, by: "IEEE" }}}).pretty()

//Embedded Document - The inner document should be exactly matching with the query
coll_bios.find({name: {first: "Yukihiro", aka : "Matz", last: "Matsumoto" }}).pretty()

//Any additonal fields other than the query field elements is not considered in match criteria
coll_bios.find({"name.first": "Yukihiro", "name.last": "Matsumoto" }).pretty()

print("=======================================================================")
coll_score.find({score: {$gt:50, $lt:60}})

coll_score.find({score: 50}, {_id:0, student:1})

coll_score.find({score: 50})

//Just like numeric, any item that starts with b to o. - case sensitive
//Both the below queries are not same
coll_inventory.find({item: {$gte:"b", $lt:"o"}})
coll_inventory.find({item: {$gte:"B", $lt:"O"}})
print("=======================================================================")
coll_inventory.find({price:{$exists:true}})
coll_inventory.find({price:{$type:2}}) //BSON data type of "String" is 2
//Regex - item contains the specified letter.
coll_inventory.find({item: {$regex: "a"}})
//Regex - item ends with the specified letter.
coll_inventory.find({item: {$regex: "r$"}})
//Regex - item starts with the specified letter.
coll_inventory.find({item: {$regex: "^p"}})

//CURSOR
print("=========================================")
cur = coll_inventory.find().batchSize(2); null; 
//null stops the cursor from getting printed
//batch size changes the default batch size of the cursor, default 16Mb per batch

//sort or limit or skip is processed in mongo server not in the client
//So sort/limit/skip cannot be done once cursor is opened / after iterating or calling hasNext on the cursor
//NOTE : SORT / SKIP / LIMIT will be called in this order even if you interchangely called them on the client.
//cur.sort({item: -1}).limit(3).skip(2)
//cur.sort({item: -1}).skip(2).limit(3)
cur.limit(3).skip(2).sort({item: -1})

while(cur.hasNext()) printjson(cur.next());
print("=========================================")
cureach = coll_inventory.find(); null; 
cureach.limit(3).skip(2).sort({item: -1})

cureach.forEach(print);
print("=================******===================")
curarray = coll_inventory.find(); null; 
curarray.toArray(); null;
print("=========================================")
curarray[4]
print("=========================================")
curstime = coll_inventory.find(); null; 
//by default cursors will be closed after 10 mins if not accessed 
curstime.noCursorTimeout();
curstime.close()
//once closed the cursor cannot be accessed
curstime.toArray();

