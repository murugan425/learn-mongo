// cat .\script_dataset.js | mongo
use sample;
show collections;

collection = db.school
collection.drop()

students = [{
    _id: 1,
    zipcode: "63109",
    students: [
                 { name: "john", school: 102, age: 10 },
                 { name: "jess", school: 102, age: 11 },
                 { name: "jeff", school: 108, age: 15 }
              ]
   },
   {
    _id: 2,
    zipcode: "63110",
    students: [
                 { name: "ajax", school: 100, age: 7 },
                 { name: "achilles", school: 100, age: 8 },
              ]
   },
   {
    _id: 3,
    zipcode: "63109",
    students: [
                 { name: "ajax", school: 100, age: 7 },
                 { name: "achilles", school: 100, age: 8 },
              ]
   },
   {
    _id: 5,
    zipcode: "63109",
    students: [
                 { name: "barney", school: 102, age: 7 },
                 { name: "ruth", school: 102, age: 16 },
              ]
   }]

collection.save(students);
collection.find();

collection = db.bios
collection.drop()

biographys = [{
    "_id" : 1,
    "name" : {
        "first" : "John",
        "last" : "Backus"
    },
    "birth" : ISODate("1924-12-03T05:00:00Z"),
    "death" : ISODate("2007-03-17T04:00:00Z"),
    "contribs" : "ALGOL",
    "awards" : [
        {
            "award" : "W.W. McDowell Award",
            "year" : 1967,
            "by" : "IEEE Computer Society"
        },
        {
            "award" : "National Medal of Science",
            "year" : 1975,
            "by" : "National Science Foundation"
        },
        {
            "award" : "Turing Award",
            "year" : 1977,
            "by" : "ACM"
        },
        {
            "award" : "Draper Prize",
            "year" : 1993,
            "by" : "National Academy of Engineering"
        }
    ]
},
{
    "_id" : ObjectId("51df07b094c6acd67e492f41"),
    "name" : {
        "first" : "John",
        "last" : "McCarthy"
    },
    "birth" : ISODate("1927-09-04T04:00:00Z"),
    "death" : ISODate("2011-12-24T05:00:00Z"),
    "contribs" : [
        "Lisp",
        "Artificial Intelligence",
        "ALGOL"
    ],
    "awards" : [
        {
            "award" : "Turing Award",
            "year" : 1971,
            "by" : "ACM"
        },
        {
            "award" : "Kyoto Prize",
            "year" : 1988,
            "by" : "Inamori Foundation"
        },
        {
            "award" : "National Medal of Science",
            "year" : 1990,
            "by" : "National Science Foundation"
        }
    ]
},
{
    "_id" : 3,
    "name" : {
        "first" : "Grace",
        "last" : "Hopper"
    },
    "title" : "Rear Admiral",
    "birth" : ISODate("1906-12-09T05:00:00Z"),
    "death" : ISODate("1992-01-01T05:00:00Z"),
    "contribs" : [
        "UNIVAC",
        "compiler",
        "FLOW-MATIC",
        "COBOL"
    ],
    "awards" : [
        {
            "award" : "Computer Sciences Man of the Year",
            "year" : 1969,
            "by" : "Data Processing Management Association"
        },
        {
            "award" : "Distinguished Fellow",
            "year" : 1973,
            "by" : " British Computer Society"
        },
        {
            "award" : "W. W. McDowell Award",
            "year" : 1976,
            "by" : "IEEE Computer Society"
        },
        {
            "award" : "National Medal of Technology",
            "year" : 1991,
            "by" : "United States"
        }
    ]
},
{
    "_id" : 4,
    "name" : {
        "first" : "Kristen",
        "last" : "Nygaard"
    },
    "birth" : ISODate("1926-08-27T04:00:00Z"),
    "death" : ISODate("2002-08-10T04:00:00Z"),
    "contribs" : [
        "OOP",
        "Simula"
    ],
    "awards" : [
        {
            "award" : "Rosing Prize",
            "year" : 1999,
            "by" : "Norwegian Data Association"
        },
        {
            "award" : "Turing Award",
            "year" : 2005,
            "by" : "ACM"
        },
        {
            "award" : "IEEE John von Neumann Medal",
            "year" : 2002,
            "by" : "IEEE"
        }
    ]
},
{
    "_id" : 5,
    "name" : {
        "first" : "Ole-Johan",
        "last" : "Dahl"
    },
    "birth" : ISODate("1931-10-12T04:00:00Z"),
    "death" : ISODate("2002-06-29T04:00:00Z"),
    "contribs" : [
        "OOP",
        "Simula"
    ],
    "awards" : [
        {
            "award" : "Rosing Prize",
            "year" : 1999,
            "by" : "Norwegian Data Association"
        },
        {
            "award" : "Turing Award",
            "year" : 2001,
            "by" : "ACM"
        },
        {
            "award" : "IEEE John von Neumann Medal",
            "year" : 2001,
            "by" : "IEEE"
        }
    ]
},
{
    "_id" : 6,
    "name" : {
        "first" : "Guido",
        "last" : "van Rossum"
    },
    "birth" : ISODate("1956-01-31T05:00:00Z"),
    "contribs" : [
        "Python"
    ],
    "awards" : [
        {
            "award" : "Award for the Advancement of Free Software",
            "year" : 2001,
            "by" : "Free Software Foundation"
        },
        {
            "award" : "NLUUG Award",
            "year" : 2003,
            "by" : "NLUUG"
        }
    ]
},
{
    "_id" : ObjectId("51e062189c6ae665454e301d"),
    "name" : {
        "first" : "Dennis",
        "last" : "Ritchie"
    },
    "birth" : ISODate("1941-09-09T04:00:00Z"),
    "death" : ISODate("2011-10-12T04:00:00Z"),
    "contribs" : [
        "UNIX",
        "C"
    ],
    "awards" : [
        {
            "award" : "Turing Award",
            "year" : 1983,
            "by" : "ACM"
        },
        {
            "award" : "National Medal of Technology",
            "year" : 1998,
            "by" : "United States"
        },
        {
            "award" : "Japan Prize",
            "year" : 2011,
            "by" : "The Japan Prize Foundation"
        }
    ]
},
{
    "_id" : 8,
    "name" : {
        "first" : "Yukihiro",
        "aka" : "Matz",
        "last" : "Matsumoto"
    },
    "birth" : ISODate("1965-04-14T04:00:00Z"),
    "contribs" : [
        "Ruby"
    ],
    "awards" : [
        {
            "award" : "Award for the Advancement of Free Software",
            "year" : "2011",
            "by" : "Free Software Foundation"
        }
    ]
},
{
    "_id" : 9,
    "name" : {
        "first" : "James",
        "last" : "Gosling"
    },
    "birth" : ISODate("1955-05-19T04:00:00Z"),
    "contribs" : [
        "Java"
    ],
    "awards" : [
        {
            "award" : "The Economist Innovation Award",
            "year" : 2002,
            "by" : "The Economist"
        },
        {
            "award" : "Officer of the Order of Canada",
            "year" : 2007,
            "by" : "Canada"
        }
    ]
},
{
    "_id" : 10,
    "name" : {
        "first" : "Martin",
        "last" : "Odersky"
    },
    "contribs" : [
        "Scala"
    ]
}]

