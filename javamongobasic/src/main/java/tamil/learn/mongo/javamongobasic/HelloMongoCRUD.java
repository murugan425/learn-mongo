/**
 * @author Murugan_Nagarajan
 * @date Apr 2, 2018
 */
package tamil.learn.mongo.javamongobasic;

import static tamil.learn.mongo.javamongobasic.utilities.PrintDocument.printJson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;

import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Filters.*;
/**
 * 
 */
public class HelloMongoCRUD {
	static Logger logger = LoggerFactory.getLogger(HelloMongoCRUD.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		logger.info("Starting the app instance to try mongodb CRUD operation");
		MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		MongoDatabase db = client.getDatabase("employee");
		
		//******************CREATE - INSERT******************
		MongoCollection<Document> docCollection = db.getCollection("insertTest");
		docCollection.drop();
		//Created a empty document
		Document myOfficialDoc = new Document();
		//Adding different type of objects along with a key attribute.
		//doc.append(key, value);
		myOfficialDoc.append("id", "Murugan_Nagarajan").append("experience", 9).append("role", "Technology Lead");
		Document myPersonalDoc = new Document();
		myPersonalDoc.append("id", "Murugan425").append("age", 31).append("city", "Shanghai");
		logger.info("BEFORE INSERT");
		logger.info(printJson(myOfficialDoc).getWriter().toString());
		logger.info(printJson(myPersonalDoc).getWriter().toString());
		//Insert one document
		//docCollection.insertOne(myOfficialDoc);
		//Insert many documents
		docCollection.insertMany(Arrays.asList(myOfficialDoc,myPersonalDoc));
		logger.info("AFTER INSERT");
		logger.info(printJson(myOfficialDoc).getWriter().toString());
		logger.info(printJson(myPersonalDoc).getWriter().toString());
		
		//******************RETRIEVE - FIND******************
		//Get only the first document in the collection - Not preferred
		logger.info("TOTAL DOCUMENT COUNT : " + docCollection.count());
		logger.info("FIRST DOCUMENT IN THE COLLECTION \n" +printJson(docCollection.find().first()).getWriter().toString());
		//Get all the documents in a single call and add it to a list
		List<Document> docList = docCollection.find().into(new ArrayList<Document>());
		logger.info("LIST OF DOCS");
		for (Document doc: docList) {
			logger.info(printJson(doc).getWriter().toString());			
		}
		//Get the cursor, iterate it and get the document one by one. Helps to process the documents.
		MongoCursor<Document> cursor = docCollection.find().iterator();
		try {
			logger.info("ACCESS DOCUMENT USING CURSOR");
			while (cursor.hasNext()) {
				logger.info(printJson(cursor.next()).getWriter().toString());				
			}
		} finally {
			cursor.close();
		}
		
		//******************RETRIEVE - FILTER/PROJECTION/SORT/SKIP/LIMIT******************
		MongoCollection<Document> filterCollection = db.getCollection("filterTest");
		filterCollection.drop();
		
		for (int i = 0; i < 100; i++) {
			filterCollection.insertOne(new Document()
					.append("xyz", new Random().nextInt(5))
					.append("abc", new Random().nextInt(100))
					.append("index", i));
		}
		logger.info("BEFORE FILTERING");
		for (Document doc: filterCollection.find()) {
			logger.info(printJson(doc).getWriter().toString());			
		}
		
		Bson filters = and(eq("xyz", 0),gt("abc",60),lte("abc",90));
		
		//Bson projections = exclude("xyz","_id");
		
		//COMBINING EXLUDE & INCLUDE - USE PROJECTION.FIELDS
		Bson projections = fields(include("abc","index"),exclude("_id"));
	
		//Bson sorts = descending("index");
		//COMBINING ASCENDING & DESCENDING - USE SORTS.ORDERBY
		Bson sorts = orderBy(descending("abc"),ascending("index"));
		
		List<Document> filterDocsList = filterCollection.find(filters)
				.projection(projections).into(new ArrayList<Document>());
		logger.info("LIST OF FILTERED DOCS");
		logger.info("AFTER FILTERING - FILTERED DOCS : " + filterDocsList.size());
		for (Document doc: filterDocsList) {
			logger.info(printJson(doc).getWriter().toString());			
		}
		List<Document> filterProjSortDocList = filterCollection.find(filters)
				.projection(projections).sort(sorts).skip(2).limit(20).into(new ArrayList<Document>());
		logger.info("AFTER FILTERING/SORTING/SKIP/LIMITING - FILTERED DOCS : " + filterProjSortDocList.size()); 
		for (Document doc: filterProjSortDocList) {
			logger.info(printJson(doc).getWriter().toString());			
		}
		
		//******************UPDATE - UPSERT/DELETE******************
		MongoCollection<Document> updollection = db.getCollection("updateTest");
		updollection.drop();
		for (int i = 0; i < 10; i++) {
			updollection.insertOne(new Document()
					.append("x", i*10)
					.append("index", i));
		}
		logger.info("BEFORE UPDATE");
		for (Document doc: updollection.find()) {
			logger.info(printJson(doc).getWriter().toString());			
		}
		//updollection.updateOne(eq("index",5), set("x", 5*50));
		
		//UPDATE MULTIPLE ELEMENTS OR EVEN TO ADD NEW ELEMENTS USE UPDATES.COMBINE
		updollection.updateOne(eq("index",5), combine(set("x", 5*50),set("updated",true)));
		
		updollection.updateMany(gte("index", 7), inc("x", 100));
		
		//SET UPSERTS TO TRUE IN CASE IF THE UPDATE NEED TO BEHAVE AS A INSERT AS WELL
		//index: 13 doesn't exist, so it needs to be inserted
		updollection.updateOne(eq("index",13), combine(set("x", 1300),set("updated",true)), 
				new UpdateOptions().upsert(true));
		
		updollection.deleteMany(lte("index", 3));
		
		logger.info("AFTER UPDATE/DELETE");
		for (Document doc: updollection.find().projection(excludeId())) {
			logger.info(printJson(doc).getWriter().toString());			
		}
		client.close();
	}	
};
