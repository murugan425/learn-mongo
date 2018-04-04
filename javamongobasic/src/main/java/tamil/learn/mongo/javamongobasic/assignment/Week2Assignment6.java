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
import java.util.Arrays;
import java.util.List;

public class Week2Assignment6 {

	public static void main(String[] args) {
		MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		MongoDatabase db = client.getDatabase("video");
		MongoCollection<Document> docCollection = db.getCollection("movieDetails");
		Bson filters = and(eq("countries.1", "Sweden"));
		Bson projections = include("title","countries");
		//Bson filters = all("genres", Arrays.asList("Comedy","Crime"));	
		//Bson filters = eq("genres",Arrays.asList("Comedy","Crime"));
		//Bson filters = and(eq("genres.0", "Comedy"), ("genres", Arrays.asList("Comedy", "Crime")));
		//Bson projections = include("title","genres");
		List<Document> docList = docCollection.find(filters).projection(projections).into(new ArrayList<Document>());
		
		for (Document doc: docList) {
			System.out.println(printJson(doc).getWriter().toString());			
		}
		System.out.println(docList.size());
		//System.out.println(printJson(docCollection.find().first()).getWriter().toString());
		client.close();
	}

}