collection.insertMany(biographys)
collection.find().pretty()

collection = db.inventory
collection.drop()

inventories = [
    { item: "journal", qty: 25, size: { h: 14, w: 21, uom: "cm" }, status: "A", price: 100 },
    { item: "notebook", qty: 50, size: { h: 8.5, w: 11, uom: "in" }, status: "A" },
    { item: "paper", qty: 100, size: { h: 8.5, w: 11, uom: "in" }, status: "B", sale: true },
    { item: "planner", qty: 75, size: { h: 22.85, w: 30, uom: "cm" }, status: "D", sale: false, price: 60 },
    { item: "postcard", qty: 45, size: { h: 10, w: 15.25, uom: "cm" }, status: "A" },
    { item: "marker", qty: 15, size: { h: 5, w: 10, uom: "cm" }, status: "A", price: "50" },
 ]

collection.insertMany(inventories)
collection.find().pretty()

collection = db.scores
collection.drop()
for(i=0; i<100; i++) {
    names=["exam", "essay", "quiz"];
    for(j=0; j<3; j++) {
        collection.insert({student:i, type:names[j], score:Math.round(Math.random()*100)})
    }
}
collection.find().pretty()

collection = db.students
collection.drop()
studentsscore = [
{ "_id" : 1, "score" : [ -1, 3, 1 ] },
{ "_id" : 2, "score" : [ 1, 5, 0 ] },
{ "_id" : 3, "score" : [ 5, 5 ] }]
collection.insertMany(studentsscore)
collection.find().pretty()

collection = db.users
collection.drop()

userslist = [
    {name:"User1", friends:["Bob", "Fred"], favorites:["swimming","running"]},
    {name:"User2", friends:["Joe", "Pete"], favorites:["swimming","pickles"]},
    {name:"User3", friends:["Pete", "Joe", "Tim", "Bob"], favorites:["pickles","cycling"]},
    {name:"User4", friends:["Joe", "Bob"], favorites:["burger","swimming"]}
 ]

collection.insertMany(userslist)
collection.find().pretty()

