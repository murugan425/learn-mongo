//CREATE
use sample;
show collections;

collection = db.test

collection.drop()

//CREATE
doc = {name:"User1", "age": 31}
collection.insert(doc)

doc = {name:"User2", "age": 25}
collection.insert(doc)

doc = {name:"User3", "age": 20}
collection.insert(doc)

doc = {name:"User4", "age": 35}
collection.insert(doc)


doc = {name:"Apple", "color": "Red", shape: "Round"}
collection.insert(doc)

//SAVE - Updates existing or Inserts new doc
doc = {name:"Orange", "color": "Orange", shape: "Round"}
collection.save(doc, {w:0, j:true, wtimeout: 10})

//WriteResult({
//    "nMatched" : 0,
//    "nUpserted" : 1,
//    "nModified" : 0,
//    "_id" : ObjectId("5b4c818762964e26266642ae")
//})

collection.save(doc, {w:0, j:true, wtimeout: 1000})
//{ "_id" : ObjectId("5b4c818762964e26266642ae"), "name" : "Orange", "color" : "Orange", "shape" : "Round" }

//UPDATE
collection.update({name:"User3"}, {$set:{age: 40}})

collection.find()

collection.update({name:"User5"}, {$set:{age: 40}})
//NoMatch - WriteResult({ "nMatched" : 0, "nUpserted" : 0, "nModified" : 0 })

collection.update({name:"User5"}, {name:"User5", age: 45, mail:"testuser@junk.com"}, {upsert: true})
//WriteResult({
//   "nMatched" : 0,
//   "nUpserted" : 1,
//   "nModified" : 0,
//  "_id" : ObjectId("5b4c8d79a3b1638e2e924c08")
//})
collection.find()

collection.update({age:{$gt: 25}}, {$set:{adults: true}}, {upsert: true, multi:true})

collection.find({name:"User5"})

//FindAndModify
collection.findAndModify({query: {name:"User4"}, update:{$set:{age: 55}}, new:true});

collection.find({name:"User4"})

collection.findAndModify({query: {name:"User4"}, update:{$set:{age: 60}}, fields:{age:1}})

collection.find({name:"User4"})

collection.findAndModify({query: {name:"User4"}, remove:true});