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
import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Filters.*;

import static tamil.learn.mongo.javamongobasic.utilities.PrintDocument.printJson;

import java.util.ArrayList;
import java.util.List;

public class Week2Assignment2 {

	public static void main(String[] args) {
		MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		MongoDatabase db = client.getDatabase("students");
		MongoCollection<Document> docCollection = db.getCollection("grades");
		Bson filters = gte("score", 65);
		List<Document> docList = docCollection.find().filter(filters).sort(ascending("score")).into(new ArrayList<Document>());
		System.out.println(docCollection.count());
		
		for (Document doc: docList) {
			System.out.println(printJson(doc).getWriter().toString());			
		}
		
		System.out.println(printJson(docCollection.find().first()).getWriter().toString());
		client.close();
	}

}
