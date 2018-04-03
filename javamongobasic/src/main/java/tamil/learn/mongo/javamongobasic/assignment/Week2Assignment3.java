/**
 * @author Murugan_Nagarajan
 * @date Apr 3, 2018
 */
package tamil.learn.mongo.javamongobasic.assignment;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Filters.*;

import static tamil.learn.mongo.javamongobasic.utilities.PrintDocument.printJson;

import java.util.ArrayList;
import java.util.List;

public class Week2Assignment3 {

	public static void main(String[] args) {
		MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		MongoDatabase db = client.getDatabase("students");
		MongoCollection<Document> docCollection = db.getCollection("grades");
		Bson filters = eq("type", "homework");
		//Bson projections = fields(excludeId());
		Bson sorts = orderBy(ascending("student_id", "score"));

		List<Document> docList = docCollection.find(filters).
								sort(sorts).into(new ArrayList<Document>());
		
		Document previousDoc = docList.get(0);
		for (Document doc: docList) {
			if(docList.indexOf(doc) == 0 || !(doc.get("student_id").toString().equals(previousDoc.get("student_id").toString()))) {
				System.out.println(printJson(doc).getWriter());
				//docCollection.deleteOne(doc);
			}
			previousDoc = doc;			
		}
		
		/*for (Document doc: docList) {
			System.out.println(printJson(doc).getWriter());
		}*/
		
		System.out.println(docList.size());
		client.close();
	}

}
