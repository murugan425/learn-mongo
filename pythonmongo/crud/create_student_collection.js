//Use the below script in terminal to generate a new collection termed "scores" in "school" db
//mongo < create_student_collection.js

use school;
db.scores.drop();
var types = ['exam', 'homework', 'quiz']
for (student_id = 0; student_id < 100; student_id++) {
    for (type=0; type < 3; type++) {
    var r = {'student_id':student_id, 'type':types[type], 'score':Math.random() * 100};
    db.scores.insert(r);
    }
}




